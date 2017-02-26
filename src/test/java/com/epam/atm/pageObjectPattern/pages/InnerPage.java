package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.core.Element;
import com.epam.atm.pageObjectPattern.pages.pageSections.folders.FoldersSection;
import com.epam.atm.pageObjectPattern.pages.pageSections.logout.Logout;
import com.epam.atm.pageObjectPattern.pages.pageSections.toolbar.Toolbar;
import com.epam.atm.pageObjectPattern.utils.JS;

/**
 * Created by Ivan_Matsur on 2/4/2017.
 */
public class InnerPage extends Page {

  private final String EMAIL_ADDRESS_CSS = "div[data-key='view=head-user']";

  protected FoldersSection foldersSection;
  protected Toolbar toolbar;

  @FindBy(css = EMAIL_ADDRESS_CSS)
  private WebElement emailAddress;

  public InnerPage() {
    super();
    this.foldersSection = new FoldersSection();
    this.toolbar = new Toolbar();
  }

  public FoldersSection getFoldersSection() {
    FoldersSection foldersSection = this.foldersSection;
    System.out.println("Got access to Folder Section");
    return foldersSection;
  }

  public Toolbar getToolbar() {
    Toolbar toolbar = this.toolbar;
    System.out.println("Got access to Toolbar");
    return this.toolbar;
  }

  public String getEmailAddress() {
    String userEmailAddress = new Element(emailAddress).getText();
    System.out.println("Got email address: " + userEmailAddress);
    return userEmailAddress;
  }

  public Logout getLogoutPopup() {
    JS.addJSBorderColorToElement(emailAddress);
    JS.addJSClickerByQuerySelector(EMAIL_ADDRESS_CSS);
    System.out.println("Opened popup with Logout button");
    return new Logout();
  }
}
