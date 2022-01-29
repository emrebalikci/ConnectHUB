package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Department;

import java.util.List;

public interface DepartmentService {

    DataResult<List<Department>> getAll();

    Result add(String departmentName);

}
