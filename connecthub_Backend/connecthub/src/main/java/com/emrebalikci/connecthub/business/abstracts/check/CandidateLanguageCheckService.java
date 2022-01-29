package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateLanguageAddDto;

import java.util.Map;

public interface CandidateLanguageCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsCandidateLanguageById(Integer candLangId);

    void checkIfViolatesUk(CandidateLanguageAddDto candLangAddDto);

}
