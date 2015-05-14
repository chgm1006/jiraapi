package com.uengine.jiraapi.project;

import com.uengine.jiraapi.rest.RESTOfIssue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Forrest G. Choi on 2015-05-14.
 */
public class JIRAProjectTest {

    @Test
    public void testGetProjectInfo() throws Exception {
        RESTOfIssue rs = new RESTOfIssue("admin:promin1006");
        JIRAProject project = new JIRAProject();

        rs.setProjectUrl("guruforrest.atlassian.net");

        String resp = project.getProjectInfo(rs.getAuth(), rs.getUrl());
        System.out.println(resp);

        JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(resp);
        System.out.println("josnObject = " + jsonArray);
//        JSONArray sum = jsonObject.getJSONArray("expand");
        JSONObject sum =  (JSONObject)jsonArray.get(1);
        ArrayList arrayList = (ArrayList) jsonArray.get(0);

        System.out.println("size :: " + jsonArray.size());
    }
}