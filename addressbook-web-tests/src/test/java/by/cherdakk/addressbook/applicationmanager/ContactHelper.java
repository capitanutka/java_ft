package by.cherdakk.addressbook.applicationmanager;

import by.cherdakk.addressbook.model.ContactData;
import by.cherdakk.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    contactsCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact);
    submitContactModification();
    contactsCache = null;
    returnToHomePage();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("email"),contactData.getEmail());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void delete(ContactData deletedContact) {
    selectContact(deletedContact.getId());
    deleteSelectedContacts();
    contactsCache = null;
  }

  public void initContactModification(int id){
    wd.findElement(By.xpath("//input[@id='" + id + "']/../..//td[8]//img")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@class]"));
    for (WebElement element : elements) {
      Integer id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      String firstname = element.findElement(By.xpath("./td[3]")).getText();
      String allPhones = element.findElement(By.xpath("./td[6]")).getText();
      String allEmails = element.findElement(By.xpath("./td[5]")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withPhones(allPhones).withEmails(allEmails));
    }
    return contacts;
  }

  private Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }

    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@class]"));
    for (WebElement element : elements) {
      Integer id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      String firstname = element.findElement(By.xpath("./td[3]")).getText();
      String allPhones = element.findElement(By.xpath("./td[6]")).getText();
      String allEmails = element.findElement(By.xpath("./td[5]")).getText();
      contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withPhones(allPhones).withEmails(allEmails));
    }
    return new Contacts(contactsCache);
  }

  public int count() {
    return wd.findElements(By.xpath("//tr[@class]")).size();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}