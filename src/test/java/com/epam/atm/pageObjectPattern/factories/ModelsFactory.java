package com.epam.atm.pageObjectPattern.factories;

import com.epam.atm.pageObjectPattern.models.Email;
import com.epam.atm.pageObjectPattern.models.User;

/**
 * Created by Ivan_Matsur on 2/26/2017.
 */
public class ModelsFactory {

  public static User createJohnSmith() {
    return new User("TestJohnSmith", "123456Password", "TestJohnSmith@yandex.ru");
  }

  public static Email createTestEmail() {
    return new Email("test@test.by", "Test", "Hello World!");
  }
}
