package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.annotations.*;

public class TestContactCreation extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Name", "Lastname", "Address", "+375442020327", "test@test.by"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
