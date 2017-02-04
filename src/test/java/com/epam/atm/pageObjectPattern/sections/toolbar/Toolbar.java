package com.epam.atm.pageObjectPattern.sections.toolbar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.pages.EmailPage;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class Toolbar {

  private WebDriver webDriver;

  private final String ToolbarXPath = "//div[@data-key='box=toolbar-buttons-box']";
  private final String SelectAllItemXPath = "//div[not(contains(@class, 'is-hidden'))]/label[@class='mail-Toolbar-Item-Checkbox']";
  private final String NewEmailItemXPath = "//a[@href='#compose']";
  private final String DeleteSelectedXPath = "//div[contains(@data-key, 'view=toolbar-button-delete')]";

  @FindBy(xpath = ToolbarXPath + SelectAllItemXPath)
  private WebElement selectAllCheckbox;

  @FindBy(xpath = ToolbarXPath + NewEmailItemXPath)
  private WebElement newEmail;

  @FindBy(xpath = ToolbarXPath + DeleteSelectedXPath)
  private WebElement deleteSelected;

  public Toolbar(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(this.webDriver, this);
  }

  public EmailPage writeNewEmail() {
    newEmail.click();
    System.out.println("Clicked button to create a new email");
    return new EmailPage(webDriver);
  }

  public void selectAllEmails() {
    System.out.println("Checked off checkbox to select all present emails");
    selectAllCheckbox.click();
  }

  public void deleteSelectedEmails() {
    System.out.println("Clicked button to delete all selected emails");
    deleteSelected.click();
  }
}
