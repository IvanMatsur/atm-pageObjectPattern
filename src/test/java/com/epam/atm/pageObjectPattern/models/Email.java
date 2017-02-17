package com.epam.atm.pageObjectPattern.models;

/**
 * Created by Ivan_Matsur on 2/16/2017.
 */
public class Email {

  private String mailTo;
  private String mailSubject;
  private String mailBody;

  public Email(String mailTo, String mailSubject, String mailBody) {
    this.mailTo = mailTo;
    this.mailSubject = mailSubject;
    this.mailBody = mailBody;
  }

  public String getMailTo() {
    return mailTo;
  }

  public void setMailTo(String mailTo) {
    this.mailTo = mailTo;
  }

  public String getMailSubject() {
    return mailSubject;
  }

  public void setMailSubject(String mailSubject) {
    this.mailSubject = mailSubject;
  }

  public String getMailBody() {
    return mailBody;
  }

  public void setMailBody(String mailBody) {
    this.mailBody = mailBody;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Email email = (Email)o;

    if (mailTo != null ? !mailTo.equals(email.mailTo) : email.mailTo != null)
      return false;
    if (mailSubject != null ? !mailSubject.equals(email.mailSubject) : email.mailSubject != null)
      return false;
    return mailBody != null ? mailBody.equals(email.mailBody) : email.mailBody == null;

  }

  @Override
  public int hashCode() {
    int result = mailTo != null ? mailTo.hashCode() : 0;
    result = 31 * result + (mailSubject != null ? mailSubject.hashCode() : 0);
    result = 31 * result + (mailBody != null ? mailBody.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Email{"
           + "mailTo='"
           + mailTo
           + '\''
           + ", mailSubject='"
           + mailSubject
           + '\''
           + ", mailBody='"
           + mailBody
           + '\''
           + '}';
  }
}
