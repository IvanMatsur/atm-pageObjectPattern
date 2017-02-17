package com.epam.atm.pageObjectPattern.models;

/**
 * Created by Ivan_Matsur on 2/16/2017.
 */
public class User {

  private String username;
  private String password;
  private String emailAddress;

  public User(String username, String password, String emailAddress) {
    this.username = username;
    this.password = password;
    this.emailAddress = emailAddress;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    User user = (User)o;

    if (username != null ? !username.equals(user.username) : user.username != null)
      return false;
    if (password != null ? !password.equals(user.password) : user.password != null)
      return false;
    return emailAddress != null ? emailAddress.equals(user.emailAddress) : user.emailAddress == null;
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "User{"
           + "username='"
           + username
           + '\''
           + ", password='"
           + password
           + '\''
           + ", emailAddress='"
           + emailAddress
           + '\''
           + '}';
  }
}
