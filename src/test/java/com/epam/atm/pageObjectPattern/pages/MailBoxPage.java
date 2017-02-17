package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.models.Email;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class MailBoxPage extends InnerPage {

  private final String FIRST_EMAIL_IN_FOLDER_XPATH = "//div[contains(@class, 'mail-MessagesList')]/div[1]";
  private final String NO_EMAILS_LINK_XPATH = "//a[contains(@class, 'b-messages__placeholder-item__link')]";

  @FindBy(xpath = FIRST_EMAIL_IN_FOLDER_XPATH)
  private WebElement firstEmailInFolder;

  @FindBy(xpath = NO_EMAILS_LINK_XPATH)
  private WebElement noEmailsLink;

  public MailBoxPage() {
    super();
  }

  public EmailPage openFirstEmail(Email email) {
    addJSBorderColorToElement(firstEmailInFolder);
    firstEmailInFolder.click();
    System.out.println("Opened the first email in the folder");
    return new EmailPage(email);
  }

  public boolean isFirstEmailInFolderPresent() {
    return isElementPresent(firstEmailInFolder, "There is an email in the folder");
  }

  public boolean isNoEmailsLinkPresent() {
    addJSBorderColorToElement(noEmailsLink);
    return isElementPresent(noEmailsLink, "No emails link is present");
  }
}