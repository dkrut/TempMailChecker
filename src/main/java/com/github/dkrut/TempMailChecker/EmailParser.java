package com.github.dkrut.TempMailChecker;

/**
 * Created by Denis Krutikov on 10.05.2020.
 */

public class EmailParser {

    private String[] getEmailParsed(String email) {
        return email.split("@");
    }

    public String getEmailLogin(String email) {
        return getEmailParsed(email)[0];
    }

    public String getEmailDomain(String email) {
        return "@" + getEmailParsed(email)[1];
    }
}