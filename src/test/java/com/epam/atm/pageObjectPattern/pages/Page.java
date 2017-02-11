package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/8/2017.
 */
public abstract class Page {

  public Page() {
    PageFactory.initElements(YandexMailBoxTest.WEB_DRIVER, this);
  }

  public boolean isElementPresent(WebElement webElement, String s) {
    boolean result;
    try {
      result = webElement.isDisplayed();
      System.out.println(s + ": TRUE");
    } catch (NoSuchElementException e) {
      result = false;
      System.out.println(s + ": FALSE");
    }
    return result;
  }

  public boolean isElementPresent(WebElement webElement) {
    boolean result;
    try {
      result = webElement.isDisplayed();
    } catch (NoSuchElementException e) {
      result = false;
    }
    return result;
  }

  public void addJSBorderColorToElement(WebElement element) {
    //JS Executor
    String bg = element.getCssValue("backgroundColor");
    JavascriptExecutor js = ((JavascriptExecutor)YandexMailBoxTest.WEB_DRIVER);
    js.executeScript("arguments[0].style.backgroundColor = '" + "Cyan" + "'", element);
    js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
  }

}
