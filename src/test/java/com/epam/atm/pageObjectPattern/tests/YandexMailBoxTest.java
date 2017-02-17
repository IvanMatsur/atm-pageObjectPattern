package com.epam.atm.pageObjectPattern.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.epam.atm.pageObjectPattern.models.Email;
import com.epam.atm.pageObjectPattern.models.User;
import com.epam.atm.pageObjectPattern.pages.LoginPage;
import com.epam.atm.pageObjectPattern.pages.MailBoxPage;
import com.epam.atm.pageObjectPattern.pages.pageSections.folders.Folder;
import com.epam.atm.pageObjectPattern.services.InnerPageService;
import com.epam.atm.pageObjectPattern.services.OuterPageService;

/**
 * Created by Ivan_Matsur on 2/1/2017.
 */
public class YandexMailBoxTest extends BaseTest {

  public final static String URL = "https://www.yandex.by/";

  private User user = new User("TestJohnSmith", "123456Password", "TestJohnSmith@yandex.ru");
  private Email email = new Email("test@test.by", "Test", "Hello World!");

  private OuterPageService outerPageService = new OuterPageService();
  private InnerPageService innerPageService = new InnerPageService();

  @AfterClass
  public void doPreparationForNextLaunch() {
    if (getDriver().getCurrentUrl().equals(URL)) {
      new LoginPage().openLoginPage(URL).doLogin(user.getUsername(), user.getPassword());
    }

    MailBoxPage mailBoxPage = new MailBoxPage().getFoldersSection().openAndCleanFolder(Folder.Type.SENT);
    mailBoxPage = mailBoxPage.getFoldersSection().openAndCleanFolder(Folder.Type.DRAFT);
    mailBoxPage = mailBoxPage.getFoldersSection().openAndCleanFolder(Folder.Type.TRASH);

    mailBoxPage.getLogoutPopup().doLogout();

    getDriver().quit();
  }

  @Test(description = "Check that login is successful", groups = "login")
  public void loginToMailBox() {
    outerPageService.openPage(URL);
    outerPageService.loginToEmailBox(user);
    innerPageService.checkIsLoginSuccessful(user);
  }

  @Test(description = "Check that new email can be created and saved as draft", groups = "creation", dependsOnMethods = "loginToMailBox")
  public void createEmailDraft() {
    innerPageService.createNewEmail(email);
    innerPageService.saveEmailAsDraft(email);
    innerPageService.checkIsEmailSavedAsDraft();
  }

  @Test(description = "Check content of the sent email", groups = "content", dependsOnMethods = "createEmailDraft")
  public void checkDraftContent() {
    innerPageService.openEmailSavedAsDraft(email);
    innerPageService.checkDraftFields(email);
  }

  @Test(description = "Check that draft can be sent", groups = "send", dependsOnMethods = "checkDraftContent")
  public void sendDraft() {
    innerPageService.sendDraftEmail(email);
    innerPageService.checkIsDraftSent(email);
  }

  @Test(description = "Check that Draft folder is empty", groups = "send", dependsOnGroups = "content", dependsOnMethods = "sendDraft")
  public void isNoDrafts() {
    innerPageService.checkIsDraftFolderEmpty();
  }

  @Test(description = "Check that email is in Sent folder", groups = "send", dependsOnGroups = "content", dependsOnMethods = "sendDraft")
  public void isMailSent() {
    innerPageService.checkIsNotSentFolderEmpty();
  }

  @Test(description = "Check logout is successful", groups = "logout", dependsOnGroups = "send", alwaysRun = true)
  public void logoutTest() {
    innerPageService.logout();
    innerPageService.checkIsLogoutSuccessful(URL);
  }
}