package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.CandidateSkillService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateCheckService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateSkillCheckService;
import com.emrebalikci.connecthub.core.business.abstracts.CheckService;
import com.emrebalikci.connecthub.core.entities.ApiError;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.Utils;
import com.emrebalikci.connecthub.core.utilities.results.*;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateSkillDao;
import com.emrebalikci.connecthub.dataAccess.abstracts.SkillDao;
import com.emrebalikci.connecthub.entities.concretes.CandidateSkill;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSkillAddDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CandidateSkillManager implements CandidateSkillService {

    private final CandidateSkillDao candidateSkillDao;
    private final SkillDao skillDao;
    private final CheckService check;
    private final CandidateCheckService candidateCheck;
    private final CandidateSkillCheckService candidateSkillCheck;
    private final ModelMapper modelMapper;

    @Override
    public DataResult<List<CandidateSkill>> getAll() {
        return new SuccessDataResult<>(candidateSkillDao.findAll());
    }

    @Override
    public DataResult<?> add(CandidateSkillAddDto candSkillAddDto) {
        candidateCheck.existsCandidateById(candSkillAddDto.getCandidateId());
        check.existsSkillById(candSkillAddDto.getSkillId());
        candidateSkillCheck.checkIfViolatesUk(candSkillAddDto);
        CandidateSkill candidateSkill = modelMapper.map(candSkillAddDto, CandidateSkill.class);
        CandidateSkill savedCandSkill = candidateSkillDao.save(candidateSkill);
        savedCandSkill.setSkill(skillDao.getById(savedCandSkill.getSkill().getId()));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedCandSkill);
    }

    @Override
    public Result addMultiple(List<CandidateSkillAddDto> candSkillAddDtoList) {
        Map<String, Object> errors = new LinkedHashMap<>();
        List<Object> savedCandSkills = new ArrayList<>();
        short failed = 0;
        for (int i = 0; i < candSkillAddDtoList.size(); i++) {
            DataResult<?> result = add(candSkillAddDtoList.get(i));
            if (!result.isSuccess()) {
                errors.put(String.format("Skill[%d]", i), result);
                failed++;
            } else savedCandSkills.add(result.getData());
        }
        if (failed > 0) return new ErrorDataResult<>(Msg.FAILED.get(), new ApiError(errors));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedCandSkills);
    }

    @Override
    public Result deleteById(int candSkillId) {
        candidateSkillCheck.existsCandidateSkillById(candSkillId);
        candidateSkillDao.deleteById(candSkillId);
        return new SuccessResult(Msg.DELETED.get());
    }

}
