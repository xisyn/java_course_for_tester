package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithibTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("d23092fec22fefd2262dcb0368fcc72fd02691f1");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("xisyn", "java_course_for_tester")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
