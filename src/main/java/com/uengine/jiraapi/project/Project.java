package com.uengine.jiraapi.project;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;
import java.util.Map;

public class Project {
    ValidateCheck validateCheck = new ValidateCheck();
    Client client = Client.create();
    WebResource webResource = null;
    ClientResponse response = null;

    public Project() {
    }

    public Project(String auth, String url) {
        validateCheck.checkNullValue(auth, url);
        webResource = client.resource(url);
        response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);
    }

    public Map<String, Object> getProjectInfo() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());
        System.out.println(JSONSerializer.toJSON(response.getEntity(String.class)));
//        Map<String, Object> map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
        JSONArray jsonArray = JSONArray.fromObject(response.getEntity(String.class));
        System.out.println(jsonArray.size());

        return null;
    }
}

