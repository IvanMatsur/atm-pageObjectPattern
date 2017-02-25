package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.tests.BaseTest;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class EmailPage extends InnerPage {

  public EmailPage() {
    super();
  }

  private final String NEW_EMAIL_TO_FIELD_XPATH = "//div[@name='to']";
  private final String NEW_EMAIL_SUBJECT_FIELD_XPATH = "//input[@name='subj']";
  private final String NEW_EMAIL_BODY_AREA_XPATH = "//div[@role='textbox']";
  private final String DRAFT_EMAIL_BODY_XPATH = "//div[@role='textbox']/div";
  private final String SAVE_BUTTON_XPATH = "//button[@data-action='save']";
  private final String SUBMIT_BUTTON_XPATH = "//button[contains(@title, '(Ctrl + Enter')]";
  private final String REDIRECT_LINK_XPATH = "//a[@class='mail-Done-Redirect-Link' and @href='#inbox']";

  private String getDraftEmailMailToXPath(String emailTo) {
    return "//span[@data-contact-email='" + emailTo + "']";
  }

  private String getDraftEmailSubjectXPath(String emailSubject) {
    return "//input[@name='subj' and @value='" + emailSubject + "']";
  }

  @FindBy(xpath = NEW_EMAIL_TO_FIELD_XPATH)
  private WebElement newEmailToField;

  @FindBy(xpath = NEW_EMAIL_SUBJECT_FIELD_XPATH)
  private WebElement newEmailSubjectField;

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

  public void fillEmailTo(String emailTo) {
    addJSBorderColorToElement(newEmailToField);
    newEmailToField.sendKeys(emailTo);
    System.out.println("Filled email \"To\" field");
  }

  public void fillSubject(String emailSubject) {
    addJSBorderColorToElement(newEmailSubjectField);
    newEmailSubjectField.sendKeys(emailSubject);
    System.out.println("Filled email \"Subject\" field");
  }

  public void fillEmailBody(String emailBody) {
    addJSBorderColorToElement(newEmailBodyArea);
    actionMoveToElementAndClickAndSendKeys(newEmailBodyArea, emailBody);
    System.out.println("Filled email \"Body\" text area");
  }

  public void fillAllEmailFields(String emailTo, String emailSubject, String emailBody) {
    fillEmailTo(emailTo);
    fillSubject(emailSubject);
    fillEmailBody(emailBody);
  }

  public boolean isDraftEmailContactProper(String emailTo) {
    WebElement draftEmailContact = BaseTest.getDriver().findElement(
      By.xpath(getDraftEmailMailToXPath(emailTo)));
    addJSBorderColorToElement(draftEmailContact);
    return isElementPresent(draftEmailContact, "Correct draft contact is present");
  }

  public boolean isDraftEmailSubjectProper(String emailSubject) {
    WebElement draftEmailSubject = BaseTest.getDriver().findElement(
      By.xpath(getDraftEmailSubjectXPath(emailSubject)));
    addJSBorderColorToElement(draftEmailSubject);
    return isElementPresent(draftEmailSubject, "Correct draft subject is present");
  }

  public String getDraftEmailBody() {
    addJSBorderColorToElement(draftEmailBody);
    String result = draftEmailBody.getText();
    System.out.println("Got draft body text");
    return result;
  }

  public EmailPage clickPopUpSaveButton() {
    addJSBorderColorToElement(saveButton);
    saveButton.click();
    System.out.println("Clicked \"Save\" button to save new email as a draft");
    return this;
  }

  public boolean sendEmail() {
    addJSBorderColorToElement(submitEmailButton);
    actionMoveToElementAndClick(submitEmailButton);
    System.out.println("Clicked \"Submit\" button to send the email");

    addJSBorderColorToElement(redirectLink);
    return isElementPresent(redirectLink, "Email has been sent");
  }


}