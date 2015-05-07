package com.uengine.jiraapi;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

import javax.naming.AuthenticationException;

/**
 * Created by Administrator on 2015-05-06.
 */
public class RESTClient2 {

    static String auth = new String(Base64.encode("admin:promin1006"));
    static String url = "https://guruforrest.atlassian.net/rest/api/2/issue";
    static String data =
            "{\"fields\"" +
            ":{\"project\"" +
            ":{\"key\":\"JIRAAPI\"}," +
            "\"summary\":\"REST Test \",\"issuetype\":{\"name\":\"Task\"}}}";

    static String url1 = "https://guruforrest.atlassian.net/rest/api/2/issue/JIRAAPI-1";
    static String url2 = "https://guruforrest.atlassian.net/rest/api/2/issue/JIRAAPI-1";
    static String data1 = "{\"fields\":{\"assignee\":{\"name\":\"vinodh\"}}}";

    static String url3 = "https://guruforrest.atlassian.net/rest/api/2/issue/JIRAAPI-1";

    private static String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").post(ClientResponse.class, data);
        System.out.println(auth);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return "issue successfully created";
    }

    private static String invokeGetMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);

        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return response.getEntity(String.class);
    }

    private static String invodePutMethod(String auth, String url, String data1) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").put(ClientResponse.class, data1);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return "success";
    }

    private static String invodeDeleteMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").delete(ClientResponse.class);

        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return "successfully deleted";
    }

    public static void main(String[] args) throws AuthenticationException, ClientHandlerException {
        /*
        * 1. 이슈 생성
        * */
        System.out.println(invokePostMethod(auth, url, data));

        /*
        * 2. 이슈 정보
        * */
//        String resp = invokeGetMethod(auth, url1);
//        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(resp);
//        JSONObject sum = jsonObject.getJSONObject("fields");
//        System.out.println(sum.getString("summary"));

        /*
        * 3. 이슈 업데이트
        * */
//        System.out.println(invodePutMethod(auth, url2, data1));

        /*
        * 4. 이슈 삭제
        * */
//        System.out.println(invodeDeleteMethod(auth, url3));

    }



}
