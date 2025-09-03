package com.github.countrybros.application.abstractions;

import com.github.countrybros.model.utils.Email;

public interface IEmailService {

    /**
     * Sends an email
     *
     * @param email     The email to send.
     */
    public void sendEmail(Email email);
}
