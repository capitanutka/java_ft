package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(group);
    Comparator<? super GroupData> byID = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
