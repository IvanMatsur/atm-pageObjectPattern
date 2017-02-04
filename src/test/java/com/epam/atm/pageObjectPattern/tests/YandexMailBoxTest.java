package com.epam.atm.pageObjectPattern.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

  private WebDriver webDriver;

  public final static String URL = "https://www.yandex.by/";
  public final String USERNAME = "TestJohnSmith";
  public final String PASSWORD = "123456Password";
  public final String EMAIL = "TestJohnSmith@yandex.ru";
  public final static String MAILTO = "test@test.by";
  public final static String MAILSUBJECT = "Test";
  public final String MAILBODY = "Hello World!";

  @BeforeClass
  private void doPreparationForTests() {
    System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
    webDriver = new ChromeDriver();
    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    webDriver.manage().window().maximize();
  }

  @AfterClass
  private void doPreparationForNextLaunch() {
    if (webDriver.getCurrentUrl().equals(URL)) {
      new LoginPage(webDriver).openLoginPage().doLogin(USERNAME, PASSWORD);
    }

    MailBoxPage mailBoxPage = new MailBoxPage(webDriver).folders().openSentFolder();

    if (mailBoxPage.isFirstEmailInFolderPresent()) {
      mailBoxPage.toolbar().selectAllEmails();
      mailBoxPage.toolbar().deleteSelectedEmails();
    }

    mailBoxPage.folders().openDraftFolder();

    if (mailBoxPage.isFirstEmailInFolderPresent()) {
      mailBoxPage.toolbar().selectAllEmails();
      mailBoxPage.toolbar().deleteSelectedEmails();
    }

    mailBoxPage.logout().doLogout();

    webDriver.quit();
  }

  @Test(description = "Check that login is successful", groups = "login")
  public void loginToMailBox() {
    MailBoxPage mailBoxPage = new LoginPage(webDriver).openLoginPage().doLogin(USERNAME, PASSWORD);
    String emailAddress = mailBoxPage.emailAddress();
    Assert.assertEquals(emailAddress, EMAIL, "Login failed");
  }

  @Test(description = "Check that new email can be created and saved as draft", groups = "creation", dependsOnMethods = "loginToMailBox")
  public void createEmailDraft() {
    EmailPage emailPage = new MailBoxPage(webDriver).toolbar().writeNewEmail();
    emailPage.fillAllEmailFields(MAILTO, MAILSUBJECT, MAILBODY);
    MailBoxPage mailBoxPage = emailPage.folders().openDraftFolder();
    emailPage.clickPopUpSaveButton();
    mailBoxPage.isFirstEmailInFolderPresent();
    Assert.assertEquals(
      mailBoxPage.folders().getEmailsNumberInDraftFolder(),
      "1",
      "New email creation and saving as a draft failed");
  }

  @Test(description = "Check content of the sent email", groups = "content", dependsOnMethods = "createEmailDraft")
  public void checkDraftContent() {
    EmailPage emailPage = new MailBoxPage(webDriver).folders().openDraftFolder().openFirstEmail();
    Assert.assertTrue(emailPage.isDraftEmailContactProper(), "Address of the saved draft is incorrect");
    Assert.assertTrue(emailPage.isDraftEmailSubjectProper(), "Subject of the saved draft is incorrect");
    Assert.assertEquals(emailPage.getDraftEmailBody(), MAILBODY, "Body of the saved draft is incorrect");
  }

  @Test(description = "Check that draft can be sent", groups = "send", dependsOnMethods = "checkDraftContent")
  public void sendDraft() {
    EmailPage emailPage = new MailBoxPage(webDriver).folders().openDraftFolder().openFirstEmail();
    boolean isEmailSent = emailPage.sendEmail();
    Assert.assertTrue(isEmailSent, "Sending draft email failed");
  }

  @Test(description = "Check that Draft folder is empty", groups = "send", dependsOnGroups = "content", dependsOnMethods = "sendDraft")
  public void isNoDrafts() {
    MailBoxPage mailBoxPage = new MailBoxPage(webDriver).folders().openDraftFolder();
    Assert.assertTrue(mailBoxPage.isNoEmailsLinkPresent(), "Draft folder is NOT empty");
  }

  @Test(description = "Check that email is in Sent folder", groups = "send", dependsOnGroups = "content", dependsOnMethods = "sendDraft")
  public void isMailSent() {
    MailBoxPage mailBoxPage = new MailBoxPage(webDriver).folders().openSentFolder();
    mailBoxPage.isFirstEmailInFolderPresent();
    String emailsNumberInSent = mailBoxPage.folders().getEmailsNumberInSentFolder();
    Assert.assertEquals(emailsNumberInSent, "1", "Sent email is NOT in Sent folder");
  }

  @Test(description = "Check logout is successful", groups = "logout", dependsOnGroups = "send")
  public void logoutTest() {
    new MailBoxPage(webDriver).logout().doLogout();
    Assert.assertEquals(webDriver.getCurrentUrl(), URL, "Logout failed");
  }
}
