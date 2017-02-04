package com.epam.atm.pageObjectPattern.sections.folders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.pages.MailBoxPage;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class Folder {

  public enum Type {
      INBOX, SENT, TRASH, SPAM, DRAFT
  }

  private WebDriver webDriver;
  private Type type;

  private final String InboxFolderXPath = "//a[@href='#inbox']";
  private final String SentFolderXPath = "//a[@href='#sent']";
  private final String TrashFolderXPath = "//a[@href='#trash']";
  private final String SpamFolderXPath = "//a[@href='#spam']";
  private final String DraftFolderXPath = "//a[@href='#draft']";
  private final String EmailsNumberInXPath = "//span[@class='mail-NestedList-Item-Info-Extras']";

  @FindBy(xpath = InboxFolderXPath)
  private WebElement inboxFolder;

  @FindBy(xpath = SentFolderXPath)
  private WebElement sentFolder;

  @FindBy(xpath = TrashFolderXPath)
  private WebElement trashFolder;

  @FindBy(xpath = SpamFolderXPath)
  private WebElement spamFolder;

  @FindBy(xpath = DraftFolderXPath)
  private WebElement draftFolder;

  @FindBy(xpath = InboxFolderXPath + EmailsNumberInXPath)
  private WebElement emailsNumberInInbox;

  @FindBy(xpath = SentFolderXPath + EmailsNumberInXPath)
  private WebElement emailsNumberInSent;

  @FindBy(xpath = TrashFolderXPath + EmailsNumberInXPath)
  private WebElement emailsNumberInTrash;

  @FindBy(xpath = SpamFolderXPath + EmailsNumberInXPath)
  private WebElement emailsNumberInSpam;

  @FindBy(xpath = DraftFolderXPath + EmailsNumberInXPath)
  private WebElement emailsNumberInDraft;

  public Folder(WebDriver webDriver, Type type) {
    this.webDriver = webDriver;
    this.type = type;
    PageFactory.initElements(this.webDriver, this);
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
    return new MailBoxPage(webDriver);
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