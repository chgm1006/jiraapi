package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * 이슈 정보를 가져온다.
 */
public class Retrieve {
    private ValidateCheck validateCheck = new ValidateCheck();
    private Client client = null;
    private WebResource webResource = null;
    private ClientResponse response = null;

    /**
     * auth와 url을 설정한다.
     *
     * @param auth JIRA 인증정보.ex) admin:1234
     * @param url  REST URL
     */
    public Retrieve(String auth, String url) {
        validateCheck.checkNullValue(auth, url);
        client = Client.create();
        webResource = client.resource(url);
        response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);
    }


    /**
     * 이슈 정보를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public Map<String, Object> getIssueInfo() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());
        return (Map) JSONSerializer.toJSON(response.getEntity(String.class));
    }

    /**
     * 이슈의 코멘트 정보를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public ArrayList<Object> getComments() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());
        Map<String, Object> map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
        JSONArray jsonArray = JSONArray.fromObject(map.get("comments"));

        ArrayList<Object> list = new ArrayList<Object>();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            list.add((Map) it.next());
        }
        return list;
    }

}
