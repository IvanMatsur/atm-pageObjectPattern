package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.sections.folders.FoldersSection;
import com.epam.atm.pageObjectPattern.sections.logout.Logout;
import com.epam.atm.pageObjectPattern.sections.toolbar.Toolbar;

/**
 * Created by Ivan_Matsur on 2/4/2017.
 */
public class Page {

  private final String EmailAddressXPath = "//div[@class='mail-User-Name']";

  protected final WebDriver webDriver;

  protected FoldersSection foldersSection;
  protected Toolbar toolbar;

  @FindBy(xpath = EmailAddressXPath)
  private WebElement emailAddress;

  public Page(WebDriver webDriver) {
    this.webDriver = webDriver;
    this.foldersSection = new FoldersSection(this.webDriver);
    this.toolbar = new Toolbar(this.webDriver);
    PageFactory.initElements(this.webDriver, this);
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
    return new Logout(webDriver);
  }
}
