package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.GroupData;
import by.cherdakk.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("modified").withHeader("modified").withFooter("modified");
    app.group().modify(group);
    assertThat(app.group().count(),equalTo( before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}
