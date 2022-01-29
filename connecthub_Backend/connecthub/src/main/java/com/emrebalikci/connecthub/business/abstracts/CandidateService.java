package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Candidate;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateAddDto;

import java.util.List;

public interface CandidateService {

    DataResult<List<Candidate>> getAll();

    DataResult<Boolean> existsByNatId(String nationalityId);

    DataResult<Candidate> getById(int candId);

    DataResult<Candidate> getByEmailAndPW(String email, String password);

    Result add(CandidateAddDto candidateAddDto);

    Result updateGithubAccount(String githubAccountLink, int candId);

    Result updateLinkedinAccount(String linkedinAccountLink, int candId);

    Result updateFavoriteJobAdverts(int jobAdvertisementId, int candId, String type);

}
