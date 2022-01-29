package com.emrebalikci.connecthub.core.business.abstracts;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckService;
import com.emrebalikci.connecthub.core.entities.User;

import java.util.Map;

public interface UserCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsUserById(Integer userId);

    void notExistsUserByEmail(String email, User user);

    void existsUserByIdAndPW(String password, int userId);
}
