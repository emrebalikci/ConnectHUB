package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.SystemEmployee;
import com.emrebalikci.connecthub.entities.concretes.dtos.SystemEmployeeAddDto;

import java.util.List;

public interface SystemEmployeeService {

    DataResult<List<SystemEmployee>> getAll();

    DataResult<SystemEmployee> getById(int sysEmplId);

    DataResult<SystemEmployee> getByEmailAndPW(String email, String password);

    Result add(SystemEmployeeAddDto systemEmployeeAddDto);

    Result updateFirstName(String firstName, int sysEmplId);

    Result updateLastName(String lastName, int sysEmplId);

}
