package com.epam.atm.pageObjectPattern.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

/**
 * Created by Ivan_Matsur on 2/17/2017.
 */
public abstract class BaseTest {

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

  @BeforeClass()
  public void doPreparationForTests() {
    getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    getDriver().manage().window().maximize();
  }
}
