package com.github.dkrut.TempMailChecker;

/**
 * Created by Denis Krutikov on 10.05.2020.
 */

public class EmailParser {
    private String email;

    public EmailParser(String email) {
        this.email = email;
    }

    private String[] getEmailParsed() {
        return email.split("@");
    }

    public String getEmailLogin(){
        return getEmailParsed()[0];
    }

    public String getEmailDomain(){
        return "@" + getEmailParsed()[1];
    }
}