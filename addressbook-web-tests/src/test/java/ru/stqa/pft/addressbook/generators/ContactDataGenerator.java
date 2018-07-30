package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator extends BaseDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    protected ContactDataGenerator() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(),
                        contact.getLastname(), contact.getAddress(), contact.getEmail()/*, contact.getGroup()*/, contact.getPhoto()));
            }
        }
    }

    public void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) throws IOException {
        File photo = new File("src/test/resources/stru.png");
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format(properties.getProperty("contact.Firstname") + "%s", i))
                    .withMiddlename(String.format(properties.getProperty("contact.Middlename") + "%s", i))
                    .withLastname(String.format(properties.getProperty("contact.Lastname") + "%s", i))
                    .withAddress(String.format(properties.getProperty("contact.Address") + "%s", i))
                    .withEmail(String.format(properties.getProperty("contact.Email") + "%s", i))
                    //.withGroup(String.format(properties.getProperty("contact.Group") + "%s", i))
                    .withPhoto(photo));
        }
        return contacts;
    }

}
