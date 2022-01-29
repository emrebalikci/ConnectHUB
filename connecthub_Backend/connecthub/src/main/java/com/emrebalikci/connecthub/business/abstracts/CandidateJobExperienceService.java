package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.CandidateJobExperience;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateJobExperienceAddDto;

import java.util.List;

public interface CandidateJobExperienceService {

    DataResult<List<CandidateJobExperience>> getAll();

    DataResult<List<CandidateJobExperience>> getByQuitYear(Short sortDirection);

    Result add(CandidateJobExperienceAddDto candidateJobExperienceAddDto);

    Result deleteById(int candJobExpId);

    Result updateWorkPlace(String workPlace, int candJobExpId);

    Result updatePosition(short positionId, int candJobExpId);

    Result updateStartYear(short startYear, int candJobExpId);

    Result updateQuitYear(Short quitYear, int candJobExpId);

}
