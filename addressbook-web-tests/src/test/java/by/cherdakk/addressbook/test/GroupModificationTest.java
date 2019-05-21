package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void TestGroupModification() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData().withId(before.get(index).getId()).withName("modified").withHeader("modified").withFooter("modified");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(before.size(), after.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byID = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }
}
