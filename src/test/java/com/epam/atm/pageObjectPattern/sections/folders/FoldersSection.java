package com.epam.atm.pageObjectPattern.sections.folders;

import org.openqa.selenium.WebDriver;

import com.epam.atm.pageObjectPattern.pages.MailBoxPage;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class FoldersSection {

  private WebDriver webDriver;

  private Folder inboxFolder;
  private Folder sentFolder;
  private Folder trashFolder;
  private Folder spamFolder;
  private Folder draftFolder;

  public FoldersSection(WebDriver webDriver) {
    this.webDriver = webDriver;
    inboxFolder = new Folder(webDriver, Folder.Type.INBOX);
    sentFolder = new Folder(webDriver, Folder.Type.SENT);
    trashFolder = new Folder(webDriver, Folder.Type.TRASH);
    spamFolder = new Folder(webDriver, Folder.Type.SPAM);
    draftFolder = new Folder(webDriver, Folder.Type.DRAFT);
  }

  public MailBoxPage openDefaultFolder() {
    openInboxFolder();
    return new MailBoxPage(webDriver);
  }

  public MailBoxPage openInboxFolder() {
    inboxFolder.open();
    return new MailBoxPage(webDriver);
  }

  public MailBoxPage openSentFolder() {
    sentFolder.open();
    return new MailBoxPage(webDriver);
  }

  public MailBoxPage openTrashFolder() {
    trashFolder.open();
    return new MailBoxPage(webDriver);
  }

  public MailBoxPage openSpamFolder() {
    spamFolder.open();
    return new MailBoxPage(webDriver);
  }

  public MailBoxPage openDraftFolder() {
    draftFolder.open();
    return new MailBoxPage(webDriver);
  }

  public String getEmailsNumberInSentFolder() {
    return sentFolder.getEmailsNumberIn();
  }

  public String getEmailsNumberInDraftFolder() {
    return draftFolder.getEmailsNumberIn();
  }
}