package com.emrebalikci.connecthub.business.abstracts.check;



import java.util.Map;

public interface SystemEmployeeCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    
    void existsSystemEmployeeById(Integer sysEmplId);
}
