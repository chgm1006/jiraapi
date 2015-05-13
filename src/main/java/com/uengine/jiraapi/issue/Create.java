package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;

/**
 * Created by Forrest G. Choi on 2015-05-11.
 */
public class Create {

    public String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        new ValidateCheck().nullCheckValue(auth, url, data);

        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").post(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Username과 Password가 잘못되었습니다.");
        }
        return "issue successfully created";
    }
}
