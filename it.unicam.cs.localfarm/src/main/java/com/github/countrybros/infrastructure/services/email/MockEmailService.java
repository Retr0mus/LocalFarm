package com.github.countrybros.infrastructure.services.email;

import com.github.countrybros.application.abstractions.IEmailService;
import com.github.countrybros.model.utils.Email;

public class MockEmailService implements IEmailService {

    /**
     * Sends an email
     *
     * @param email The email to send.
     */
    @Override
    public void sendEmail(Email email) {

    }
}
