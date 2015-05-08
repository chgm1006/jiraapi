package com.uengine.jiraapi.rest;

import com.sun.jersey.core.util.Base64;

/**
 * Created by Forrest G. Choi on 2015-05-07.
 */
public class RESTOfIssue {
    private String auth = new String(Base64.encode("admin:promin1006"));
    private String url = "https://guruforrest.atlassian.net/rest/api/2/issue";
    private String data =
            "{\"fields\"" +
                    ":{\"project\"" +
                    ":{\"key\":\"CREAT\"}," +
                    "\"summary\":\"createProject3 \",\"issuetype\":{\"name\":\"Task\"}}}";

    private String url1 = "https://guruforrest.atlassian.net/rest/api/2/issue/CREAT-1";
    private String url2 = "https://guruforrest.atlassian.net/rest/api/2/issue/CREAT-1";
    private String data1 = "{\"fields\":{\"assignee\":{\"name\":\"vinodh\"}}}";

    private String url3 = "https://guruforrest.atlassian.net/rest/api/2/issue/CREAT-1";

    public RESTOfIssue(String auth) {
        this.auth = auth;
    }

    public RESTOfIssue() {

    }

    public String getAuth() {

        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }
}
