package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.SchoolService;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.core.utilities.results.SuccessDataResult;
import com.emrebalikci.connecthub.dataAccess.abstracts.SchoolDao;
import com.emrebalikci.connecthub.entities.concretes.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolManager implements SchoolService {

    private final SchoolDao schoolDao;

    @Override
    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<>(schoolDao.findAll());
    }

    @Override
    public Result add(String schoolName) {
        School savedSchool = schoolDao.save(new School(schoolName));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedSchool);
    }
}
