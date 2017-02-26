package com.epam.atm.pageObjectPattern.services;

import org.testng.Assert;

import com.epam.atm.pageObjectPattern.core.Driver;
import com.epam.atm.pageObjectPattern.models.Email;
import com.epam.atm.pageObjectPattern.models.User;
import com.epam.atm.pageObjectPattern.pages.EmailPage;
import com.epam.atm.pageObjectPattern.pages.MailBoxPage;

/**
 * Created by Ivan_Matsur on 2/16/2017.
 */
public class InnerPageService {

  private MailBoxPage mailBoxPage;
  private EmailPage emailPage;

  public void checkIsLoginSuccessful(User user) {
    mailBoxPage = new MailBoxPage();
    Assert.assertEquals(mailBoxPage.getEmailAddress(), user.getEmailAddress(), "Login failed");
  }

  public void createNewEmail(Email email) {
    emailPage = new MailBoxPage().getToolbar().writeNewEmail();
    emailPage.fillAllEmailFields(email.getMailTo(), email.getMailSubject(), email.getMailBody());
  }

  public void saveEmailAsDraft() {
    emailPage = new EmailPage();
    emailPage.getFoldersSection().openDraftFolder();
    emailPage.clickPopUpSaveButton();
  }

  public void checkIsEmailSavedAsDraft() {
    mailBoxPage = new MailBoxPage();
    Assert.assertTrue(
      mailBoxPage.isFirstEmailInFolderPresent(),
      "New email creation and saving as a draft failed");
  }

  public void openEmailSavedAsDraft() {
    mailBoxPage = new MailBoxPage().getFoldersSection().openDraftFolder();
    mailBoxPage.openFirstEmail();
  }

  public void checkDraftFields(Email email) {
    emailPage = new EmailPage();
    Assert.assertTrue(
      emailPage.isDraftEmailContactProper(email.getMailTo()),
      "Address of the saved draft is incorrect");
    Assert.assertTrue(
      emailPage.isDraftEmailSubjectProper(email.getMailSubject()),
      "Subject of the saved draft is incorrect");
    Assert.assertEquals(
      emailPage.getDraftEmailBody(),
      email.getMailBody(),
      "Body of the saved draft is incorrect");
  }

  public void sendDraftEmail() {
    mailBoxPage = new MailBoxPage().getFoldersSection().openDraftFolder();
    mailBoxPage.openFirstEmail();
  }

  public void checkIsDraftSent() {
    emailPage = new EmailPage();
    Assert.assertTrue(emailPage.sendEmail(), "Sending draft email failed");
  }

  public void checkIsDraftFolderEmpty() {
    mailBoxPage = new MailBoxPage().getFoldersSection().openDraftFolder();
    Assert.assertTrue(mailBoxPage.isNoEmailsLinkPresent(), "Draft folder is NOT empty");
  }

  public void checkIsNotSentFolderEmpty() {
    mailBoxPage = new MailBoxPage().getFoldersSection().openSentFolder();
    Assert.assertTrue(mailBoxPage.isFirstEmailInFolderPresent(), "Sent email is NOT in Sent folder");
  }

  public void logout() {
    mailBoxPage = new MailBoxPage();
    mailBoxPage.getLogoutPopup().doLogout();
  }

  public void checkIsLogoutSuccessful(String url) {
    Assert.assertEquals(Driver.getDriver().getCurrentUrl(), url, "Logout failed");
  }
}
