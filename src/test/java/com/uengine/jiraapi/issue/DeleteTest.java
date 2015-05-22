package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class DeleteTest {
    private RESTOfIssue rs;

    @Before
    public void setUp() {
        rs = new RESTOfIssue();
    }

    /*
    * 4. 이슈 삭제
    * */
    @Test
    public void testInvokeDelete() {
        rs.setAuth("admin:promin1006");
        rs.setIssueUrl("guruforrest.atlassian.net", "CREAT-5");
        Delete delete = new Delete(rs.getUrl(), rs.getAuth());

        Map<String, Object> map = delete.invokeDelete(rs.getAuth(), rs.getUrl());
        System.out.println(map);

        Assert.assertTrue(map != null);
        Assert.assertEquals(map.get("errorCode"), 404);
    }
}