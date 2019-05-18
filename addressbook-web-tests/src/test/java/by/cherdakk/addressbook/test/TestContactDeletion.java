package by.cherdakk.addressbook.test;

import org.testng.annotations.Test;

public class TestContactDeletion extends TestBase{

  @Test
  public void TestContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().goToHomePage();
  }

}
