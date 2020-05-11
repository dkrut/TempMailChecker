package com.github.dkrut.TempMailChecker;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Denis Krutikov on 10.05.2020.
 */

public class TempMailChecker {
    private MainPage mainPage = new MainPage();
    private Letter letter = new Letter();
    private TextTransfer textTransfer = new TextTransfer();
    private EmailParser emailParser = new EmailParser();

    public TempMailChecker() {
        baseUrl = "https://temp-mail.org";
        screenshots = false;
        savePageSource = false;
        reportsFolder = null;
    }

    public String getTempEmail() {
        open("/");
        mainPage.copyCurrentEmailFromInputWarp();
        return textTransfer.getData();
    }

    public String getEmailLogin(String email) {
        return emailParser.getEmailLogin(email);
    }

    public String getEmailDomain(String email) {
        return emailParser.getEmailDomain(email);
    }

    public void setTempEmail(String emailLogin, String emailDomain) {
        open("/");
        mainPage.changeEmail(emailLogin, emailDomain);
    }
}
