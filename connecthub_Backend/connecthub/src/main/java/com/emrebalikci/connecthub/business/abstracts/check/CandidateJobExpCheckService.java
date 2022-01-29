package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateJobExperienceAddDto;

import java.util.Map;

public interface CandidateJobExpCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsCandidateJobExpById(Integer candJobExpId);

    void checkIfViolatesUk(CandidateJobExperienceAddDto candJobExpAddDto);

}
