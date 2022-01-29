package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.DepartmentService;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.core.utilities.results.SuccessDataResult;
import com.emrebalikci.connecthub.dataAccess.abstracts.DepartmentDao;
import com.emrebalikci.connecthub.entities.concretes.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentManager implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Override
    public DataResult<List<Department>> getAll() {
        return new SuccessDataResult<>(departmentDao.findAll());
    }

    @Override
    public Result add(String departmentName) {
        Department savedDepartment = departmentDao.save(new Department(departmentName));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedDepartment);
    }
}
