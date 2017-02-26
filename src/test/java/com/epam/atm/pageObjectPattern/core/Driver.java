package com.epam.atm.pageObjectPattern.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Ivan_Matsur on 2/25/2017.
 */
public class Driver{

  private static WebDriver webDriver;

  public static WebDriver getDriver() {
    if (webDriver == null) {
      setDriver();
    }
    return webDriver;
  }

  private static void setDriver() {
    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
    webDriver = new ChromeDriver();
  }
}
