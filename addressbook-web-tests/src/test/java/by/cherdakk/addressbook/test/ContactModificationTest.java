package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import by.cherdakk.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("Lastname").withAddress("Address").withMobilePhone("+375442020327").withEmail("test@test.by"));
    }
  }

  @Test
  public void TestContactModification() throws Exception {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("modified").withLastname("modified").withAddress("modified").withMobilePhone("modified").withEmail("modified");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo( before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
