package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Name", "Mname", "Lname", "test address", "test@test.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePage();
    }
}
