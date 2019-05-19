package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class TestContactModification extends TestBase {

  @Test
  public void TestContactModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "Lastname", "Address", "+375442020327", "test@test.by"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"modified", "modified", "modified", "modified", "modified");
    app.getContactHelper().fillContactForm(contact);
//   app.getContactHelper().modifyContactForm();
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byID = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byID);
    after.sort(byID);

    Assert.assertEquals(before, after);
  }

}
