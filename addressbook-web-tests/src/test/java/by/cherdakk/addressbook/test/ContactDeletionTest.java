package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("Lastname").withAddress("Address").withMobilephone("+375442020327").withEmail("test@test.by"));
    }
  }


  @Test
  public void TestContactDeletion() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().selectContact(index);
    app.contact().deleteSelectedContacts();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(before.size() - 1, after.size());
    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
