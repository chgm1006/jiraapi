package com.uengine.jiraapi.project;

import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Forrest G. Choi on 2015-05-14.
 */
public class ProjectTest {
    RESTOfIssue rs = new RESTOfIssue();

    @Before
    public void setUp() throws Exception {
        rs.setAuth("chgm1006:1234");
    }

    /**
     * 프로젝트의 정보를 가져온다.
     *
     * @throws Exception
     */
    @Test
    public void testGetAllProjectInfo() throws Exception {
        rs.setProjectUrl("guruforrest.atlassian.net");
        Project project = new Project(rs.getAuth(), rs.getUrl());

        ArrayList<Object> list = project.getAllProjectInfo();
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(((Map) list.get(0)).get("self"), "https://guruforrest.atlassian.net/rest/api/2/project/10001");

        System.out.println(list);
    }


    /*
    * 프로젝트 키 목록을 가져온다.
    * */
    @Test
    public void testGetProjectKeys() throws Exception {
        rs.setProjectUrl("guruforrest.atlassian.net");
        Project retrieve = new Project(rs.getAuth(), rs.getUrl());
        ArrayList<String> list = retrieve.getProjectKeys();
        Assert.assertEquals(list.size(), 2);
        Assert.assertTrue(list.get(0).equals("CREAT"));
        System.out.println(list.get(0));
    }

    /*
    * 프로젝트 Name 목록을 가져온다.
    * */
    @Test
    public void testGetProjectNames() throws Exception {
        rs.setProjectUrl("guruforrest.atlassian.net");
        Project retrieve = new Project(rs.getAuth(), rs.getUrl());
        ArrayList<String> list = retrieve.getProjectNames();
        Assert.assertEquals(list.size(), 2);
        Assert.assertTrue(list.get(1).equals("jira-api"));
        System.out.println(list.get(1));
    }
}