package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import by.cherdakk.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Name").withLastname("Lastname").withAddress("Address").withMobilephone("+375442020327").withEmail("test@test.by");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(before.size() + 1, after.size());

    contact.withId(after.stream().mapToInt((o) -> o.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(contact)));
  }

}
