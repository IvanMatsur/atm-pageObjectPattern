package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/8/2017.
 */
public abstract class Page {

  public Page() {
    boolean pageInitialized = false;

    while (!pageInitialized){
      try {
        PageFactory.initElements(YandexMailBoxTest.WEB_DRIVER, this);
        pageInitialized = true;
      } catch (StaleElementReferenceException e1) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e2) {
        }
      }
    }
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
    //JS Executor Highlighter
    String bg = element.getCssValue("backgroundColor");
    JavascriptExecutor js = ((JavascriptExecutor)YandexMailBoxTest.WEB_DRIVER);
    js.executeScript("arguments[0].style.backgroundColor = '" + "Cyan" + "'", element);
    js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
  }

  public void addJSClickerByQuerySelector(String css) {
    JavascriptExecutor jsExec = (JavascriptExecutor) YandexMailBoxTest.WEB_DRIVER;
    jsExec.executeScript("document.querySelector(\"" + css + "\").click()");
  }

  public void actionMoveToElementAndClickAndSendKeys(WebElement webElement, String text) {
    new Actions(YandexMailBoxTest.WEB_DRIVER).moveToElement(webElement).click().sendKeys(text).build().perform();
  }

  public void actionMoveToElementAndClick(WebElement webElement) {
    new Actions(YandexMailBoxTest.WEB_DRIVER).moveToElement(webElement).click().build().perform();
  }
}