package com.epam.atm.pageObjectPattern.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.epam.atm.pageObjectPattern.utils.JS;
import com.epam.atm.pageObjectPattern.utils.Waiter;

/**
 * Created by Ivan_Matsur on 2/25/2017.
 */
public class Element implements WebElement {

  private WebElement webElement;

  public Element(WebElement webElement) {
    this.webElement = webElement;
  }

  public void click() {
    Waiter.addExplicitWaiterToBeClickable(webElement);
    JS.addJSBorderColorToElement(webElement);
    webElement.click();
  }

  public void submit() {
    webElement.submit();
  }

  public void sendKeys(CharSequence... charSequences) {
    JS.addJSBorderColorToElement(webElement);
    webElement.sendKeys(charSequences);
  }

  public void clear() {
    webElement.clear();
  }

  public String getTagName() {
    return webElement.getTagName();
  }

  public String getAttribute(String s) {
    return webElement.getAttribute(s);
  }

  public boolean isSelected() {
    return webElement.isSelected();
  }

  public boolean isEnabled() {
    return webElement.isEnabled();
  }

  public String getText() {
    JS.addJSBorderColorToElement(webElement);
    return webElement.getText();
  }

  public List<WebElement> findElements(By by) {
    return webElement.findElements(by);
  }

  public WebElement findElement(By by) {
    return webElement.findElement(by);
  }

  public boolean isDisplayed() {
    JS.addJSBorderColorToElement(webElement);
    return webElement.isDisplayed();
  }

  public Point getLocation() {
    return webElement.getLocation();
  }

  public Dimension getSize() {
    return webElement.getSize();
  }

  public Rectangle getRect() {
    return webElement.getRect();
  }

  public String getCssValue(String s) {
    return webElement.getCssValue(s);
  }

  public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
    return webElement.getScreenshotAs(outputType);
  }
}
