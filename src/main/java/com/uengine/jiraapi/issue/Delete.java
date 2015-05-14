package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;

/**
 * 이슈 정보를 삭제
 */
public class Delete {
    private ValidateCheck validateCheck = new ValidateCheck();

    /**
     * 이슈 정보를 삭제한다.
     *
     * @param auth  JIRA 인증정보.ex) admin:1234
     * @param url   REST URL
     * @return
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public String invodeDelete(String auth, String url) throws AuthenticationException, ClientHandlerException {
        validateCheck.checkNullValue(auth, url);

        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").delete(ClientResponse.class);

        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Username과 Password가 잘못되었습니다.");
        } else if (statusCode == 404) {
            throw new AuthenticationException("삭제할 이슈정보를 찾지 못했습니다.");
        }
        return "이슈를 삭제하였습니다.";
    }

}
