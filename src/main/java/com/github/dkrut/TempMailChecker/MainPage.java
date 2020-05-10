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
    ElementsCollection emailDomainList = $$(".select2-results [role=\"tree\"]");
    SelenideElement buttonSave = $(".input-box-warp #postbut");
    SelenideElement emptyInbox = $(".emptyInboxTitle");

    ElementsCollection inboxList = $$(".inbox-dataList ul li"); // -1
    String inboxSenderName = ".inboxSenderName";
    String inboxSenderEmail = ".inboxSenderEmail";
    String inboxSubject = ".col-box:nth-child(2) .inboxSubject a";
    String inboxAttachment = ".attachment [class=\"viewLink link\"]";
    String inboxOpen = ".m-link-view a";
}
