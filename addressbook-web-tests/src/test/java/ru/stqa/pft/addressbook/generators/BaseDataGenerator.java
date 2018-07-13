package ru.stqa.pft.addressbook.generators;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseDataGenerator {

    protected final Properties properties;

    protected BaseDataGenerator() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/test/resources/local.properties");
        properties.load(fileInputStream);
    }
}
