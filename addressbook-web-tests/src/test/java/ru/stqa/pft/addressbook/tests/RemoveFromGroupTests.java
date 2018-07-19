package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(properties.getProperty("group.Name")));
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
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
    public void testRemoveFromGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();

        Contacts contactsBefore = app.db().contacts();
        ContactData removeContact = contactsBefore.iterator().next();

        if (removeContact.getGroups().size() == 0) {
            app.contact().addToGroup(removeContact, group.getId());
        }

        Groups groupBefore = app.db().contacts().getById(removeContact.getId()).getGroups();
        removeContact = app.db().contacts().getById(removeContact.getId());
        group = app.db().groups().getById(group.getId());

        app.contact().removeFromGroup(removeContact, group.getId());

        Groups groupAfter = app.db().contacts().getById(removeContact.getId()).getGroups();

        assertThat(groupBefore, equalTo(groupAfter.withAdded(group)));
    }
}
