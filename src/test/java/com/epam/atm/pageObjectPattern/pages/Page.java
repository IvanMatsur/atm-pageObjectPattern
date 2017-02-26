package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.core.Driver;
import com.epam.atm.pageObjectPattern.core.Element;

/**
 * Created by Ivan_Matsur on 2/8/2017.
 */
public abstract class Page {

  public Page() {
    PageFactory.initElements(Driver.getDriver(), this);
  }

  public boolean isElementPresent(Element element) {
    boolean result;
    try {
      result = element.isDisplayed();
    } catch (NoSuchElementException e) {
      result = false;
    }
    return result;
  }

  public boolean isElementPresent(Element element, String s) {
    boolean result;
    try {
      result = element.isDisplayed();
      System.out.println(s + ": TRUE");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println(s + ": FALSE");
    }
    return result;
  }
}