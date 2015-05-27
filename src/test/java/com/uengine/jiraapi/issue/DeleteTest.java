package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
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
    public void testdeleteIssue() {
        rs.setAuth("admin:1234");
        rs.setIssueUrl("guru-forrest.atlassian.net", "CREAT-1");
        Delete delete = new Delete(rs.getAuth(), rs.getUrl());

        Map<String, Object> map = delete.deleteIssueOrComment(rs.getAuth(), rs.getUrl());
        System.out.println(map);

//        Assert.assertEquals(map.get("errorCode"), 404);
    }

    /*
   * 4. 이슈 삭제
   * */
    @Test
    public void testdeleteComment() {
        rs.setAuth("admin:1234");
        rs.setCommentUrl("guru-forrest.atlassian.net", "CREAT-2", "10010");
        Delete delete = new Delete(rs.getAuth(), rs.getUrl());

        Map<String, Object> map = delete.deleteIssueOrComment(rs.getAuth(), rs.getUrl());
        System.out.println(map);

//        Assert.assertEquals(map.get("errorCode"), 404);
    }
}