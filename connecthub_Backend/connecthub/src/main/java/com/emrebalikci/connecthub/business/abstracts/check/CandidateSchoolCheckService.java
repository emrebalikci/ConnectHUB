package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSchoolAddDto;

import java.util.Map;

public interface CandidateSchoolCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsCandidateSchoolById(Integer candSchId);

    void checkIfViolatesUk(CandidateSchoolAddDto candSchAddDto);

}
