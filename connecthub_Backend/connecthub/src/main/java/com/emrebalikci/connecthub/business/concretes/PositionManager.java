package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.PositionService;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.core.utilities.results.SuccessDataResult;
import com.emrebalikci.connecthub.dataAccess.abstracts.PositionDao;
import com.emrebalikci.connecthub.entities.concretes.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionManager implements PositionService {

    private final PositionDao positionDao;

    @Override
    public DataResult<List<Position>> getAll() {
        return new SuccessDataResult<>(positionDao.findAll());
    }

    @Override
    public Result add(String positionTitle) {
        Position savedPosition = positionDao.save(new Position(positionTitle));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedPosition);
    }
}
