package com.uengine.jiraapi.project;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * All about Project information
 */
public class Project {
    private ValidateCheck validateCheck = new ValidateCheck();
    private Client client = Client.create();
    private WebResource webResource = null;
    private ClientResponse response = null;

    public Project() {
    }

    /**
     * auth와 url을 설정한다.
     *
     * @param auth
     * @param url
     */
    public Project(String auth, String url) {
        validateCheck.checkNullValue(auth, url);
        webResource = client.resource(url);
        response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);
    }


    /**
     * get project information list
     *
     * @return Project Information Lists
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public ArrayList<Object> getAllProjectInfo() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());
        JSONArray jsonArray = JSONArray.fromObject(response.getEntity(String.class));

        ArrayList<Object> list = new ArrayList<Object>();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }


    /**
     * get Project keys lists
     *
     * @return Project keys list
     * @throws AuthenticationException
     */
    public ArrayList<String> getProjectKeys() throws AuthenticationException {
        validateCheck.getStatusException(response.getStatus());
        JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(response.getEntity(String.class));
        ArrayList<String> arrayList = new ArrayList<String>();

        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add((String) JSONObject.fromObject(it.next()).get("key"));
        }
        return arrayList;
    }

    /**
     * get Project names list
     *
     * @return Project keys list
     * @throws AuthenticationException
     */
    public ArrayList<String> getProjectNames() throws AuthenticationException {
        validateCheck.getStatusException(response.getStatus());
        JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(response.getEntity(String.class));
        ArrayList<String> arrayList = new ArrayList<String>();

        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add((String) JSONObject.fromObject(it.next()).get("name"));
        }
        return arrayList;
    }
}

