package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateCheckService;
import com.emrebalikci.connecthub.core.adapters.abstracts.MernisService;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateDao;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateAddDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateCheckManager extends BaseCheckManager implements CandidateCheckService {

    private final CandidateDao candidateDao;
    private final MernisService mernisService;

    @SneakyThrows
    @Override
    public void existsCandidateById(Integer candId) {
        if (CheckUtils.notExistsById(candidateDao, candId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("Candidate"));
    }

    @Override
    public void notExistsCandidateByNatId(String nationalityId) {
        if (candidateDao.existsByNationalityId(nationalityId))
            errors.put("nationalityId", Msg.IS_IN_USE.get("Nationality ID"));
    }

    @Override
    public void realPerson(CandidateAddDto candidateAddDto) {
        if (!mernisService.realPerson(candidateAddDto))
            errors.put("mernis", Msg.MERNIS_FAIL.get());
    }

}
