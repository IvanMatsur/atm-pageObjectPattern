package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.sections.folders.FoldersSection;
import com.epam.atm.pageObjectPattern.sections.logout.Logout;
import com.epam.atm.pageObjectPattern.sections.toolbar.Toolbar;
import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/4/2017.
 */
public class InnerPage extends Page {

  private final String EMAIL_ADDRESS_XPATH = "//div[@class='mail-User-Name']";

  protected FoldersSection foldersSection;
  protected Toolbar toolbar;

  @FindBy(xpath = EMAIL_ADDRESS_XPATH)
  private WebElement emailAddress;

  public InnerPage() {
    super();
    this.foldersSection = new FoldersSection();
    this.toolbar = new Toolbar();
    PageFactory.initElements(YandexMailBoxTest.WEB_DRIVER, this);
  }

  public FoldersSection folders() {
    FoldersSection foldersSection = this.foldersSection;
    System.out.println("Got access to Folder Section");
    return foldersSection;
  }

  public Toolbar toolbar() {
    Toolbar toolbar = this.toolbar;
    System.out.println("Got access to Toolbar");
    return this.toolbar;
  }

  public String emailAddress() {
    String userEmailAddress = emailAddress.getText();
    System.out.println("Got email address: " + userEmailAddress);
    return userEmailAddress;
  }

  public Logout logout() {
    emailAddress.click();
    System.out.println("Opened popup with Logout button");
    return new Logout();
  }

  public boolean isElementPresent(WebElement webElement, String s) {
    boolean result;
    try {
      result = webElement.isDisplayed();
      System.out.println(s + " : TRUE");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println(s + " : FALSE");
    }
    return result;
  }
}
