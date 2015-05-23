package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 이슈 정보를 업데이트 한다.
 */
public class Update {
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
     * @param data json 형식의 생성할 이슈의 필드값.
     */
    public Update(String auth, String url, String data) {
        validateCheck.checkNullValue(auth, url, data);
        this.client = Client.create();
        this.webResource = client.resource(url);
        this.response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").put(ClientResponse.class, data);
    }

    private Update() {
    }


    /**
     * 이슈정보를 업데이트.
     *
     * @return
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public String updateIssue() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response);
        System.out.println(response.getStatus());
        Map<String, Object> map = new HashMap<String, Object>();
        if (response.getStatus() == 204) {
        }
        return "업데이트가 완료되었습니다.";
    }
}
