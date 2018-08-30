package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        String status = issues.getAsJsonObject().get("status").toString();
        if (status.equals("closed") || status.equals("resolved")){
            return false;
        } else {
            return true;
        }
    }

}
