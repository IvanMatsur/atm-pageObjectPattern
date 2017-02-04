package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/1/2017.
 */
public class LoginPage {

  private WebDriver webDriver;

  private final String LoginFieldXPath = "//input[@name='login']";
  private final String PasswordFieldXPath = "//input[@name='passwd']";
  private final String LoginButtonXPath = "//form[@method='POST']//button[contains(@class, auth__button)]";

  @FindBy(xpath = LoginFieldXPath)
  private WebElement usernameField;

  @FindBy(xpath = PasswordFieldXPath)
  private WebElement passwordField;

  @FindBy(xpath = LoginButtonXPath)
  private WebElement loginButton;

  public LoginPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(this.webDriver, this);
  }

  public LoginPage openLoginPage() {
    webDriver.navigate().to(YandexMailBoxTest.URL);
    System.out.println("Login page is opened");
    return this;
  }

  public MailBoxPage doLogin(String username, String password) {
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    loginButton.click();
    System.out.println("Logged successfully");
    return new MailBoxPage(webDriver);
  }
}