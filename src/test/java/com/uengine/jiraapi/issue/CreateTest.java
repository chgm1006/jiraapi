package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.ClientHandlerException;
import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Test;

import javax.naming.AuthenticationException;

public class CreateTest {

    /*
    * 1. 이슈 생성 :: 프로젝트 이슈를 open
    * */
    @Test
    public void testInvokePost() {
        RESTOfIssue rs = new RESTOfIssue();
        Create create = new Create();

        String data =
                "{\"fields\"" +
                        ":{\"project\"" +
                        ":{\"key\":\"CREAT\"}," +
                        "\"summary\":\"createProject3 \",\"issuetype\":{\"name\":\"Task\"}}}";


        rs.setAuth("admin:promin1006");
        rs.setIssueUrl("guruforrest.atlassian.net");
        rs.setData(data);
        System.out.println(rs.getUrl());

        try {
            System.out.println(create.invokePost(rs.getAuth(), rs.getUrl(), rs.getData()));
            System.out.println(rs.getData());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        }
    }
}