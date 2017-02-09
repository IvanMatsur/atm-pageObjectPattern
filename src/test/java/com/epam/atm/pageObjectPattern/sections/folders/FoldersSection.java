package com.epam.atm.pageObjectPattern.sections.folders;

import com.epam.atm.pageObjectPattern.pages.MailBoxPage;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class FoldersSection {

  private Folder inboxFolder;
  private Folder sentFolder;
  private Folder trashFolder;
  private Folder spamFolder;
  private Folder draftFolder;

  public FoldersSection() {
    inboxFolder = new Folder(Folder.Type.INBOX);
    sentFolder = new Folder(Folder.Type.SENT);
    trashFolder = new Folder(Folder.Type.TRASH);
    spamFolder = new Folder(Folder.Type.SPAM);
    draftFolder = new Folder(Folder.Type.DRAFT);
  }

  public MailBoxPage openDefaultFolder() {
    openInboxFolder();
    return new MailBoxPage();
  }

  public MailBoxPage openInboxFolder() {
    inboxFolder.open();
    return new MailBoxPage();
  }

  public MailBoxPage openSentFolder() {
    sentFolder.open();
    return new MailBoxPage();
  }

  public MailBoxPage openTrashFolder() {
    trashFolder.open();
    return new MailBoxPage();
  }

  public MailBoxPage openSpamFolder() {
    spamFolder.open();
    return new MailBoxPage();
  }

  public MailBoxPage openDraftFolder() {
    draftFolder.open();
    return new MailBoxPage();
  }

  public String getEmailsNumberInSentFolder() {
    return sentFolder.getEmailsNumberIn();
  }

  public String getEmailsNumberInDraftFolder() {
    return draftFolder.getEmailsNumberIn();
  }
}