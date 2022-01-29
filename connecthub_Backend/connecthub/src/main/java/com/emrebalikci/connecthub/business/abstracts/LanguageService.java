package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Language;

import java.util.List;

public interface LanguageService {

    DataResult<List<Language>> getAll();

    Result add(String languageName);

}
