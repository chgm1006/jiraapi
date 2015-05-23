package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.ClientHandlerException;
import com.uengine.jiraapi.data.IssueData;
import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Before;
import org.junit.Test;

import javax.naming.AuthenticationException;

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
        * 1. 이슈 생성 :: 프로젝트 이슈를 생성
        * */
    @Test
    public void testCreateIssue() {
        rs.setAuth("admin:promin1006");
        rs.setIssueUrl("guruforrest.atlassian.net");
        rs.setData(issueData.getCreateIssueData("CREAT", "something's wrong 8888", "aaaa", ""));
        create = new Create(rs.getAuth(), rs.getUrl(), rs.getData());

        try {
            System.out.println(create.createIssue());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateComment() throws Exception {
        rs.setAuth("admin:promin1006");
        rs.setCommentUrl("guruforrest.atlassian.net", "CREAT-27");
        rs.setData(issueData.getCreateCommentData("test 44444", "Developers"));

        create = new Create(rs.getAuth(), rs.getUrl(), rs.getData());
        System.out.println(create.createComment());
    }
}