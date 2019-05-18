package by.cherdakk.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;

import static by.cherdakk.addressbook.MyFirstProgramm.distance;

public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(3,7.2);
    Point p2 = new Point(3,4.2);
    Point p3 = new Point(8,7.2);
    Assert.assertEquals(distance(p1,p2), 3.0);
    Assert.assertEquals(distance(p1,p3), 5.0);
  }
}
