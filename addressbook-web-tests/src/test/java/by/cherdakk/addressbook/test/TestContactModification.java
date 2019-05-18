package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class TestContactModification extends TestBase {

  @Test
  public void TestContactModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "Lastname", "Address", "+375442020327", "test@test.by"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().modifyContactForm();
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
