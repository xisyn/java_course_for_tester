package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost postLogin = new HttpPost(app.getProperty("web.baseUrl") + "/login_password_page.php");
        List<NameValuePair> paramsLogin = new ArrayList<>();
        paramsLogin.add(new BasicNameValuePair("username", username));
        paramsLogin.add(new BasicNameValuePair("return", "index.php"));
        postLogin.setEntity(new UrlEncodedFormEntity(paramsLogin));
        httpClient.execute(postLogin);

        HttpPost postPassword = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<NameValuePair> paramsPassword = new ArrayList<>();
        paramsPassword.add(new BasicNameValuePair("username", username));
        paramsPassword.add(new BasicNameValuePair("password", password));
        paramsPassword.add(new BasicNameValuePair("secure_session", "on"));
        paramsPassword.add(new BasicNameValuePair("return", "index.php"));
        postPassword.setEntity(new UrlEncodedFormEntity(paramsPassword));
        CloseableHttpResponse responsePassword = httpClient.execute(postPassword);
        String body = getTextFrom(responsePassword);
        return body.contains(String.format("<a href=\"/mantisbt/account_page.php\">%s</a>", username));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<a href=\"/mantisbt/account_page.php\">%s</a>", username));
    }

}
