package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.ClientHandlerException;
import com.uengine.jiraapi.data.IssueData;
import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Before;
import org.junit.Test;

public class CreateTest {

    private RESTOfIssue rs;
    private Create create;
    private IssueData issueData;

    @Before
    public void setUp() throws Exception {
        rs = new RESTOfIssue();
        issueData = new IssueData();
    }

    /*
        * 이슈를 생성
        * */
    @Test
    public void testCreateIssue() {
        rs.setAuth("admin:1234");
        rs.setIssueUrl("guru-forrest.atlassian.net");
        rs.setData(issueData.getCreateIssueData("CREAT", "something's wrong 8888", "aaaa", ""));

        create = new Create(rs.getAuth(), rs.getUrl(), rs.getData());

        try {
            System.out.println(create.createIssueOrComment());
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 코멘트를 생성
     */
    @Test
    public void testCreateComment() {
        rs.setAuth("admin:1234");
        rs.setCommentUrl("guru-forrest.atlassian.net", "CREAT-2", "");
        rs.setData(issueData.getCommentData("test 88888", "Developers"));

        create = new Create(rs.getAuth(), rs.getUrl(), rs.getData());
        try {
            System.out.println(create.createIssueOrComment());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}