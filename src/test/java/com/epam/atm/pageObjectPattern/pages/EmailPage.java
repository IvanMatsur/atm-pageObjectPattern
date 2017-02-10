package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class EmailPage extends InnerPage {

  private final String NEW_EMAIL_TO_FIELD_XPATH = "//div[@name='to']";
  private final String DRAFT_EMAIL_CONTACT_XPATH = "//span[@data-contact-email='"
                                                   + YandexMailBoxTest.MAIL_TO
                                                   + "']";
  private final String NEW_EMAIL_SUBJECT_FIELD_XPATH = "//input[@name='subj']";
  private final String DRAFT_EMAIL_SUBJECT_XPATH = "//input[@name='subj' and @value='"
                                                   + YandexMailBoxTest.MAIL_SUBJECT
                                                   + "']";
  private final String NEW_EMAIL_BODY_AREA_XPATH = "//div[@role='textbox']";
  private final String DRAFT_EMAIL_BODY_XPATH = "//div[@role='textbox']/div";
  private final String SAVE_BUTTON_XPATH = "//button[@data-action='save']";
  private final String SUBMIT_BUTTON_XPATH = "//button[contains(@title, '(Ctrl + Enter')]";
  private final String REDIRECT_LINK_XPATH = "//a[@class='mail-Done-Redirect-Link' and @href='#inbox']";

  @FindBy(xpath = NEW_EMAIL_TO_FIELD_XPATH)
  private WebElement newEmailToField;

  @FindBy(xpath = DRAFT_EMAIL_CONTACT_XPATH)
  private WebElement draftEmailContact;

  @FindBy(xpath = NEW_EMAIL_SUBJECT_FIELD_XPATH)
  private WebElement newEmailSubjectField;

  @FindBy(xpath = DRAFT_EMAIL_SUBJECT_XPATH)
  private WebElement draftEmailSubject;

  @FindBy(xpath = NEW_EMAIL_BODY_AREA_XPATH)
  private WebElement newEmailBodyArea;

  @FindBy(xpath = DRAFT_EMAIL_BODY_XPATH)
  private WebElement draftEmailBody;

  @FindBy(xpath = SAVE_BUTTON_XPATH)
  private WebElement saveButton;

  @FindBy(xpath = SUBMIT_BUTTON_XPATH)
  private WebElement submitEmailButton;

  @FindBy(xpath = REDIRECT_LINK_XPATH)
  private WebElement redirectLink;

  public EmailPage() {
    super();
  }

  public void fillEmailTo(String to) {
    newEmailToField.sendKeys(to);
    System.out.println("Filled email \"To\" field");
  }

  public void fillSubject(String subject) {
    newEmailSubjectField.sendKeys(subject);
    System.out.println("Filled email \"Subject\" field");
  }

  public void fillEmailBody(String body) {
    new Actions(YandexMailBoxTest.WEB_DRIVER).moveToElement(newEmailBodyArea).click().sendKeys(body).build().perform();
    System.out.println("Filled email \"Body\" text area");
  }

  public void fillAllEmailFields(String to, String subject, String body) {
    fillEmailTo(to);
    fillSubject(subject);
    fillEmailBody(body);
  }

  public boolean isDraftEmailContactProper() {
    return isElementPresent(draftEmailContact, "Correct draft contact is present");
  }

  public boolean isDraftEmailSubjectProper() {
    return isElementPresent(draftEmailSubject, "Correct draft subject is present");
  }

  public String getDraftEmailBody() {
    String result = draftEmailBody.getText();
    System.out.println("Got draft body text");
    return result;
  }

  public EmailPage clickPopUpSaveButton() {
    saveButton.click();
    System.out.println("Clicked \"Save\" button to save new email as a draft");
    return this;
  }

  public boolean sendEmail() {
    new Actions(YandexMailBoxTest.WEB_DRIVER).moveToElement(submitEmailButton).click().build().perform();
    System.out.println("Clicked \"Submit\" button to send the email");

    return isElementPresent(redirectLink, "Email has been sent");
  }
}