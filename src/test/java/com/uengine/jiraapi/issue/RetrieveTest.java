package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.Test;

/**
 * Created by Forrest G. Choi on 2015-05-13.
 */
public class RetrieveTest {

    /*
    * 2. 이슈 정보 : 이슈 정보를 가져옴.
    * */
    @Test
    public void testInvokeGet() throws Exception {
        RESTOfIssue rs = new RESTOfIssue();
        Retrieve retrieve = new Retrieve();

        rs.setAuth("admin:promin1006");
        rs.setUrl("guruforrest.atlassian.net", "CREAT-8");
        System.out.println(rs.getUrl());

        String resp = retrieve.invokeGet(rs.getAuth(), rs.getUrl());
        System.out.println(resp);

        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(resp);
        JSONObject sum = jsonObject.getJSONObject("fields");
//        String  sum = (String) jsonObject.getString("comment");
        System.out.println(sum.get("comment"));
        System.out.println("sum :: " + sum);
    }
}