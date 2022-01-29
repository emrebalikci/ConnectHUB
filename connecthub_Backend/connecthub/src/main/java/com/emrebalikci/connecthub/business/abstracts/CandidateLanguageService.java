package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.CandidateLanguage;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateLanguageAddDto;

import java.util.List;

public interface CandidateLanguageService {

    DataResult<List<CandidateLanguage>> getAll();

    Result add(CandidateLanguageAddDto candidateLanguageAddDto);

    Result deleteById(int candLangId);

    Result updateLanguage(short languageId, int candLangId);

    Result updateLangLevel(String languageLevel, int candLangId);

}
