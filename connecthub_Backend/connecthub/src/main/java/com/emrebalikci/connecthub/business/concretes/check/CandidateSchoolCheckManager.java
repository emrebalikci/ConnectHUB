package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateSchoolCheckService;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.UKViolationException;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateSchoolDao;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSchoolAddDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateSchoolCheckManager extends BaseCheckManager implements CandidateSchoolCheckService {

    private final CandidateSchoolDao candidateSchoolDao;

    @SneakyThrows
    @Override
    public void existsCandidateSchoolById(Integer candSchId) {
        if (CheckUtils.notExistsById(candidateSchoolDao, candSchId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("Candidate's school info"));
    }

    @SneakyThrows
    @Override
    public void checkIfViolatesUk(CandidateSchoolAddDto candSchAddDto) {
        if (candidateSchoolDao.existsBySchool_IdAndDepartment_IdAndCandidate_Id
                (candSchAddDto.getSchoolId(), candSchAddDto.getDepartmentId(), candSchAddDto.getCandidateId()))
            throw new UKViolationException(Msg.UK_CAND_SCH.get());
    }

}
