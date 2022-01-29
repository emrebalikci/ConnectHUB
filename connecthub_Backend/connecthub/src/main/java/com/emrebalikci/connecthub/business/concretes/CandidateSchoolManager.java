package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.CandidateSchoolService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateCheckService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateSchoolCheckService;
import com.emrebalikci.connecthub.core.business.abstracts.CheckService;
import com.emrebalikci.connecthub.core.entities.ApiError;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.Utils;
import com.emrebalikci.connecthub.core.utilities.results.*;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateSchoolDao;
import com.emrebalikci.connecthub.dataAccess.abstracts.DepartmentDao;
import com.emrebalikci.connecthub.dataAccess.abstracts.SchoolDao;
import com.emrebalikci.connecthub.entities.concretes.CandidateSchool;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSchoolAddDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateSchoolManager implements CandidateSchoolService {

    private final CandidateSchoolDao candidateSchoolDao;
    private final SchoolDao schoolDao;
    private final DepartmentDao departmentDao;
    private final CheckService check;
    private final CandidateCheckService candidateCheck;
    private final CandidateSchoolCheckService candidateSchoolCheck;
    private final ModelMapper modelMapper;

    @Override
    public DataResult<List<CandidateSchool>> getAll() {
        return new SuccessDataResult<>(candidateSchoolDao.findAll());
    }

    @Override
    public DataResult<List<CandidateSchool>> getByGradYear(Short sortDirection) {
        Sort sort = Utils.getSortByDirection(sortDirection, "graduationYear");
        return new SuccessDataResult<>(Msg.SORT_DIRECTION.getCustom("%s (graduationYear)"), candidateSchoolDao.findAll(sort));
    }

    @Override
    public Result add(CandidateSchoolAddDto candSchAddDto) {
        candidateCheck.existsCandidateById(candSchAddDto.getCandidateId());
        check.existsSchoolById(candSchAddDto.getSchoolId());
        check.existsDepartmentById(candSchAddDto.getDepartmentId());
        candidateSchoolCheck.checkIfViolatesUk(candSchAddDto);
        check.noStartEndYearConflict(candSchAddDto.getStartYear(), candSchAddDto.getGraduationYear());
        ErrorDataResult<ApiError> res = Utils.getErrorsIfExist(check);
        if (res != null) return res;

        CandidateSchool candidateSchool = modelMapper.map(candSchAddDto, CandidateSchool.class);
        CandidateSchool savedCandSch = candidateSchoolDao.save(candidateSchool);
        savedCandSch.setSchool(schoolDao.getById(savedCandSch.getSchool().getId()));
        savedCandSch.setDepartment(departmentDao.getById(savedCandSch.getDepartment().getId()));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedCandSch);
    }

    @Override
    public Result deleteById(int candSchId) {
        candidateSchoolCheck.existsCandidateSchoolById(candSchId);
        candidateSchoolDao.deleteById(candSchId);
        return new SuccessResult(Msg.DELETED.get());
    }

    @Override
    public Result updateSchool(int schoolId, int candSchId) {
        candidateSchoolCheck.existsCandidateSchoolById(candSchId);
        check.existsSchoolById(schoolId);
        CandidateSchool candidateSchool = candidateSchoolDao.getById(candSchId);
        check.notTheSame(candidateSchool.getSchool().getId(), schoolId, "School");
        candidateSchool.setSchool(schoolDao.getById(schoolId));
        return execLastUpdAct(candidateSchool);
    }

    @Override
    public Result updateDepartment(short departmentId, int candSchId) {
        candidateSchoolCheck.existsCandidateSchoolById(candSchId);
        check.existsDepartmentById(departmentId);
        CandidateSchool candidateSchool = candidateSchoolDao.getById(candSchId);
        check.notTheSame(candidateSchool.getDepartment().getId(), departmentId, "Department");
        candidateSchool.setDepartment(departmentDao.getById(departmentId));
        return execLastUpdAct(candidateSchool);
    }

    @Override
    public Result updateStartYear(short startYear, int candSchId) {
        candidateSchoolCheck.existsCandidateSchoolById(candSchId);
        CandidateSchool candidateSchool = candidateSchoolDao.getById(candSchId);
        check.notTheSame(candidateSchool.getStartYear(), startYear, "Start year");
        check.noStartEndYearConflict(startYear, candidateSchool.getGraduationYear());
        candidateSchool.setStartYear(startYear);
        return execLastUpdAct(candidateSchool);
    }

    @Override
    public Result updateGradYear(Short graduationYear, int candSchId) {
        candidateSchoolCheck.existsCandidateSchoolById(candSchId);
        CandidateSchool candidateSchool = candidateSchoolDao.getById(candSchId);
        check.notTheSame(candidateSchool.getGraduationYear(), graduationYear, "Graduation year");
        check.noStartEndYearConflict(candidateSchool.getStartYear(), graduationYear);
        candidateSchool.setGraduationYear(graduationYear);
        return execLastUpdAct(candidateSchool);
    }

    private Result execLastUpdAct(CandidateSchool candidateSchool) {
        ErrorDataResult<ApiError> res = Utils.getErrorsIfExist(check);
        if (res != null) return res;
        CandidateSchool savedCandSch = candidateSchoolDao.save(candidateSchool);
        return new SuccessDataResult<>(Msg.UPDATED.get(), savedCandSch);
    }

}
