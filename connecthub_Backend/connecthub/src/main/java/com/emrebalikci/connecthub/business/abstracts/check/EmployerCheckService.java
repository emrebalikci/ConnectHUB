package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.Employer;
import com.emrebalikci.connecthub.entities.concretes.EmployerUpdate;

import java.util.Map;

public interface EmployerCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsEmployerById(Integer employerId);

    void notExistsEmployerByCompanyName(String companyName, Employer employer);

    void companyNameIsNotRequested(String companyName, Employer employer);

    void notExistsEmployerByWebsite(String website, Employer employer);

    void emailIsNotRequested(String email, Employer employer);

    void websiteIsNotRequested(String website, Employer employer);

    void emailWebsiteHaveTheSameDomain(String email, String website);

    void notTheSameEmailAndWebsite(String email, String website, EmployerUpdate employerUpdate);

    boolean changed(Employer employer);

}
