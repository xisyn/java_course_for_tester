package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Name", "Mname", "Lname", "test address", "test@test.com"));
        submitContactCreation();
        returnToGroupPage();
    }
}
