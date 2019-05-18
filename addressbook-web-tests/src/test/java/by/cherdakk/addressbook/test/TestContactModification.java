package by.cherdakk.addressbook.test;

import org.testng.annotations.Test;

public class TestContactModification extends TestBase {

  @Test
  public void TestContactModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().modifyContactForm();
    app.getContactHelper().submiContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
