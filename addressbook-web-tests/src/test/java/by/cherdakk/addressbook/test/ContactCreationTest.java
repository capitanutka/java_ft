package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Name").withLastname("Lastname").withAddress("Address").withMobilephone("+375442020327").withEmail("test@test.by");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(before.size() + 1, after.size());

    contact.withId(after.stream().mapToInt((o) -> o.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
