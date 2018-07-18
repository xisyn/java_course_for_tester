package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname(properties.getProperty("contact.Firstname"))
                    .withMiddlename(properties.getProperty("contact.Middlename"))
                    .withLastname(properties.getProperty("contact.Lastname"))
                    .withAddress(properties.getProperty("contact.Address"))
                    .withEmail(properties.getProperty("contact.Email"))
                    .inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModification() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname(properties.getProperty("contact.Firstname"))
                .withMiddlename(properties.getProperty("contact.Middlename"))
                .withLastname(properties.getProperty("contact.Lastname"))
                .withAddress(properties.getProperty("contact.Address"))
                .withEmail(properties.getProperty("contact.Email"))
                .inGroup(groups.iterator().next());
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
