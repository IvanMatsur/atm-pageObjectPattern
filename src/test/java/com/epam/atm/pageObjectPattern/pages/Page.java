package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.atm.pageObjectPattern.tests.BaseTest;

/**
 * Created by Ivan_Matsur on 2/8/2017.
 */
public abstract class Page {

  public Page() {
      PageFactory.initElements(BaseTest.getDriver(), this);
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
    JavascriptExecutor js = ((JavascriptExecutor) BaseTest.getDriver());
    js.executeScript("arguments[0].style.backgroundColor = '" + "Cyan" + "'", element);
    js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
  }

  public void addJSClickerByQuerySelector(String css) {
    JavascriptExecutor jsExec = (JavascriptExecutor) BaseTest.getDriver();
    jsExec.executeScript("document.querySelector(\"" + css + "\").click()");
  }

  public void actionMoveToElementAndClickAndSendKeys(WebElement webElement, String text) {
    new Actions(BaseTest.getDriver()).moveToElement(webElement).click().sendKeys(text).build().perform();
  }

  public void actionMoveToElementAndClick(WebElement webElement) {
    new Actions(BaseTest.getDriver()).moveToElement(webElement).click().build().perform();
  }

  public void addExplicitWaiterToBeClickable(WebElement webElement) {
    WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), 10);
    wait.until(ExpectedConditions.elementToBeClickable(webElement));
  }
}