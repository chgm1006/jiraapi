package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;

/**
 * 이슈 정보를 업데이트 한다.
 */
public class Update {
    ValidateCheck validateCheck = new ValidateCheck();


    /**
     * 이슈정보를 업데이트.
     *
     * @param auth  JIRA 인증정보.ex) admin:1234
     * @param url   REST URL
     * @param data  JSON 형태의 데이터
     * @return
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public String invodePut(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        validateCheck.checkNullValue(auth, url, data);
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").put(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Username과 Password가 잘못되었습니다.");
        }
        return "업데이트가 완료되었습니다.";
    }
}
