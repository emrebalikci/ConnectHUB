package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.CvCheckService;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.dataAccess.abstracts.CvDao;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CvCheckManager extends BaseCheckManager implements CvCheckService {

    private final CvDao cvDao;

    @SneakyThrows
    @Override
    public void existsCvById(Integer cvId) {
        if (CheckUtils.notExistsById(cvDao, cvId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("CV"));
    }

    @Override
    public void notExistsCvByTitleAndCandidateId(String title, Integer candId) {
        if (cvDao.existsByTitleAndCandidate_Id(title, candId))
            errors.put("title", Msg.USED.get("Title"));
    }

}
