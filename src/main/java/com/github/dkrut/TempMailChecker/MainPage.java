package com.github.dkrut.TempMailChecker;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

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
    SelenideElement emailDomain = $(".select2-selection__arrow");
    ElementsCollection emailDomainList = $$(".select2-results [role=\"tree\"] li"); //4
    SelenideElement buttonSave = $(".input-box-warp #postbut");
    SelenideElement emptyInbox = $("[class=\"inbox-empty\"] .emptyInboxTitle");
    SelenideElement emailChangedMessage = $(".growl-message");

    ElementsCollection inboxList = $$(".inbox-dataList ul li"); // -1
    String inboxSenderName = ".inboxSenderName";
    String inboxSenderEmail = ".inboxSenderEmail";
    String inboxSubject = ".col-box:nth-child(2) .inboxSubject a";
    String inboxAttachment = ".attachment [class=\"viewLink link\"]";
    String inboxOpen = ".m-link-view a";

    public void copyCurrentEmailFromInputWarp() {
        currentEmail.click();
        currentEmail.sendKeys(Keys.CONTROL, "c");
    }

    public void clickButtonQrCode() {
        buttonQrCode.click();
    }

    public void clickButtonCopyTempEmailAddresseeMain() {
        buttonCopyTempEmailAddresseeMain.click();
    }

    public void clickButtonCopyTempEmailAddressee() {
        buttonCopyTempEmailAddressee.click();
    }

    public void clickButtonRefresh() {
        buttonRefresh.click();
    }

    public void clickButtonDeleteEmail() {
        buttonDeleteEmail.click();
    }

    private void clickButtonChangeEmail() {
        buttonChangeEmail.click();
    }

    private void clickEmailDomainsArray() {
        emailDomain.click();
    }

    public void clickButtonSaveEmail() {
        buttonSave.click();
    }

    public boolean emptyInbox() {
        if (emptyInbox.exists()) sleep(3000);
        return emptyInbox.exists();
    }

    public void setEmailLogin(String emailLogin) {
        login.sendKeys(emailLogin);
    }

    private String randomString(int stringLength) {
        String symbols = "abcdefghijklmnopqrstuvwxyz" + "0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            sb.append(symbols.charAt(random.nextInt(symbols.length()-1)));
        }
         return sb.toString();
    }

    private String randomString() {
        return randomString(10);
    }

    public void setEmailLoginRandom() {
        login.sendKeys(randomString());
    }

    public void setEmailLoginRandom(int emailLoginLength) {
        login.sendKeys(randomString(emailLoginLength));
    }

    private int domainsCount() {
        clickButtonChangeEmail();
        clickEmailDomainsArray();
        System.out.println("Domains count: " + emailDomainList.size());
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

    public String getEmailDomainName(int index) {
        return emailDomainList.get(index).getText();
    }

    public void chooseEmailDomain(int index) {
        emailDomainList.get(index).click();
        System.out.println();
    }

    public void setEmailDomain(String emailDomain) {
        String[] domains = getDomains();
        for (int i = 0; i < domains.length; i++) {
            if (domains[i].equals(emailDomain)) {
                System.out.println("Check #" + (i+1) + ". Email domain found: " + domains[i]);
                chooseEmailDomain(i);
                break;
            } else System.out.println("Check #" + (i+1) + ". Email domain not found: " + emailDomain);
        }
    }

    public void setEmailDomainRandom() {
        String[] domains = getDomains();
        chooseEmailDomain((int)(Math.random() * domains.length));
    }

    public void changeEmail(String emailLogin, String emailDomain) {
        clickButtonChangeEmail();
        setEmailDomain(emailDomain);
        setEmailLogin(emailLogin);
        clickButtonSaveEmail();
        checkMessageEmailChanged();
    }

    public void checkMessageEmailChanged() {
        emailChangedMessage.shouldBe(Condition.visible);
    }

    public void changeEmailRandomDomain(String emailLogin) {
        clickButtonChangeEmail();
        setEmailDomainRandom();
        setEmailLogin(emailLogin);
        clickButtonSaveEmail();
        checkMessageEmailChanged();
    }

    public void changeEmailRandomLogin(String emailDomain) {
        clickButtonChangeEmail();
        setEmailDomain(emailDomain);
        setEmailLoginRandom();
        clickButtonSaveEmail();
        checkMessageEmailChanged();
    }

    public void changeEmailRandomLogin(String emailDomain, int emailLoginLength) {
        clickButtonChangeEmail();
        setEmailDomain(emailDomain);
        setEmailLoginRandom(emailLoginLength);
        clickButtonSaveEmail();
        checkMessageEmailChanged();
    }

    public void changeEmailRandomLoginDomain() {
        clickButtonChangeEmail();
        setEmailDomainRandom();
        setEmailLoginRandom();
        clickButtonSaveEmail();
        checkMessageEmailChanged();
    }

    public int inboxSize() {
        return (inboxList.size()-1);
    }

    public int inboxCount() {
        if (emptyInbox()) return 0;
        else return inboxSize();
    }

    public SelenideElement getInboxInstance(int index) {
        return inboxList.get(index);
    }

    public String getInboxSenderName(int index) {
        return getInboxInstance(index).find(By.cssSelector(inboxSenderName)).getText();
    }

    public String getInboxSenderEmail(int index) {
        return getInboxInstance(index).find(By.cssSelector(inboxSenderEmail)).getText();
    }

    public String getInboxSubject(int index) {
        return getInboxInstance(index).find(By.cssSelector(inboxSubject)).getText();
    }

    public boolean getInboxAttachment(int index) {
        return getInboxInstance(index).find(By.cssSelector(inboxAttachment)).exists();
    }

    public void clickOpenInbox(int index) {
        getInboxInstance(index).find(By.cssSelector(inboxOpen)).click();
    }

    public int getInboxIndexBySenderName(String senderName) {
        int index = 0;
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxSenderName(i).equals(senderName)) {
                index = i;
                break;
            } else index = 0;
        } return index;
    }

    public int getInboxIndexBySenderEmail(String senderEmail) {
        int index = 0;
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxSenderEmail(i).equals(senderEmail)) {
                index = i;
                break;
            } else index = 0;
        } return index;
    }

    public int getInboxIndexBySubject(String subject) {
        int index = 0;
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxSubject(i).equals(subject)) {
                index = i;
                break;
            } else index = 0;
        } return index;
    }

    public int getInboxIndexByAttachment() {
        int index = 0;
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxAttachment(i)) {
                index = i;
                break;
            } else index = 0;
        } return index;
    }


    public ArrayList<Integer> getInboxIndexBySenderNameA(String senderName) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxSenderName(i).equals(senderName)) {
                index.add(i);
            }
        } return index;
    }

    public ArrayList<Integer> getInboxIndexBySenderEmailA(String senderEmail) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxSenderName(i).equals(senderEmail)) {
                index.add(i);
            }
        } return index;
    }

    public ArrayList<Integer> getInboxIndexBySubjectA(String subject) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxSenderName(i).equals(subject)) {
                index.add(i);
            }
        } return index;
    }

    public ArrayList<Integer> getInboxIndexByAttachmentA() {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i <= inboxCount(); i++) {
            if (getInboxAttachment(i)) {
                index.add(i);
            }
        } return index;
    }
}
