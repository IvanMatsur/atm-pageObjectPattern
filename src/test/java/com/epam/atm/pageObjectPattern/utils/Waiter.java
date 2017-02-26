package com.epam.atm.pageObjectPattern.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.atm.pageObjectPattern.core.Driver;

/**
 * Created by Ivan_Matsur on 2/25/2017.
 */
public class Waiter {

  public static void addExplicitWaiterToBeClickable(WebElement webElement) {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
    wait.until(ExpectedConditions.elementToBeClickable(webElement));
  }
}
