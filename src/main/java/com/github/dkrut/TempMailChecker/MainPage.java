package com.github.dkrut.TempMailChecker;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Denis Krutikov on 10.05.2020.
 */

public class MainPage {

    SelenideElement currentEmail = $(".input-warp #mail");
    SelenideElement buttonQrCode = $("button[class=\"btn-rds icon-btn btn-l-gary popover-qr\"]");
    SelenideElement buttonCopyTempEmailAddresseeMain = $(".input-box-col.hidden-xs-sm button[data-clipboard-action=copy]");
    SelenideElement buttonCopyTempEmailAddressee = $("#click-to-copy");
    SelenideElement buttonRefresh = $("#click-to-refresh");
    SelenideElement buttonChangeEmail = $("#click-to-change");
    SelenideElement buttonDeleteEmail = $("#click-to-delete");
    SelenideElement login = $(".input-box-warp [name=\"new_mail\"]");
    SelenideElement emailDomain = $(".selection [role=\"combobox\"]");
    ElementsCollection emailDomainList = $$(".select2-results [role=\"tree\"] li"); //4
    SelenideElement buttonSave = $(".input-box-warp #postbut");
    SelenideElement emptyInbox = $(".emptyInboxTitle");

    ElementsCollection inboxList = $$(".inbox-dataList ul li"); // -1
    String inboxSenderName = ".inboxSenderName";
    String inboxSenderEmail = ".inboxSenderEmail";
    String inboxSubject = ".col-box:nth-child(2) .inboxSubject a";
    String inboxAttachment = ".attachment [class=\"viewLink link\"]";
    String inboxOpen = ".m-link-view a";

    private void clickChangeEmail() {
        buttonChangeEmail.click();
    }

    private void clickEmailDomains() {
        emailDomain.click();
    }

    private int domainsCount() {
        clickChangeEmail();
        clickEmailDomains();
        return emailDomainList.size();
    }

    public String[] getDomains() {
        int domainsCount = domainsCount();
        String[] domains = new String[domainsCount];
        for (int i = 0; i < domainsCount; i++) {
            domains[i] = emailDomainList.get(i).getText();
        }
        return domains;
    }

    public void changeEmail(String emailLogin, String emailDomain) {
        clickChangeEmail();
        login.sendKeys(emailLogin);
        for (int i = 1; i <= 4; i++) {
            if (emailDomainList.get(i).getText().equals(emailDomain)) {
                emailDomainList.get(i).click();
                buttonSave.click();
            } else System.out.println(emailDomain + " not found.");
        }
    }
}
