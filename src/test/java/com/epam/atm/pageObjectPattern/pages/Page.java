package com.epam.atm.pageObjectPattern.pages;

import org.openqa.selenium.support.PageFactory;

import com.epam.atm.pageObjectPattern.tests.YandexMailBoxTest;

/**
 * Created by Ivan_Matsur on 2/8/2017.
 */
public abstract class Page {

  public Page() {
    PageFactory.initElements(YandexMailBoxTest.WEB_DRIVER, this);
  }
}
