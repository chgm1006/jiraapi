package com.uengine.jiraapi.data;

import org.junit.Before;
import org.junit.Test;

public class IssueDataTest {
    IssueData issueData = null;

    @Before
    public void setUp() throws Exception {
        issueData = new IssueData();
    }

    @Test
    public void testGetCreateIssueData() throws Exception {
        String data = issueData.getCreateIssueData("CREAT", "something's wrong 4444", "", "");
        System.out.println(data);
    }

}