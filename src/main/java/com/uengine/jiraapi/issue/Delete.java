package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import com.uengine.jiraapi.Exception.ValidateCheck;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 이슈 정보를 삭제
 */
public class Delete {
    private ValidateCheck validateCheck = new ValidateCheck();
    private Client client;
    private WebResource webResource;
    private ClientResponse response;

    public Delete(String url, String auth) {
        client = Client.create();
        webResource = client.resource(url);
        response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").delete(ClientResponse.class);
    }

    private Delete() {
    }

    /**
     * 이슈 정보를 삭제한다.
     *
     * @param auth JIRA 인증정보.ex) admin:1234
     * @param url  REST URL
     * @return 성공한 삭제 건수
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public Map<String, Object> deleteIssue(String auth, String url)  {
        validateCheck.checkNullValue(auth, url);
        Map<String, Object> map = new HashMap<String, Object>();

        int statusCode = response.getStatus();
        if (statusCode == 204) {
            map.put("isDeleted", true);
            map.put("errorCode", "");
            map.put("errorMsg", "");
        } else if (statusCode == 401) {
            map.put("isDeleted", false);
            map.put("errorCode", statusCode);
            map.put("errorMsg", "Username과 Password가 잘못되었습니다.");
        } else if (statusCode == 403) {
            String user = new String(Base64.decode(auth)).split(":")[0];
            map.put("isDeleted", false);
            map.put("errorCode", statusCode);
            map.put("errorMsg", user + "은(는) 해당 이슈에 대한 삭제 권한이 없습니다.");
        } else if (statusCode == 404) {
            map.put("isDeleted", false);
            map.put("errorCode", statusCode);
            map.put("errorMsg", "삭제할 이슈정보가 없습니다.");
        }

        return map;
    }

}
