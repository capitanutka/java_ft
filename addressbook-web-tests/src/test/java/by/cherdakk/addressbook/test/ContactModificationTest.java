package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("Lastname").withAddress("Address").withMobilephone("+375442020327").withEmail("test@test.by"));
    }
  }

  @Test
  public void TestContactModification() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("modified").withLastname("modified").withAddress("modified").withMobilephone("modified").withEmail("modified");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(before.size(), after.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byID = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
