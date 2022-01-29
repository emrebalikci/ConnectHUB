package com.emrebalikci.connecthub.core.business.abstracts;

import org.springframework.stereotype.Component;

@Component
public interface EmailService {

    void sendVerificationMail(String email);

}
