package com.epam.atm.pageObjectPattern.services;

import com.epam.atm.pageObjectPattern.models.User;
import com.epam.atm.pageObjectPattern.pages.LoginPage;

/**
 * Created by Ivan_Matsur on 2/16/2017.
 */
public class OuterPageService {

  public void openPage(String url) {
    new LoginPage().openLoginPage(url);
  }

  public void loginToEmailBox(User user) {
    new LoginPage().doLogin(user.getUsername(), user.getPassword());
  }
}
