package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/1/2017.
 */
public class LoginPage extends Page {

  private final String LOGIN_FIELD_XPATH = "//input[@name='login']";
  private final String PASSWORD_FIELD_XPATH = "//input[@name='passwd']";
  private final String LOGIN_BUTTON_XPATH = "//form[@method='POST']//button[contains(@class, auth__button)]";

  @FindBy(xpath = LOGIN_FIELD_XPATH)
  private WebElement usernameField;

  @FindBy(xpath = PASSWORD_FIELD_XPATH)
  private WebElement passwordField;

  @FindBy(xpath = LOGIN_BUTTON_XPATH)
  private WebElement loginButton;

  public LoginPage() {
    super();
  }

  public LoginPage openLoginPage() {
    YandexMailBoxTest.WEB_DRIVER.navigate().to(YandexMailBoxTest.URL);
    System.out.println("Login page is opened");
    return this;
  }

  public MailBoxPage doLogin(String username, String password) {
    addJSBorderColorToElement(usernameField);
    usernameField.sendKeys(username);

    addJSBorderColorToElement(passwordField);
    passwordField.sendKeys(password);

    addJSBorderColorToElement(loginButton);
    loginButton.click();

    System.out.println("Logged successfully");
    return new MailBoxPage();
  }
}