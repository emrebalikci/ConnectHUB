package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Position;

import java.util.List;

public interface PositionService {

    DataResult<List<Position>> getAll();

    Result add(String positionTitle);

}
