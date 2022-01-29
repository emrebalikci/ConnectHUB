package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.City;

import java.util.List;

public interface CityService {

    DataResult<List<City>> getAll();

    Result add(String cityName);

}
