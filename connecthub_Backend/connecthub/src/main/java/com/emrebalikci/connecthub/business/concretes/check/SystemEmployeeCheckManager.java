package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.SystemEmployeeCheckService;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.dataAccess.abstracts.SystemEmployeeDao;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemEmployeeCheckManager extends BaseCheckManager implements SystemEmployeeCheckService {

    private final SystemEmployeeDao systemEmployeeDao;

    @SneakyThrows
    @Override
    public void existsSystemEmployeeById(Integer sysEmplId) {
        if (CheckUtils.notExistsById(systemEmployeeDao, sysEmplId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("System Employee"));
    }

}
