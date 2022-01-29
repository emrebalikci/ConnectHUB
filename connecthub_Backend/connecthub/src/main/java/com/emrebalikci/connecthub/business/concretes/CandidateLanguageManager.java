package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.CandidateLanguageService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateCheckService;
import com.emrebalikci.connecthub.business.abstracts.check.CandidateLanguageCheckService;
import com.emrebalikci.connecthub.core.business.abstracts.CheckService;
import com.emrebalikci.connecthub.core.entities.ApiError;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.Utils;
import com.emrebalikci.connecthub.core.utilities.results.*;
import com.emrebalikci.connecthub.dataAccess.abstracts.CandidateLanguageDao;
import com.emrebalikci.connecthub.dataAccess.abstracts.LanguageDao;
import com.emrebalikci.connecthub.entities.concretes.CandidateLanguage;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateLanguageAddDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateLanguageManager implements CandidateLanguageService {

    private final CandidateLanguageDao candidateLanguageDao;
    private final LanguageDao languageDao;
    private final CandidateCheckService candidateCheck;
    private final CandidateLanguageCheckService candidateLangCheck;
    private final CheckService check;
    private final ModelMapper modelMapper;

    @Override
    public DataResult<List<CandidateLanguage>> getAll() {
        return new SuccessDataResult<>(candidateLanguageDao.findAll());
    }

    @Override
    public Result add(CandidateLanguageAddDto candLangAddDto) {
        candidateCheck.existsCandidateById(candLangAddDto.getCandidateId());
        check.existsLanguageById(candLangAddDto.getLanguageId());
        candidateLangCheck.checkIfViolatesUk(candLangAddDto);
        CandidateLanguage candidateLanguage = modelMapper.map(candLangAddDto, CandidateLanguage.class);
        CandidateLanguage savedCandLang = candidateLanguageDao.save(candidateLanguage);
        savedCandLang.setLanguage(languageDao.getById(savedCandLang.getLanguage().getId()));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedCandLang);
    }

    @Override
    public Result deleteById(int candLangId) {
        candidateLangCheck.existsCandidateLanguageById(candLangId);
        candidateLanguageDao.deleteById(candLangId);
        return new SuccessResult(Msg.DELETED.get());
    }

    @Override
    public Result updateLanguage(short languageId, int candLangId) {
        candidateLangCheck.existsCandidateLanguageById(candLangId);
        check.existsLanguageById(languageId);
        CandidateLanguage candLang = candidateLanguageDao.getById(candLangId);
        check.notTheSame(candLang.getLanguage().getId(), languageId, "Language");
        candLang.setLanguage(languageDao.getById(languageId));
        return execLastUpdAct(candLang);
    }

    @Override
    public Result updateLangLevel(String languageLevel, int candLangId) {
        candidateLangCheck.existsCandidateLanguageById(candLangId);
        CandidateLanguage candLang = candidateLanguageDao.getById(candLangId);
        check.notTheSame(candLang.getLanguageLevel(), languageLevel, "Language level");
        candLang.setLanguageLevel(languageLevel);
        return execLastUpdAct(candLang);
    }

    private Result execLastUpdAct(CandidateLanguage candidateLanguage) {
        ErrorDataResult<ApiError> errors = Utils.getErrorsIfExist(check);
        if (errors != null) return errors;
        CandidateLanguage savedCandLang = candidateLanguageDao.save(candidateLanguage);
        return new SuccessDataResult<>(Msg.UPDATED.get(), savedCandLang);
    }

}
