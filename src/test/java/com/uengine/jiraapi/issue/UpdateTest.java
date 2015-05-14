package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Test;

/**
 * Created by Forrest G. Choi on 2015-05-13.
 */
public class UpdateTest {

    /*
    * 3. 이슈 업데이트
    * */
    @Test
    public void testInvodePut() throws Exception {
        RESTOfIssue rs = new RESTOfIssue();
        Update update = new Update();
        String data = "{\"fields\":{\"assignee\":{\"name\":\"vinodh\"}}}";

        rs.setAuth("admin:promin1006");
        rs.setIssueUrl("guruforrest.atlassian.net", "CREAT-5");
        rs.setData(data);
        System.out.println(update.invodePut(rs.getAuth(), rs.getUrl(), rs.getData()));

    }
}