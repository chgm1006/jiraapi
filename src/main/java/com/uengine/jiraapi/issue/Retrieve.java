package com.uengine.jiraapi.issue;

import com.sun.jersey.api.client.*;
import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * 이슈 정보를 가져온다.
 */
public class Retrieve {
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
     */
    public Retrieve(String auth, String url) {
        validateCheck.checkNullValue(auth, url);
        client = Client.create();
        webResource = client.resource(url);
        response = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);
    }

    public Retrieve() {
    }

    /**
     * 이슈 정보를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public Map<String, Object> getIssueInfo() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());
        return (Map) JSONSerializer.toJSON(response.getEntity(String.class));
    }

    /**
     * 이슈 ID를 가져온다.
     *
     * @return get Issue ids list
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public ArrayList<Object> getIssueIDs() {
        ArrayList<Object> list = new ArrayList<Object>();

        try {
            validateCheck.getStatusException(response.getStatus());
            Map<String, Object> map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
            JSONArray jsonArray = JSONArray.fromObject(map.get("sections"));
            JSONArray jsonArray1 = JSONArray.fromObject(((Map<String, Object>) jsonArray.get(0)).get("issues"));

            Iterator it = jsonArray1.iterator();
            while (it.hasNext()) {
                list.add(((Map)it.next()).get("key"));
            }
        } catch (ClientHandlerException e) {
            e.printStackTrace();
        } catch (UniformInterfaceException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 이슈의 코멘트 정보를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public ArrayList<Object> getComments() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());
        Map<String, Object> map = (Map<String, Object>) JSONSerializer.toJSON(response.getEntity(String.class));
        JSONArray jsonArray = JSONArray.fromObject(map.get("comments"));

        ArrayList<Object> list = new ArrayList<Object>();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            list.add((Map) it.next());
        }
        return list;
    }

    /**
     * 이슈의 코멘트 ID 리스트를 가져온다.
     *
     * @return 이슈정보를 JSON 형태로 반환한다.
     * @throws AuthenticationException
     * @throws ClientHandlerException
     */
    public ArrayList<String > getCommentIDs() throws AuthenticationException, ClientHandlerException {
        validateCheck.getStatusException(response.getStatus());

        ArrayList<String> list = new ArrayList<String>();
        Iterator it = getComments().iterator();
        while (it.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) it.next();
            list.add((String) map.get("id"));
        }

        return list;
    }

}
