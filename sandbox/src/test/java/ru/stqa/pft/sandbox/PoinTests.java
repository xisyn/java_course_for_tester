package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PoinTests {
    @Test
    public void testDistance1() {
        Point p1 = new Point(2,2);
        Point p2 = new Point(1,1);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }
    @Test
    public void testDistance2() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }
}
