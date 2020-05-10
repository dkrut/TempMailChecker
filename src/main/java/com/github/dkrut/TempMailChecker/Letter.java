package com.github.dkrut.TempMailChecker;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Denis Krutikov on 10.05.2020.
 */

public class Letter {

    SelenideElement backToMain = $(".back-btn-link");
    SelenideElement inboxDate = $(".user-data-time-data");
    SelenideElement inboxFromName = $(".from-name");
    SelenideElement inboxFromEmail = $(".from-email");
    SelenideElement inboxSubject = $(".user-data-subject");
    ElementsCollection inboxContent = $$(".inbox-data-content-intro div");
    ElementsCollection inboxWarpList = $$(".btn-warp-list a.btn-link");
    SelenideElement letterAttachmentsBtn = inboxWarpList.get(1);
    SelenideElement letterDownloadBtn = inboxWarpList.get(2);
    SelenideElement letterDeleteBtn = inboxWarpList.get(3);
    SelenideElement letterSourceBtn = inboxWarpList.get(4);

    ElementsCollection inboxAttachments = $$(".btn-warp-list ul.attachments li"); // -1
    String attachmentName = ".attachmentName";
    String attachmentDownload = "a img";
}
