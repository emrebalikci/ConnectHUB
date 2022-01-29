package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.CandidateJobExperienceService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateCheckService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateJobExpCheckService;
import com.emrebalikci.connecthub.core.business.abstracts.CheckService;
import com.emrebalikci.connecthub.core.entities.ApiError;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.Utils;
import com.emrebalikci.connecthub.core.utilities.results.*;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateJobExperienceDao;
import com.emrebalikci.connecthub.dataAccess.abstracts.PositionDao;
import com.emrebalikci.connecthub.entities.concretes.CandidateJobExperience;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateJobExperienceAddDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateJobExperienceManager implements CandidateJobExperienceService {

    private final CandidateJobExperienceDao candidateJobExpDao;
    private final PositionDao positionDao;
    private final CheckService check;
    private final CandidateCheckService candidateCheck;
    private final CandidateJobExpCheckService candidateJobExpCheck;
    private final ModelMapper modelMapper;

    @Override
    public DataResult<List<CandidateJobExperience>> getAll() {
        return new SuccessDataResult<>(candidateJobExpDao.findAll());
    }

    @Override
    public DataResult<List<CandidateJobExperience>> getByQuitYear(Short sortDirection) {
        Sort sort = Utils.getSortByDirection(sortDirection, "quitYear");
        return new SuccessDataResult<>(Msg.SORT_DIRECTION.getCustom("%s (quitYear)"), candidateJobExpDao.findAll(sort));
    }

    @Override
    public DataResult<?> add(CandidateJobExperienceAddDto candJobExpAddDto) {
        candidateCheck.existsCandidateById(candJobExpAddDto.getCandidateId());
        check.existsPositionById(candJobExpAddDto.getPositionId());
        candidateJobExpCheck.checkIfViolatesUk(candJobExpAddDto);
        check.noStartEndYearConflict(candJobExpAddDto.getStartYear(), candJobExpAddDto.getQuitYear());
        ErrorDataResult<ApiError> errors = Utils.getErrorsIfExist(check);
        if (errors != null) return errors;

        CandidateJobExperience candJobExp = modelMapper.map(candJobExpAddDto, CandidateJobExperience.class);
        CandidateJobExperience savedCandJobExp = candidateJobExpDao.save(candJobExp);
        savedCandJobExp.setPosition(positionDao.getById(savedCandJobExp.getPosition().getId()));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedCandJobExp);
    }

    @Override
    public Result deleteById(int candJobExpId) {
        candidateJobExpCheck.existsCandidateJobExpById(candJobExpId);
        candidateJobExpDao.deleteById(candJobExpId);
        return new SuccessResult(Msg.DELETED.get());
    }

    @Override
    public Result updateWorkPlace(String workPlace, int candJobExpId) {
        candidateJobExpCheck.existsCandidateJobExpById(candJobExpId);
        CandidateJobExperience candJobExp = candidateJobExpDao.getById(candJobExpId);
        check.notTheSame(candJobExp.getWorkPlace(), workPlace, "Work place");
        candJobExp.setWorkPlace(workPlace);
        return execLastUpdAct(candJobExp);
    }

    @Override
    public Result updatePosition(short positionId, int candJobExpId) {
        candidateJobExpCheck.existsCandidateJobExpById(candJobExpId);
        check.existsPositionById(positionId);
        CandidateJobExperience candJobExp = candidateJobExpDao.getById(candJobExpId);
        check.notTheSame(candJobExp.getPosition().getId(), positionId, "Position");
        candJobExp.setPosition(positionDao.getById(positionId));
        return execLastUpdAct(candJobExp);
    }

    @Override
    public Result updateStartYear(short startYear, int candJobExpId) {
        candidateJobExpCheck.existsCandidateJobExpById(candJobExpId);
        CandidateJobExperience candJobExp = candidateJobExpDao.getById(candJobExpId);
        check.notTheSame(candJobExp.getStartYear(), startYear, "Start year");
        check.noStartEndYearConflict(startYear, candJobExp.getQuitYear());
        candJobExp.setStartYear(startYear);
        return execLastUpdAct(candJobExp);
    }

    @Override
    public Result updateQuitYear(Short quitYear, int candJobExpId) {
        candidateJobExpCheck.existsCandidateJobExpById(candJobExpId);
        CandidateJobExperience candJobExp = candidateJobExpDao.getById(candJobExpId);
        check.notTheSame(candJobExp.getQuitYear(), quitYear, "Quit year");
        check.noStartEndYearConflict(candJobExp.getStartYear(), quitYear);
        candJobExp.setQuitYear(quitYear);
        return execLastUpdAct(candJobExp);
    }

    private Result execLastUpdAct(CandidateJobExperience candJobExp) {
        ErrorDataResult<ApiError> errors = Utils.getErrorsIfExist(check);
        if (errors != null) return errors;
        CandidateJobExperience savedCandJobExp = candidateJobExpDao.save(candJobExp);
        return new SuccessDataResult<>(Msg.UPDATED.get(), savedCandJobExp);
    }

}