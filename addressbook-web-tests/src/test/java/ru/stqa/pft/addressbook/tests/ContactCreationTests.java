package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Name").withMiddlename("Mname")
                .withLastname("Lname").withAddress("test address").withEmail("test@test.com").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Name").withMiddlename("Mname")
                .withLastname("Lname").withAddress("test address").withEmail("test@test.com").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.size()));
    }
}
