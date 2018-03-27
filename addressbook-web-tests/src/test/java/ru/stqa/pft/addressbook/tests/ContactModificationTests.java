package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Name", "Mname", "Lname", "test address", "test@test.com", "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Name", "Mname", "Lname", "test address", "test@test.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();
    }
}
