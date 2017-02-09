package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class MailBoxPage extends InnerPage {

  private final String FirstEmailInFolderXPath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div[1]";
  private final String NoEmailsLinkXPath = "//a[@class='b-messages__placeholder-item__link']";

  @FindBy(xpath = FirstEmailInFolderXPath)
  private WebElement firstEmailInFolder;

  @FindBy(xpath = NoEmailsLinkXPath)
  private WebElement noEmailsLink;

  public MailBoxPage() {
    super();
  }

  public EmailPage openFirstEmail() {
    firstEmailInFolder.click();
    System.out.println("Opened the first email in the folder");
    return new EmailPage();
  }

  public boolean isFirstEmailInFolderPresent() {
    return isElementPresent(firstEmailInFolder, "There is an email in the folder");
  }

  public boolean isNoEmailsLinkPresent() {
    return isElementPresent(noEmailsLink, "No emails link is present");
  }
}