package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.sections.folders.FoldersSection;
import com.epam.atm.pageObjectPattern.sections.toolbar.Toolbar;

/**
 * Created by Ivan_Matsur on 2/4/2017.
 */
public abstract class Page {

  protected final WebDriver webDriver;

  protected FoldersSection foldersSection;
  protected Toolbar toolbar;

  public Page(WebDriver webDriver) {
    this.webDriver = webDriver;
    this.foldersSection = new FoldersSection(this.webDriver);
    this.toolbar = new Toolbar(this.webDriver);
    PageFactory.initElements(this.webDriver, this);
  }
}
