package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateAddDto;

import java.util.Map;

public interface CandidateCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsCandidateById(Integer candId);

    void notExistsCandidateByNatId(String nationalityId);

    void realPerson(CandidateAddDto candidateAddDto);

}
