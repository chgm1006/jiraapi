package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.data.IssueData;
import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Before;
import org.junit.Test;

public class UpdateTest {
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
        rs.setAuth("admin:1234");
        rs.setIssueUrl("guru-forrest.atlassian.net", "CREAT-2");
//        rs.setData(issueData.getUpdateIssueData("Bug in business logic 2222 3333", "3w 2d", "5d", "test3333", "test22222"));
        rs.setData(issueData.getUpdateIssueData("Bug in business logic 2222 3333", "3w 2d", "5d", "test3333", "test22222"));
        update = new Update(rs.getAuth(), rs.getUrl(), rs.getData());

        System.out.println(update.updateIssueOrComment());

    }

    @Test
    public void testUpdateComment() throws Exception {
        rs.setAuth("admin:1234");
        rs.setCommentUrl("guru-forrest.atlassian.net", "CREAT-1", "10001");
        rs.setData(issueData.getCommentData("test 6666 2222 2222", "Developers"));
        System.out.println(rs.getData());
        update = new Update(rs.getAuth(), rs.getUrl(), rs.getData());

        System.out.println(update.updateIssueOrComment());
    }
}