package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
import net.sf.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Forrest G. Choi on 2015-05-13.
 */
public class RetrieveTest {
    RESTOfIssue rs = new RESTOfIssue();

    @Before
    public void setUp() throws Exception {
        rs.setAuth("admin:promin1006");
    }

    /*
    * 2. 이슈 정보 : 이슈 정보를 가져온다.
    * */
    @Test
    public void testGetIssueInfo() throws Exception {
        rs.setIssueUrl("guruforrest.atlassian.net", "CREAT-5");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());
        Map<String, Object> map = retrieve.getIssueInfo();

        Assert.assertTrue(map instanceof Map);
        Assert.assertNotNull(map);
        System.out.println(map.containsKey("id"));
        System.out.println(map.get("id"));
        System.out.println(map.size());
        System.out.println(map);
    }

    /*
    * 2. 이슈 정보 : 코멘트 정보를 가져온다.
    * */
    @Test
    public void testGetComments() throws Exception {
        rs.setCommentUrl("guruforrest.atlassian.net", "CREAT-5");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());

        ArrayList list = retrieve.getComments();
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertEquals(list.size(), 3);
        Assert.assertTrue(list.toString().contains("테스트 입니다."));
        Assert.assertTrue(((Map) list.get(1)).toString().contains("테스트"));
        System.out.println(((Map) list.get(1)).toString());

        JSONArray jsonArray = JSONArray.fromObject(list.get(4));
        System.out.println(jsonArray);
    }

    /**
     * Issue ID를 가져온다.
     *
     * @throws Exception
     */
    @Test
    public void testGetIssueIDs() throws Exception {
        rs.setIssueIDsUrl("guruforrest.atlassian.net", "CREAT");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());

        ArrayList<Object> object = retrieve.getIssueIDs();
        Assert.assertEquals(object.size(), 3);
        Assert.assertEquals(object.get(0), "CREAT-12");
    }

    @Test
    public void testGetCommentIDs() throws Exception {
        rs.setCommentUrl("guruforrest.atlassian.net", "CREAT-5");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());

        ArrayList<String> list = retrieve.getCommentIDs();
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertEquals(list.size(), 3);
        Assert.assertTrue(list.toString().contains("10000"));
        Assert.assertTrue(list.get(1).contains("10001"));
    }
}