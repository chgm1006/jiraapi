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
     * @throws RuntimeException
     */
    public void setAuth(String auth) throws RuntimeException {
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
     * @throws NullPointerException
     */
    public void setIssueUrl(String url) throws NullPointerException {
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
     * @throws NullPointerException
     */
    public void setIssueUrl(String url, String key) throws NullPointerException {
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
     * @param url       접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     * @param key       접근하고자 하는 Issue의 ID or Key. 예) JIRA-1
     * @param commentID Comment ID. Comment를 업데이트 할 경우. Comment를 새로 생성할 경우 NULL입력.
     * @throws NullPointerException
     */
    public void setCommentUrl(String url, String key, String commentID) throws NullPointerException {
        String commentURL = null;
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(validateCheck.getNullMessage("url"));
        }
        if (StringUtils.isEmpty(key)) {
            throw new NullPointerException(validateCheck.getNullMessage("key"));
        }
        if (StringUtils.isEmpty(commentID.trim())) {
            commentURL = "https://" + url + REST_ISSUE_URL + "/" + key + "/comment";
        } else {
            commentURL = "https://" + url + REST_ISSUE_URL + "/" + key + "/comment" + "/" + commentID;
        }
        this.url = commentURL;
    }

    /**
     * JIRA 프로젝트 관련 URL 주소를 설정한다.
     *
     * @param url 접근하고자 하는 JIRA 서버 주소. 예) domain.atlassian.net
     * @throws NullPointerException
     */
    public void setProjectUrl(String url) throws NullPointerException {
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
     * @throws NullPointerException
     */
    public void setIssueIDsUrl(String url, String currentProjectId) throws NullPointerException {
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
