package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Name").withMiddlename("Mname")
                    .withLastname("Mname").withAddress("test address").withEmail("test@test.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Lname")
                .withFirstname("Name").withAddress("test address").withEmail("test@test.com");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
        assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
