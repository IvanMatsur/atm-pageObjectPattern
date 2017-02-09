package com.epam.atm.pageObjectPattern.sections.logout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.pages.LoginPage;
import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/3/2017.
 */
public class Logout {

  private final String LOGOUT_BUTTON_XPATH = "//div[@class='b-mail-dropdown__item'][last()]";

  @FindBy(xpath = LOGOUT_BUTTON_XPATH)
  private WebElement logoutButton;

  public Logout() {
    PageFactory.initElements(YandexMailBoxTest.WEB_DRIVER, this);
  }

  public LoginPage doLogout() {
    logoutButton.click();
    System.out.println("Clicked button to log out");
    return new LoginPage();
  }
}
