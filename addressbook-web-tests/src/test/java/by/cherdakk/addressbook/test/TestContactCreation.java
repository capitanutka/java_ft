package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class TestContactCreation extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData("Name", "Lastname", "Address", "+375442020327", "test@test.by");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(before.size() + 1, after.size());

    before.add(contact);

    Comparator<? super ContactData> byID = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byID);
    after.sort(byID);

    Assert.assertEquals(before, after);
  }

}
