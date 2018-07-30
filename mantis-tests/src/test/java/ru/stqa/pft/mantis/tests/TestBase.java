package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    protected Properties properties;

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        properties = new Properties();
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/test/resources/local.properties");
        properties.load(fileInputStream);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
