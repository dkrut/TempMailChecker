package com.github.dkrut.TempMailChecker;


import com.codeborne.selenide.Condition;

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
        reopenBrowserOnFail = true;
    }

    private void openMain() {
        open("/");
        try {
            mainPage.currentEmailLoaded.shouldBe(Condition.exist);
        } catch (AssertionError e) {
            System.out.println("Server error. Temp email didn't load.");
            System.exit(1);
        }
    }

    /**
     * Get current temp email
     */
    public String getTempEmail() {
        openMain();
        mainPage.copyCurrentEmailFromInputWarp();
        return textTransfer.getData();
    }

    public String getTempEmailByButtonMain() {
        openMain();
        mainPage.clickButtonCopyTempEmailAddresseeMain();
        return textTransfer.getData();
    }

    public String getTempEmailByButton() {
        openMain();
        mainPage.clickButtonCopyTempEmailAddressee();
        return textTransfer.getData();
    }

    /**
     * Email parsing
     */
    public String getEmailLogin(String email) {
        return emailParser.getEmailLogin(email);
    }

    public String getEmailDomain(String email) {
        return emailParser.getEmailDomain(email);
    }

    /**
     * Set new Email
     */
    public void setTempEmail(String email) {
        openMain();
        if (email.contains("@") && email.endsWith(".com")) {
            mainPage.changeEmail(getEmailLogin(email), getEmailDomain(email));
        } else System.out.println("Email not valid");
    }

    /**
     * Inbox latest
     */
    public String getInboxSenderNameLatest() {
        return mainPage.getInboxSenderNameLatest();
    }

    public String getInboxSenderEmailLatest() {
        return mainPage.getInboxSenderEmailLatest();
    }

    public String getInboxSubjectLatest() {
        return mainPage.getInboxSubjectLatest();
    }

    public Boolean getInboxAttachmentLatest() {
        return mainPage.getInboxAttachmentLatest();
    }

    public void clickOpenInboxLatest() {
        mainPage.clickOpenInboxLatest();
    }
}