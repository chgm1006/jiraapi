package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
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
    * 2. 이슈 정보 : 이슈 정보를 가져옴.
    * */
    @Test
    public void testGetIssueInfo() throws Exception {
        rs.setIssueUrl("guruforrest.atlassian.net", "CREAT-5");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());
        Map<String, Object> map = retrieve.getIssueInfo();

        Assert.assertTrue(map instanceof Map);
        Assert.assertNotNull(map);
        System.out.println(map);
    }

    /*
    * 2. 이슈 정보 : 코멘트 정보를 가져옴.
    * */
    @Test
    public void testGetComment() throws Exception {
        rs.setCommentUrl("guruforrest.atlassian.net", "CREAT-5");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());
        ArrayList list = retrieve.getComments();
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertEquals(list.size(), 3);
        Assert.assertTrue(list.toString().contains("테스트 입니다."));
        Assert.assertTrue(((Map)list.get(1)).toString().contains("테스트"));
        System.out.println(((Map)list.get(1)).toString());
    }

    /*
    * 2. 이슈 정보 : 프로젝트 키 목록을 가져옴.
    * */
    @Test
    public void testGetProjectKeys() throws Exception {
        rs.setProjectUrl("guruforrest.atlassian.net");
        Retrieve retrieve = new Retrieve(rs.getAuth(), rs.getUrl());
        ArrayList<String> list = retrieve.getProjectKeys();
        System.out.println(list.size());

    }
}