package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.CityService;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.results.*;
import com.emrebalikci.connecthub.dataAccess.abstracts.CityDao;
import com.emrebalikci.connecthub.entities.concretes.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityManager implements CityService {

    private final CityDao cityDao;

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<>(cityDao.findAll());
    }

    @Override
    public Result add(String cityName) {
        City savedCity = cityDao.save(new City(cityName));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedCity);
    }

}
