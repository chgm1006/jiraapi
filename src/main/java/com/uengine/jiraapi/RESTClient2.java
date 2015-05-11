package com.uengine.jiraapi;

import com.sun.jersey.api.client.ClientHandlerException;
import com.uengine.jiraapi.rest.RESTOfIssue;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.naming.AuthenticationException;

public class RESTClient2 {


    public static void main(String[] args) throws AuthenticationException, ClientHandlerException {
        RESTOfIssue rs = new RESTOfIssue();

        /*
        * 1. 이슈 생성 :: 프로젝트 이슈를 open
        * */
        System.out.println("1. 이슈 생성");
//        System.out.println(invokePostMethod(auth, url, data));
        System.out.println(rs.getData());

        /*
        * 2. 이슈 정보
        * */
        System.out.println("2. 이슈 정보");
        String resp = invokeGetMethod(rs.getAuth(), rs.getUrl1());
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(resp);
        JSONObject sum = jsonObject.getJSONObject("fields");
        System.out.println(sum.getString("created"));
        System.out.println("sum :: " + sum);

        /*
        * 3. 이슈 업데이트
        * */
        System.out.println("3. 이슈 업데이트");
        System.out.println(invodePutMethod(rs.getAuth(), rs.getUrl2(), rs.getData1()));

        /*
        * 4. 이슈 삭제
        * */
        System.out.println("4. 이슈 삭제");
//        System.out.println(invodeDeleteMethod(issue.getAuth(), issue.getUrl3()));

    }


}
