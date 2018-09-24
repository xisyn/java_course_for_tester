package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PageHelper extends HelperBase {

    public PageHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.xpath(".//input[@value='Login']"));
    }

    public void resetUserPassword(String username) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.xpath("//a[text()='" + username + "']"));
        click(By.xpath("//input[@value='Reset Password']"));
    }

}
