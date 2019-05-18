package by.cherdakk.addressbook.test;

import org.testng.annotations.Test;

public class TestGroupModification extends TestBase {

  @Test
  public void TestGroupModification() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().modifyGroupForm();
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();



  }
}
