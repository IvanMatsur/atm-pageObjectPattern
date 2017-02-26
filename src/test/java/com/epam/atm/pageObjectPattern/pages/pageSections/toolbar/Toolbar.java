package com.epam.atm.pageObjectPattern.pages.pageSections.toolbar;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.atm.pageObjectPattern.core.Element;
import com.epam.atm.pageObjectPattern.pages.EmailPage;
import com.epam.atm.pageObjectPattern.pages.Page;

/**
 * Created by Ivan_Matsur on 2/2/2017.
 */
public class Toolbar extends Page {

  private final String TOOLBAR_XPATH = "//div[@data-key='box=toolbar-buttons-box']";
  private final String SELECT_ALL_ITEM_XPATH = "//div[not(contains(@class, 'is-hidden'))]/label[contains(@class, 'mail-Toolbar-Item-Checkbox')]";
  private final String NEW_EMAIL_XPATH = "//a[@href='#compose']";
  private final String DELETE_SELECTED_XPATH = "//div[contains(@data-key, 'view=toolbar-button-delete')]";

  @FindBy(xpath = TOOLBAR_XPATH + SELECT_ALL_ITEM_XPATH)
  private WebElement selectAllCheckbox;

  @FindBy(xpath = TOOLBAR_XPATH + NEW_EMAIL_XPATH)
  private WebElement newEmail;

  @FindBy(xpath = TOOLBAR_XPATH + DELETE_SELECTED_XPATH)
  private WebElement deleteSelected;

  public Toolbar() {
    super();
  }

  public EmailPage writeNewEmail() {
    new Element(newEmail).click();
    System.out.println("Clicked button to create a new email");
    return new EmailPage();
  }

  public void selectAllEmails() {
    new Element(selectAllCheckbox).click();
    System.out.println("Checked off checkbox to select all present emails");
  }

  public void deleteSelectedEmails() {
    new Element(deleteSelected).click();
    System.out.println("Clicked button to delete all selected emails");
  }

  public void selectAndDeleteAllEmails() {
    selectAllEmails();
    deleteSelectedEmails();
  }
}
