package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.*;
import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 이슈 정보를 가져온다.
 */
public class Retrieve {
    private ValidateCheck validateCheck = new ValidateCheck();
    private Client client;
    private WebResource webResource;
    private ClientResponse response;

    /**
     * auth와 url을 설정한다.
     * 이 클래스의 메소드들을 사용하기 위해서는 auth와 url을 먼저 설정 해줘야 한다.
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

    private Retrieve() {
    }

    /**
     * 이슈 정보를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     * @throws ClientHandlerException
     */
    public Map<String, Object> getIssueInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            validateCheck.getStatusException(response);
            map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        } catch (UniformInterfaceException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 이슈 ID를 가져온다.
     *
     * @return get Issue ids list
     * @throws ClientHandlerException
     */
    public ArrayList<Object> getIssueIDs() {
        ArrayList<Object> list = new ArrayList<Object>();

        try {
            validateCheck.getStatusException(response);
            Map<String, Object> map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
            JSONArray jsonArray = JSONArray.fromObject(map.get("sections"));
            JSONArray jsonArray1 = JSONArray.fromObject(((Map<String, Object>) jsonArray.get(0)).get("issues"));

            for (Object obj : jsonArray1) {
                list.add(((Map) obj).get("key"));
            }
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        } catch (UniformInterfaceException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 이슈의 코멘트 정보를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     */
    public ArrayList<Object> getComments() {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        ArrayList<Object> list = new ArrayList<Object>();

        try {
            validateCheck.getStatusException(response);
            map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
            jsonArray = JSONArray.fromObject(map.get("comments"));
            for (Object obj : jsonArray) {
                list.add(obj);
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        } catch (UniformInterfaceException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 이슈의 코멘트 ID 리스트를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     */
    public ArrayList<String> getCommentIDs() {
        try {
            validateCheck.getStatusException(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<String>();
        for (Object obj : getComments()) {
            Map<String, Object> map = (Map<String, Object>) obj;
            list.add((String) map.get("id"));
        }

        return list;
    }

}
