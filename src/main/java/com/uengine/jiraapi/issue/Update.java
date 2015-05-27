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
     * 이슈 혹은 코멘트 정보를 업데이트.
     *
     * @return 결과를 Map으로 반환
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public Map<String, Object> updateIssueOrComment() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response);

        Map<String, Object> map = new HashMap<String, Object>();
        int status = response.getStatus();

        if (status == 200 || status == 201 || status == 204) {
            map.put("isUpdated", true);
            map.put("errorCode", "");
            map.put("errorMsg", "");
        }else if (status == 401) {
            map.put("isUpdated", false);
            map.put("errorCode", status);
            map.put("errorMsg", "Username과 Password가 잘못되었습니다.");
        }else if (status == 502) {
            map.put("isUpdated", false);
            map.put("errorCode", status);
            map.put("errorMsg", "URL 정보가 잘못되었습니다.");
        }

        return map;
    }

}
