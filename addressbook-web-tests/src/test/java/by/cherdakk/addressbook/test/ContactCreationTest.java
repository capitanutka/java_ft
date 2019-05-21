package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Name").withLastname("Lastname").withAddress("Address").withMobilephone("+375442020327").withEmail("test@test.by");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(before.size() + 1, after.size());
    before.add(contact);
    Comparator<? super ContactData> byID = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
