package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        //int before = app.getGroupHelper().getGroupCount();
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        //int after = app.getGroupHelper().getGroupCount();
        // Assert.assertEquals(after, before + 1);
    }
}
