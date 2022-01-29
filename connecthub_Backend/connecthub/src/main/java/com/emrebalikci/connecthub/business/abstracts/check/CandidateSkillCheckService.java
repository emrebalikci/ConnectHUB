package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSkillAddDto;

import java.util.Map;

public interface CandidateSkillCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsCandidateSkillById(Integer candSkillId);

    void checkIfViolatesUk(CandidateSkillAddDto candSkillAddDto);

}
