package com.epam.atm.pageObjectPattern.sections.folders;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.pages.MailBoxPage;
import com.epam.atm.pageObjectPattern.pages.Page;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class Folder extends Page {

  public enum Type {
      INBOX, SENT, TRASH, SPAM, DRAFT
  }

  private Type type;

  private final String INBOX_FOLDER_XPATH = "//a[@href='#inbox']";
  private final String SENT_FOLDER_XPATH = "//a[@href='#sent']";
  private final String TRASH_FOLDER_XPATH = "//a[@href='#trash']";
  private final String SPAM_FOLDER_XPATH = "//a[@href='#spam']";
  private final String DRAFT_FOLDER_XPATH = "//a[@href='#draft']";
  private final String EMAILS_NUMBER_IN_XPATH = "//span[contains(@class, 'mail-NestedList-Item-Info-Extras')]";

  @FindBy(xpath = INBOX_FOLDER_XPATH)
  private WebElement inboxFolder;

  @FindBy(xpath = SENT_FOLDER_XPATH)
  private WebElement sentFolder;

  @FindBy(xpath = TRASH_FOLDER_XPATH)
  private WebElement trashFolder;

  @FindBy(xpath = SPAM_FOLDER_XPATH)
  private WebElement spamFolder;

  @FindBy(xpath = DRAFT_FOLDER_XPATH)
  private WebElement draftFolder;

  @FindBy(xpath = INBOX_FOLDER_XPATH + EMAILS_NUMBER_IN_XPATH)
  private WebElement emailsNumberInInbox;

  @FindBy(xpath = SENT_FOLDER_XPATH + EMAILS_NUMBER_IN_XPATH)
  private WebElement emailsNumberInSent;

  @FindBy(xpath = TRASH_FOLDER_XPATH + EMAILS_NUMBER_IN_XPATH)
  private WebElement emailsNumberInTrash;

  @FindBy(xpath = SPAM_FOLDER_XPATH + EMAILS_NUMBER_IN_XPATH)
  private WebElement emailsNumberInSpam;

  @FindBy(xpath = DRAFT_FOLDER_XPATH + EMAILS_NUMBER_IN_XPATH)
  private WebElement emailsNumberInDraft;

  public Folder(Type type) {
    super();
    this.type = type;
  }

  public MailBoxPage open() {
    WebElement folder = null;
    switch (type) {
    case INBOX:
      folder = inboxFolder;
      break;
    case SENT:
      folder = sentFolder;
      break;
    case TRASH:
      folder = trashFolder;
      break;
    case SPAM:
      folder = spamFolder;
      break;
    case DRAFT:
      folder = draftFolder;
    }

    addJSBorderColorToElement(folder);
    folder.click();
    System.out.println("Opened " + type.toString() + " folder");
    return new MailBoxPage();
  }

  public String getEmailsNumberIn() {
    WebElement emailsNumberInFolder = null;
    switch (type) {
    case INBOX:
      emailsNumberInFolder = emailsNumberInInbox;
      break;
    case SENT:
      emailsNumberInFolder = emailsNumberInSent;
      break;
    case TRASH:
      emailsNumberInFolder = emailsNumberInTrash;
      break;
    case SPAM:
      emailsNumberInFolder = emailsNumberInSpam;
      break;
    case DRAFT:
      emailsNumberInFolder = emailsNumberInDraft;
    }

    addJSBorderColorToElement(emailsNumberInFolder);
    String emailsInFolder = emailsNumberInFolder.getText();
    System.out.println("Got emails number in the " + type.toString() + " folder");
    return emailsInFolder;
  }
}