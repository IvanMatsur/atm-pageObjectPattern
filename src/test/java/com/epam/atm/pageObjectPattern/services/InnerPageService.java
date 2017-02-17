package com.epam.atm.pageObjectPattern.services;

import org.testng.Assert;

import com.epam.atm.pageObjectPattern.models.Email;
import com.epam.atm.pageObjectPattern.models.User;
import com.epam.atm.pageObjectPattern.pages.EmailPage;
import com.epam.atm.pageObjectPattern.pages.MailBoxPage;
import com.epam.atm.pageObjectPattern.tests.BaseTest;

/**
 * Created by Ivan_Matsur on 2/16/2017.
 */
public class InnerPageService {

  public void checkIsLoginSuccessful(User user) {
    Assert.assertEquals(new MailBoxPage().getEmailAddress(), user.getEmailAddress(), "Login failed");
  }

  public void createNewEmail(Email email) {
    EmailPage emailPage = new MailBoxPage().getToolbar().writeNewEmail(email);
    emailPage.fillAllEmailFields();
  }

  public void saveEmailAsDraft(Email email) {
    EmailPage emailPage = new EmailPage(email);
    MailBoxPage mailBoxPage = emailPage.getFoldersSection().openDraftFolder();
    emailPage.clickPopUpSaveButton();
  }

  public void checkIsEmailSavedAsDraft() {
    Assert.assertTrue(
      new MailBoxPage().isFirstEmailInFolderPresent(),
      "New email creation and saving as a draft failed");
  }

  public void openEmailSavedAsDraft(Email email) {
    new MailBoxPage().getFoldersSection().openDraftFolder().openFirstEmail(email);
  }

  public void checkDraftFields(Email email) {
    EmailPage emailPage = new EmailPage(email);
    Assert.assertTrue(emailPage.isDraftEmailContactProper(), "Address of the saved draft is incorrect");
    Assert.assertTrue(emailPage.isDraftEmailSubjectProper(), "Subject of the saved draft is incorrect");
    Assert.assertEquals(
      emailPage.getDraftEmailBody(),
      email.getMailBody(),
      "Body of the saved draft is incorrect");
  }

  public void sendDraftEmail(Email email) {
    new MailBoxPage().getFoldersSection().openDraftFolder().openFirstEmail(email);
  }

  public void checkIsDraftSent(Email email) {
    Assert.assertTrue(new EmailPage(email).sendEmail(), "Sending draft email failed");
  }

  public void checkIsDraftFolderEmpty() {
    MailBoxPage mailBoxPage = new MailBoxPage().getFoldersSection().openDraftFolder();
    Assert.assertTrue(mailBoxPage.isNoEmailsLinkPresent(), "Draft folder is NOT empty");
  }

  public void checkIsNotSentFolderEmpty() {
    MailBoxPage mailBoxPage = new MailBoxPage().getFoldersSection().openSentFolder();
    Assert.assertTrue(mailBoxPage.isFirstEmailInFolderPresent(), "Sent email is NOT in Sent folder");
  }

  public void logout() {
    new MailBoxPage().getLogoutPopup().doLogout();
  }

  public void checkIsLogoutSuccessful(String url) {
    Assert.assertEquals(BaseTest.getDriver().getCurrentUrl(), url, "Logout failed");
  }
}
