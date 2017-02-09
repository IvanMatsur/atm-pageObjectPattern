package com.epam.atm.pageObjectPattern.sections.folders;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.pages.MailBoxPage;
import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class Folder {

  public enum Type {
      INBOX, SENT, TRASH, SPAM, DRAFT
  }

  private Type type;

  private final String INBOX_FOLDER_XPATH = "//a[@href='#inbox']";
  private final String SENT_FOLDER_XPATH = "//a[@href='#sent']";
  private final String TRASH_FOLDER_XPATH = "//a[@href='#trash']";
  private final String SPAM_FOLDER_XPATH = "//a[@href='#spam']";
  private final String DRAFT_FOLDER_XPATH = "//a[@href='#draft']";
  private final String EMAILS_NUMBER_IN_XPATH = "//span[@class='mail-NestedList-Item-Info-Extras']";

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
    this.type = type;
    PageFactory.initElements(YandexMailBoxTest.WEB_DRIVER, this);
  }

  public MailBoxPage open() {
    switch (type) {
    case INBOX:
      inboxFolder.click();
      break;
    case SENT:
      sentFolder.click();
      break;
    case TRASH:
      trashFolder.click();
      break;
    case SPAM:
      spamFolder.click();
      break;
    case DRAFT:
      draftFolder.click();
    }
    System.out.println("Opened " + type.toString() + " folder");
    return new MailBoxPage();
  }

  public String getEmailsNumberIn() {
    String emailsInFolder = null;
    switch (type) {
    case INBOX:
      emailsInFolder = emailsNumberInInbox.getText();
      break;
    case SENT:
      emailsInFolder = emailsNumberInSent.getText();
      break;
    case TRASH:
      emailsInFolder = emailsNumberInTrash.getText();
      break;
    case SPAM:
      emailsInFolder = emailsNumberInSpam.getText();
      break;
    case DRAFT:
      emailsInFolder = emailsNumberInDraft.getText();
    }
    System.out.println("Got emails number in the " + type.toString() + " folder");
    return emailsInFolder;
  }
}