package com.epam.atm.pageObjectPattern.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.atm.pageObjectPattern.pages.EmailPage;
import com.epam.atm.pageObjectPattern.pages.LoginPage;
import com.epam.atm.pageObjectPattern.pages.MailBoxPage;

/**
 * Created by Ivan_Matsur on 2/1/2017.
 */
public class YandexMailBoxTest {

  public final static String URL = "https://www.yandex.by/";
  public final String USERNAME = "TestJohnSmith";
  public final String PASSWORD = "123456Password";
  public final String EMAIL = "TestJohnSmith@yandex.ru";
  public final static String MAIL_TO = "test@test.by";
  public final static String MAIL_SUBJECT = "Test";
  public final String MAIL_BODY = "Hello World!";
  public final String BROWSER = "Chrome";

  public static WebDriver WEB_DRIVER;

  @BeforeClass
  private void doPreparationForTests() {
    if(BROWSER.equals("Chrome")) {
      System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
      WEB_DRIVER = new ChromeDriver();
    } else {
      System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckoDriver/geckodriver.exe");
//      FirefoxProfile profile = new FirefoxProfile();
//      profile.setEnableNativeEvents(true);
      WEB_DRIVER = new FirefoxDriver(/*profile*/);
    }

    WEB_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WEB_DRIVER.manage().window().maximize();
  }

  @AfterClass
  public void doPreparationForNextLaunch() {
    if (WEB_DRIVER.getCurrentUrl().equals(URL)) {
      new LoginPage().openLoginPage().doLogin(USERNAME, PASSWORD);
    }

    MailBoxPage mailBoxPage = new MailBoxPage().folders().openSentFolder();

    if (mailBoxPage.isFirstEmailInFolderPresent()) {
      mailBoxPage.toolbar().selectAllEmails();
      mailBoxPage.toolbar().deleteSelectedEmails();
    }

    mailBoxPage = mailBoxPage.folders().openDraftFolder();

    if (mailBoxPage.isFirstEmailInFolderPresent()) {
      mailBoxPage.toolbar().selectAllEmails();
      mailBoxPage.toolbar().deleteSelectedEmails();
    }

    mailBoxPage.logout().doLogout();

    WEB_DRIVER.quit();
  }

  @Test(description = "Check that login is successful", groups = "login")
  public void loginToMailBox() {
    MailBoxPage mailBoxPage = new LoginPage().openLoginPage().doLogin(USERNAME, PASSWORD);
    String emailAddress = mailBoxPage.emailAddress();
    Assert.assertEquals(emailAddress, EMAIL, "Login failed");
  }

  @Test(description = "Check that new email can be created and saved as draft", groups = "creation", dependsOnMethods = "loginToMailBox")
  public void createEmailDraft() {
    EmailPage emailPage = new MailBoxPage().toolbar().writeNewEmail();
    emailPage.fillAllEmailFields(MAIL_TO, MAIL_SUBJECT, MAIL_BODY);
    MailBoxPage mailBoxPage = emailPage.folders().openDraftFolder();
    emailPage.clickPopUpSaveButton();
    Assert.assertTrue(
      mailBoxPage.isFirstEmailInFolderPresent(),
      "New email creation and saving as a draft failed");
  }

  @Test(description = "Check content of the sent email", groups = "content", dependsOnMethods = "createEmailDraft")
  public void checkDraftContent() {
    EmailPage emailPage = new MailBoxPage().folders().openDraftFolder().openFirstEmail();
    Assert.assertTrue(emailPage.isDraftEmailContactProper(), "Address of the saved draft is incorrect");
    Assert.assertTrue(emailPage.isDraftEmailSubjectProper(), "Subject of the saved draft is incorrect");
    Assert.assertEquals(emailPage.getDraftEmailBody(), MAIL_BODY, "Body of the saved draft is incorrect");
  }

  @Test(description = "Check that draft can be sent", groups = "send", dependsOnMethods = "checkDraftContent")
  public void sendDraft() {
    EmailPage emailPage = new MailBoxPage().folders().openDraftFolder().openFirstEmail();
    Assert.assertTrue(emailPage.sendEmail(), "Sending draft email failed");
  }

  @Test(description = "Check that Draft folder is empty", groups = "send", dependsOnGroups = "content", dependsOnMethods = "sendDraft")
  public void isNoDrafts() {
    MailBoxPage mailBoxPage = new MailBoxPage().folders().openDraftFolder();
    Assert.assertTrue(mailBoxPage.isNoEmailsLinkPresent(), "Draft folder is NOT empty");
  }

  @Test(description = "Check that email is in Sent folder", groups = "send", dependsOnGroups = "content", dependsOnMethods = "sendDraft")
  public void isMailSent() {
    MailBoxPage mailBoxPage = new MailBoxPage().folders().openSentFolder();
    Assert.assertTrue(mailBoxPage.isFirstEmailInFolderPresent(), "Sent email is NOT in Sent folder");
  }

  @Test(description = "Check logout is successful", groups = "logout", dependsOnGroups = "send", alwaysRun = true)
  public void logoutTest() {
    new MailBoxPage().logout().doLogout();
    Assert.assertEquals(WEB_DRIVER.getCurrentUrl(), URL, "Logout failed");
  }
}