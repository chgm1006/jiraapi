package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;

/**
 *  이슈 정보를 가져온다.
 */
public class Retrieve {
    private ValidateCheck validateCheck = new ValidateCheck();

    /**
     * 이슈 정보를 가져온다.
     * @param auth  JIRA 인증정보.ex) admin:1234
     * @param url   REST URL
     * @return      이슈정보를 JSON 형태로 반환한다.
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public String invokeGet(String auth, String url) throws AuthenticationException, ClientHandlerException {
        validateCheck.checkNullValue(auth, url);

        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);

        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Username과 Password가 잘못되었습니다.");
        }
        return response.getEntity(String.class);
    }


}
