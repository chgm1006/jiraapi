package com.uengine.jiraapi;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.rest.RESTOfIssue;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;

public class RESTClient2 {

    private static String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").post(ClientResponse.class, data);
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
        RESTOfIssue issue = new RESTOfIssue();
        /*
        * 1. 이슈 생성 :: 프로젝트 이슈를 open
        * */
        System.out.println("1. 이슈 생성");
//        System.out.println(invokePostMethod(auth, url, data));
        System.out.println(issue.getData());

        /*
        * 2. 이슈 정보
        * */
        System.out.println("2. 이슈 정보");
        String resp = invokeGetMethod(issue.getAuth(), issue.getUrl1());
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(resp);
        JSONObject sum = jsonObject.getJSONObject("fields");
        System.out.println(sum.getString("created"));
        System.out.println("sum :: " + sum);

        /*
        * 3. 이슈 업데이트
        * */
        System.out.println("3. 이슈 업데이트");
        System.out.println(invodePutMethod(issue.getAuth(), issue.getUrl2(), issue.getData1()));

        /*
        * 4. 이슈 삭제
        * */
        System.out.println("4. 이슈 삭제");
//        System.out.println(invodeDeleteMethod(issue.getAuth(), issue.getUrl3()));

    }


}
