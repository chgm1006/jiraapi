package com.uengine.jiraapi;

import com.sun.jersey.api.client.ClientHandlerException;
import com.uengine.jiraapi.issue.Delete;
import com.uengine.jiraapi.issue.Retrieve;
import com.uengine.jiraapi.issue.Update;
import com.uengine.jiraapi.rest.RESTOfIssue;

import javax.naming.AuthenticationException;

public class RESTClient2 {


    public static void main(String[] args) throws AuthenticationException, ClientHandlerException {
        RESTOfIssue rs = new RESTOfIssue();
        Retrieve retrieve = new Retrieve();
        Update update = new Update();
        Delete delete = new Delete();








        /*
        * 4. 이슈 삭제
        * */
        System.out.println("4. 이슈 삭제");
//        System.out.println(delete.invodeDeleteMethod(rs.getAuth(), rs.getUrl3()));

    }


}
