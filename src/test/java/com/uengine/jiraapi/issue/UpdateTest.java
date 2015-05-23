package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.data.IssueData;
import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Forrest G. Choi on 2015-05-13.
 */
public class UpdateTest {
    String data = "{\"fields\":{\"assignee\":{\"name\":\"vinodh\"}}}";
    private RESTOfIssue rs;
    private Update update;
    private IssueData issueData;

    @Before
    public void setUp() throws Exception {
        rs = new RESTOfIssue();
        issueData = new IssueData();
    }

    /*
    * 3. 이슈 업데이트
    * */
    @Test
    public void testUpdateIssue() throws Exception {
        rs.setAuth("admin:promin1006");
        rs.setIssueUrl("guruforrest.atlassian.net", "CREAT-21");
        rs.setData(issueData.getUpdateIssueData());
        System.out.println(rs.getData());
        update = new Update(rs.getAuth(), rs.getUrl(), rs.getData());

        System.out.println(update.updateIssue());

    }
}