package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Test;

/**
 * Created by Forrest G. Choi on 2015-05-14.
 */
public class DeleteTest {

    /*
    * 4. 이슈 삭제
    * */
    @Test
    public void testInvodeDelete() throws Exception {
        RESTOfIssue rs = new RESTOfIssue();
        Delete delete = new Delete();

        rs.setAuth("admin:promin1006");
        rs.setIssueUrl("guruforrest.atlassian.net", "CREAT-8?deleteSubtasks=true");

        System.out.println(rs.getUrl());
        System.out.println(delete.invodeDelete(rs.getAuth(), rs.getUrl()));
    }
}