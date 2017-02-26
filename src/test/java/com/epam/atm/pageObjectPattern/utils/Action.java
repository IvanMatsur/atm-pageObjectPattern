package com.epam.atm.pageObjectPattern.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.epam.atm.pageObjectPattern.core.Driver;

/**
 * Created by Ivan_Matsur on 2/25/2017.
 */
public class Action {

  public static void moveToElementAndClickAndSendKeys(WebElement webElement, String text) {
    new Actions(Driver.getDriver()).moveToElement(webElement).click().sendKeys(text).build().perform();
  }

  public static void moveToElementAndClick(WebElement webElement) {
    new Actions(Driver.getDriver()).moveToElement(webElement).click().build().perform();
  }
}
