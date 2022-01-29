package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.LanguageService;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.core.utilities.results.SuccessDataResult;
import com.emrebalikci.connecthub.dataAccess.abstracts.LanguageDao;
import com.emrebalikci.connecthub.entities.concretes.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageManager implements LanguageService {

    private final LanguageDao languageDao;

    @Override
    public DataResult<List<Language>> getAll() {
        return new SuccessDataResult<>(languageDao.findAll());
    }

    @Override
    public Result add(String languageName) {
        Language savedLanguage = languageDao.save(new Language(languageName));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedLanguage);
    }

}
