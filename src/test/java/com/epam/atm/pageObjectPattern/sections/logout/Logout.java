package com.epam.atm.pageObjectPattern.sections.logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.pages.LoginPage;

/**
 * Created by Ivan_Matsur on 2/3/2017.
 */
public class Logout {

  private WebDriver webDriver;

  private final String LogoutButtonXPath = "//div[@class='b-mail-dropdown__item'][last()]";

  @FindBy(xpath = LogoutButtonXPath)
  private WebElement logoutButton;

  public Logout(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(this.webDriver, this);
  }

  public LoginPage doLogout() {
    logoutButton.click();
    System.out.println("Clicked button to log out");
    return new LoginPage(webDriver);
  }
}
