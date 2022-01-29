package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Employer;
import com.emrebalikci.connecthub.entities.concretes.dtos.EmployerAddDto;

import java.util.List;

public interface EmployerService {

    DataResult<Boolean> existsByCompanyName(String companyName);

    DataResult<Boolean> existsByWebsite(String website);

    DataResult<Employer> getById(int emplId);

    DataResult<Employer> getByEmailAndPW(String email, String password);

    DataResult<List<Employer>> getAll();

    DataResult<List<Employer>> getVerified();

    DataResult<List<Employer>> getUnverified();

    Result add(EmployerAddDto employerAddDto);

    Result updateCompanyName(String companyName, int emplId);

    Result updateEmailAndWebsite(String website, String email, int emplId);

    Result updatePhoneNumber(String phoneNumber, int emplId);

    Result applyChanges(int emplId);

    Result updateVerification(boolean systemVerificationStatus, int emplId);

}
