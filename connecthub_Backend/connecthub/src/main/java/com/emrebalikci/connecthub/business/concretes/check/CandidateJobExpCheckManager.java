package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateJobExpCheckService;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.UKViolationException;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateJobExperienceDao;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateJobExperienceAddDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateJobExpCheckManager extends BaseCheckManager implements CandidateJobExpCheckService {

    private final CandidateJobExperienceDao candidateJobExpDao;

    @SneakyThrows
    @Override
    public void existsCandidateJobExpById(Integer candJobExpId) {
        if (CheckUtils.notExistsById(candidateJobExpDao, candJobExpId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("Candidate's job experience info"));
    }

    @SneakyThrows
    @Override
    public void checkIfViolatesUk(CandidateJobExperienceAddDto candJobExpAddDto) {
        if (candidateJobExpDao.existsByWorkPlaceAndPosition_IdAndCandidate_Id
                (candJobExpAddDto.getWorkPlace(), candJobExpAddDto.getPositionId(), candJobExpAddDto.getCandidateId()))
            throw new UKViolationException(Msg.UK_CAND_JOB_EXP.get());
    }
}
