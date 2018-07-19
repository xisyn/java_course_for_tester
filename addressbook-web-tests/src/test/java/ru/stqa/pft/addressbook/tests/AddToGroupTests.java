package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(properties.getProperty("group.Name")));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData()
                    .withFirstname(properties.getProperty("contact.Firstname"))
                    .withMiddlename(properties.getProperty("contact.Middlename"))
                    .withLastname(properties.getProperty("contact.Lastname"))
                    .withAddress(properties.getProperty("contact.Address"))
                    .withEmail(properties.getProperty("contact.Email")));
        }
    }

    @Test
    public void testAddToGroup() {
        Contacts contactsBefore = app.db().contacts();
        ContactData addContact = contactsBefore.iterator().next();

        Groups groupBefore = addContact.getGroups();

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();

        app.contact().addToGroup(addContact, group.getId());

        Groups groupAfter = app.db().contacts().getById(addContact.getId()).getGroups();

        assertThat(groupAfter, equalTo(groupBefore.withAdded(group)));
    }
}
