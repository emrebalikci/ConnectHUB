package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateSkillCheckService;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.UKViolationException;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateSkillDao;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSkillAddDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateSkillCheckManager extends BaseCheckManager implements CandidateSkillCheckService {

    private final CandidateSkillDao candidateSkillDao;

    @SneakyThrows
    @Override
    public void existsCandidateSkillById(Integer candSkillId) {
        if (CheckUtils.notExistsById(candidateSkillDao, candSkillId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("Candidate's skill info"));
    }

    @SneakyThrows
    @Override
    public void checkIfViolatesUk(CandidateSkillAddDto candSkillAddDto) {
        if (candidateSkillDao.existsBySkill_IdAndCandidate_Id
                (candSkillAddDto.getSkillId(), candSkillAddDto.getCandidateId()))
            throw new UKViolationException(Msg.UK_CAND_SKILL.get());
    }
}
