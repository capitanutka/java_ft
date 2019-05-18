package by.cherdakk.addressbook.test;

import by.cherdakk.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class TestGroupModification extends TestBase {

  @Test
  public void TestGroupModification() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().modifyGroupForm();
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();



  }
}
