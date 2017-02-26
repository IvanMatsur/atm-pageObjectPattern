package com.epam.atm.pageObjectPattern.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;

import com.epam.atm.pageObjectPattern.core.Driver;

/**
 * Created by Ivan_Matsur on 2/17/2017.
 */
public abstract class BaseTest {

  @BeforeClass()
  public void doPreparationForTests() {
    Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    Driver.getDriver().manage().window().maximize();
  }
}
