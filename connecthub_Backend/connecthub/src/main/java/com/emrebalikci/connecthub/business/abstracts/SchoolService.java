package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.School;

import java.util.List;

public interface SchoolService {

    DataResult<List<School>> getAll();

    Result add(String schoolName);

}
