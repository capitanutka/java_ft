package by.cherdakk.addressbook.applicationmanager;

import by.cherdakk.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilephone());
    type(By.name("email"),contactData.getEmail());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index){
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void modifyContactForm() {
    wd.findElement(By.name("firstname")).sendKeys("X");
    wd.findElement(By.name("lastname")).sendKeys("X");
    wd.findElement(By.name("address")).sendKeys("X");
    wd.findElement(By.name("mobile")).sendKeys("X");
    wd.findElement(By.name("email")).sendKeys("X");
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@class]"));
    for (WebElement element : elements) {

      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      String firstname = element.findElement(By.xpath("./td[3]")).getText();

      Integer id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));

      contacts.add(new ContactData(id, firstname, lastname, null, null, null));
    }
    return contacts;
  }
}
