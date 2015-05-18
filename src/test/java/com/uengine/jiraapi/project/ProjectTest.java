package com.uengine.jiraapi.project;

import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Forrest G. Choi on 2015-05-14.
 */
public class ProjectTest {
    RESTOfIssue rs = new RESTOfIssue();

    @Before
    public void setUp() throws Exception {
        rs.setAuth("admin:promin1006");
    }

    /**
     * 프로젝트의 정보를 가져온다.
     *
     * @throws Exception
     */
    @Test
    public void testGetProjectInfo() throws Exception {
        RESTOfIssue rs = new RESTOfIssue("admin:promin1006");
        rs.setProjectUrl("guruforrest.atlassian.net");
        Project project = new Project(rs.getAuth(), rs.getUrl());
        System.out.println(rs.getUrl());

//        Map<String, Object> map = project.getProjectInfo();
//        System.out.println(map);

    }
}