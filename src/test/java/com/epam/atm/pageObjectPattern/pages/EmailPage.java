package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.sections.folders.FoldersSection;
import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class EmailPage extends Page {

  private final String NewEmailToFieldXPath = "//div[@name='to']";
  private final String DraftEmailContactXPath = "//span[@data-contact-email='"+ YandexMailBoxTest.MAILTO +"']";
  private final String NewEmailSubjectFieldXPath = "//input[@name='subj']";
  private final String DraftEmailSubjectXPath = "//input[@name='subj' and @value='"+ YandexMailBoxTest.MAILSUBJECT +"']";
  private final String NewEmailBodyAreaXPath = "//div[@role='textbox']";
  private final String DraftEmailBodyXPath = "//div[@role='textbox']/div";
  private final String SaveButtonXPath = "//button[@data-action='save']";
  private final String SubmitButtonXPath = "//button[contains(@title, '(Ctrl + Enter')]";
  private final String RedirectLinkXPath = "//a[@class='mail-Done-Redirect-Link' and @href='#inbox']";

  @FindBy(xpath = NewEmailToFieldXPath)
  private WebElement newEmailToField;

  @FindBy(xpath = DraftEmailContactXPath)
  private WebElement draftEmailContact;

  @FindBy(xpath = NewEmailSubjectFieldXPath)
  private WebElement newEmailSubjectField;

  @FindBy(xpath = DraftEmailSubjectXPath)
  private WebElement draftEmailSubject;

  @FindBy(xpath = NewEmailBodyAreaXPath)
  private WebElement newEmailBodyArea;

  @FindBy(xpath = DraftEmailBodyXPath)
  private WebElement draftEmailBody;

  @FindBy(xpath = SaveButtonXPath)
  private WebElement saveButton;

  @FindBy(xpath = SubmitButtonXPath)
  private WebElement submitEmailButton;

  @FindBy(xpath = RedirectLinkXPath)
  private WebElement redirectLink;

  public EmailPage(WebDriver webDriver) {
    super(webDriver);
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
    newEmailBodyArea.sendKeys(body);
    System.out.println("Filled email \"Body\" text area");
  }

  public void fillAllEmailFields(String to, String subject, String body) {
    fillEmailTo(to);
    fillSubject(subject);
    fillEmailBody(body);
  }

  public boolean isDraftEmailContactProper() {
    boolean result;
    try{
      result = draftEmailContact.isDisplayed();
      System.out.println("Correct draft contact is present");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println("Correct draft contact is NOT present");
    }
    return result;
  }

  public boolean isDraftEmailSubjectProper() {
    boolean result;
    try {
      result = draftEmailSubject.isDisplayed();
      System.out.println("Correct draft subject is present");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println("Correct draft subject is NOT present");
    }
    return result;
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
    boolean result;
    submitEmailButton.click();
    System.out.println("Clicked \"Submit\" button to send the email");
    try{
      result = redirectLink.isDisplayed();
      System.out.println("Email has been sent successfully");
    } catch (NoSuchElementException e){
      result = false;
      System.out.println("Email has NOT been sent");
    }
    return result;
  }

  public FoldersSection folders() {
    FoldersSection foldersSection = this.foldersSection;
    System.out.println("Got access to Folder Section");
    return foldersSection;
  }
}