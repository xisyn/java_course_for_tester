package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("Name", "Mname", "Lname", "test address", "test@test.com", "test1"));
    }
}
