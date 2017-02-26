package com.epam.atm.pageObjectPattern.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.epam.atm.pageObjectPattern.core.Driver;

/**
 * Created by Ivan_Matsur on 2/25/2017.
 */
public class JS {

  public static void addJSBorderColorToElement(WebElement webElement) {
    //JS Executor Highlighter
    String bg = webElement.getCssValue("backgroundColor");
    JavascriptExecutor js = ((JavascriptExecutor)Driver.getDriver());
    js.executeScript("arguments[0].style.backgroundColor = '" + "Cyan" + "'", webElement);
    js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", webElement);
  }

  public static void addJSClickerByQuerySelector(String css) {
    JavascriptExecutor jsExec = (JavascriptExecutor)Driver.getDriver();
    jsExec.executeScript("document.querySelector(\"" + css + "\").click()");
  }
}
