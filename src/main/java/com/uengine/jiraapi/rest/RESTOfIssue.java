package com.uengine.jiraapi.rest;

import com.sun.jersey.core.util.Base64;
import com.uengine.jiraapi.Exception.ValidateCheck;

/**
 * Created by Forrest G. Choi on 2015-05-07.
 */
public class RESTOfIssue {
    private static final String REST_URL = "/rest/api/2/issue";
    ValidateCheck validateCheck = new ValidateCheck();
    private String auth = null;
    private String url = null;
    private String data = null;
    private String data1 = "{\"fields\":{\"assignee\":{\"name\":\"vinodh\"}}}";

    public RESTOfIssue(String auth) {
        this.auth = auth;
    }

    public RESTOfIssue() {
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        if (!auth.contains(":")) throw new NullPointerException("Username과 Password는 ':'로 구분되어야 합니다.");
        this.auth = new String(Base64.encode(auth.toLowerCase()));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!validateCheck.nullCheckURL(url)) {
            throw new NullPointerException(validateCheck.getNullMessage("url"));
        }
        this.url = "https://" + url + REST_URL;
    }

    public void setUrl(String url, String key) {
        if (!validateCheck.nullCheckURL(url)) {
            throw new NullPointerException(validateCheck.getNullMessage("url"));
        }
        if (!validateCheck.nullCheckKey(key)) {
            throw new NullPointerException(validateCheck.getNullMessage("key"));
        }

        this.url = "https://" + url + REST_URL + "/" + key;

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
