package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.CandidateSkill;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSkillAddDto;

import java.util.List;

public interface CandidateSkillService {

    DataResult<List<CandidateSkill>> getAll();

    Result add(CandidateSkillAddDto candidateSkillAddDto);

    Result addMultiple(List<CandidateSkillAddDto> candSkillAddDtoList);

    Result deleteById(int candSkillId);

}
