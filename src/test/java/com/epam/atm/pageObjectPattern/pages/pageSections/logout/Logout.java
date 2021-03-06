package com.epam.atm.pageObjectPattern.pages.pageSections.logout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.core.Element;
import com.epam.atm.pageObjectPattern.pages.LoginPage;
import com.epam.atm.pageObjectPattern.pages.Page;

/**
 * Created by Ivan_Matsur on 2/3/2017.
 */
public class Logout extends Page {

  private final String LOGOUT_BUTTON_XPATH = "//div[contains(@class, 'b-mail-dropdown__item')][last()]";

  @FindBy(xpath = LOGOUT_BUTTON_XPATH)
  private WebElement logoutButton;

  public Logout() {
    super();
  }

  public LoginPage doLogout() {
    Element element = new Element(logoutButton);
    if (isElementPresent(element)) {
      element.click();
      System.out.println("Clicked button to log out");
    }
    return new LoginPage();
  }
}
