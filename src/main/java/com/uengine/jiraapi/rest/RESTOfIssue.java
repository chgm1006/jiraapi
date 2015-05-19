package com.uengine.jiraapi.rest;

import com.sun.jersey.core.util.Base64;
import com.uengine.jiraapi.Exception.ValidateCheck;
import org.apache.commons.lang.StringUtils;

/**
 * JIRA REST의 URL 정보를 담고 있다.
 */
public class RESTOfIssue {
    private static final String REST_ISSUE_URL = "/rest/api/2/issue";
    private static final String REST_PROJECT_URL = "/rest/api/2/project";
    private static final String REST_PICKER_URL = "/rest/api/2/issue/picker";
    private ValidateCheck validateCheck = new ValidateCheck();
    private String auth = null;
    private String url = null;
    private String data = null;

    /**
     * JIRA API의 인증을 위해 username과 password를 Base64로 encode 해준다.
     *
     * @param auth 인증정보. 예) username:password
     */
    public RESTOfIssue(String auth) {
        this.auth = new String(Base64.encode(auth.toLowerCase()));
    }

    public RESTOfIssue() {
    }

    /**
     * 인코딩된 인증정보를 반환한다.
     *
     * @return 인코딩된 인증정보
     */
    public String getAuth() {
        return auth;
    }

    /**
     * auth를 Base64로 인코딩한다.
     *
     * @param auth 인증정보. 예) username:password
     */
    public void setAuth(String auth) {
        if (StringUtils.isEmpty(auth)) {
            if (!auth.contains(":")) throw new RuntimeException("Username과 Password는 ':'로 구분되어야 합니다.");
        }
        this.auth = new String(Base64.encode(auth.toLowerCase()));
    }

    /**
     * REST url 주소를 반환한다.
     *
     * @return REST url 주소
     */
    public String getUrl() {
        return url;
    }

    /**
     * JIRA Issue 관련 URL을 설정한다.
     *
     * @param url 접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     */
    public void setIssueUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(validateCheck.getNullMessage("url"));
        }
        this.url = "https://" + url + REST_ISSUE_URL;
    }

    /**
     * 특정 Issue 관련 URL을 설정한다.
     *
     * @param url 접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     * @param key 접근하고자 하는 Issue의 ID or Key. 예) JIRA-1
     */
    public void setIssueUrl(String url, String key) {
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(validateCheck.getNullMessage("url"));
        }
        if (StringUtils.isEmpty(key)) {
            throw new NullPointerException(validateCheck.getNullMessage("key"));
        }

        this.url = "https://" + url + REST_ISSUE_URL + "/" + key;

    }

    /**
     * JIRA 코멘트 관련 URL 주소를 설정한다.
     *
     * @param url 접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     * @param key 접근하고자 하는 Issue의 ID or Key. 예) JIRA-1
     */
    public void setCommentUrl(String url, String key) {
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(validateCheck.getNullMessage("url"));
        }
        if (StringUtils.isEmpty(key)) {
            throw new NullPointerException(validateCheck.getNullMessage("key"));
        }

        this.url = "https://" + url + REST_ISSUE_URL + "/" + key + "/comment";

    }

    /**
     * JIRA 프로젝트 관련 URL 주소를 설정한다.
     *
     * @param url 접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     */
    public void setProjectUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(validateCheck.getNullMessage(url));
        }

        this.url = "https://" + url + REST_PROJECT_URL;
    }

    /**
     * 특정 JIRA 프로젝트의 Issue ID를 가져오기 위한 URL을 설정한다.
     *
     * @param url              접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     * @param currentProjectId 접근하고자 하는 프로젝트의 Key. 예) JIRA
     */
    public void setIssueIDsUrl(String url, String currentProjectId) {
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(validateCheck.getNullMessage(url));
        }
        this.url = "https://" + url + REST_PICKER_URL + "?currentProjectId=" + currentProjectId;
    }

    /**
     * JIRA에 생성할 Issue 데이터를 반환한다.
     *
     * @return json 형식의 문자열
     */
    public String getData() {
        return data;
    }

    /**
     * JIRA에 생성할 Issue 데이터를 설정한다.
     *
     * @param data json 형식의 문자열
     */
    public void setData(String data) {
        this.data = data;
    }

}
