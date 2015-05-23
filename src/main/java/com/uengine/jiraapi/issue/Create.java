package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;

/**
 * 이슈를 생성
 */
public class Create {
    private ValidateCheck validateCheck = new ValidateCheck();
    private Client client = null;
    private WebResource webResource = null;
    private ClientResponse response = null;

    /**
     * auth와 url을 설정한다.
     * 이 클래스의 메소드들을 사용하기 위해서는 auth와 url을 먼저 설정 해줘야 한다.
     *
     * @param auth JIRA 인증정보.ex) admin:1234
     * @param url  REST URL
     * @param data json 형식의 생성할 이슈의 필드값.
     */
    public Create(String auth, String url, String data) {
        validateCheck.checkNullValue(auth, url);
        client = Client.create();
        webResource = client.resource(url);
        response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").post(ClientResponse.class, data);
    }

    private Create() {
    }

    /**
     * 이슈를 생성한다.
     *
     * @return 생성된 이슈를 json 형식으로 반환
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public String createIssue() throws AuthenticationException, ClientHandlerException {
        try {
            validateCheck.getStatusException(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return response.getEntity(String.class);
    }

    /**
     * 코멘트를 생성한다.
     *
     * @return 생성된 코멘트를 json 형식으로 반환
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public String createComment() throws AuthenticationException, ClientHandlerException {
        try {
            validateCheck.getStatusException(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return response.getEntity(String.class);
    }
}
