package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class MailBoxPage extends Page {

  private final String FirstEmailInFolderXPath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div[1]";
  private final String NoEmailsLinkXPath = "//a[@class='b-messages__placeholder-item__link']";

  @FindBy(xpath = FirstEmailInFolderXPath)
  private WebElement firstEmailInFolder;

  @FindBy(xpath = NoEmailsLinkXPath)
  private WebElement noEmailsLink;

  public MailBoxPage(WebDriver webDriver) {
    super(webDriver);
  }

  public EmailPage openFirstEmail() {
    firstEmailInFolder.click();
    System.out.println("Opened the first email in the folder");
    return new EmailPage(webDriver);
  }

  public boolean isFirstEmailInFolderPresent() {
    boolean result;
    try {
      result = firstEmailInFolder.isDisplayed();
      System.out.println("There is an email in the folder");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println("There is NO emails in the folder");
    }
    return result;
  }

  public boolean isNoEmailsLinkPresent() {
    boolean result;
    try {
      result = noEmailsLink.isDisplayed();
      System.out.println("No emails link is present");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println("No emails link is NOT present");
    }
    return result;
  }
}