package com.uengine.jiraapi.issue;

import com.uengine.jiraapi.rest.RESTOfIssue;
import org.junit.Test;

public class CreateTest {

    @Test
    public void testInvokePostMethod() throws Exception {
        RESTOfIssue rs = new RESTOfIssue();
        Create create = new Create();
        /*
        * 1. 이슈 생성 :: 프로젝트 이슈를 open
        * */
        System.out.println("1. 이슈 생성");
        System.out.println(create.invokePostMethod(rs.getAuth(), rs.getUrl(), rs.getData()));
        System.out.println(rs.getData());
    }
}