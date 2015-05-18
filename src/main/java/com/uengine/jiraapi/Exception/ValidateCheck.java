package com.uengine.jiraapi.Exception;

import javax.naming.AuthenticationException;

/**
 * 예외 처리 클래스
 */
public class ValidateCheck {
    /**
     * 인증값, URL, data가 NULL인지 체크한다.
     *
     * @param auth 인증값. ex) admin:1234 (username:password)
     * @param url  JIRA 서버 URL
     * @param data JSON 형태의 데이터
     */
    public void checkNullValue(String auth, String url, String data) {
        if (!checkNullAuth(auth)) {
            throw new NullPointerException(getNullMessage("auth"));
        }
        if (!checkNullURL(url)) {
            throw new NullPointerException(getNullMessage("url"));
        }
        if (!checkNullData(data)) {
            throw new NullPointerException(getNullMessage("data"));
        }
    }


    /**
     * 인증값, URL, data가 NULL인지 체크한다.
     *
     * @param auth 인증값. ex) admin:1234 (username:password)
     * @param url  JIRA 서버 URL
     */
    public void checkNullValue(String auth, String url) {
        if (!checkNullAuth(auth)) {
            throw new NullPointerException(getNullMessage("auth"));
        }
        if (!checkNullURL(url)) {
            throw new NullPointerException(getNullMessage("url"));
        }
    }

    /**
     * URL이 NULL인지 체크한다.
     *
     * @param url JIRA 서버 URL
     * @return url값이 null이면 false를 반환
     */
    public boolean checkNullURL(String url) {
        if (url == null || "".equals(url)) {
            return false;
        }
        return true;
    }

    /**
     * auth 값이 NULL인지 체크한다.
     *
     * @param auth 인증값. ex) admin:1234 (username:password)
     * @return auth값이 null이면 false를 반환
     */
    public boolean checkNullAuth(String auth) {
        if (auth == null || "".equals(auth)) {
            return false;
        }
        return true;
    }

    /**
     * key 값이 null인지 체크한다.
     *
     * @param key IssueID나 key. ex) JIRA-1
     * @return key값이 null이면 false를 반환
     */
    public boolean checkNullKey(String key) {
        if (key == null || "".equals(key)) {
            return false;
        }
        return true;
    }

    /**
     * data 값이 null인지 체크한다.
     *
     * @param data JSON 형태의 data 필드
     * @return data 값이 null이면 false반환
     */
    public boolean checkNullData(String data) {
        if (data == null || "".equals(data)) {
            return false;
        }
        return true;
    }

    /**
     * 파라미터가 null인 경우 메세지를 리턴한다.
     * checkVar가 null인지 아닌지는 검증하지 않는다.
     *
     * @param checkVar
     * @return checkVar가 null이라는 메세지를 출력.
     */
    public String getNullMessage(String checkVar) {
        return checkVar.toUpperCase() + "값이 NULL입니다.";
    }

    /**
     * status 코드의 예외 메세지를 반환한다.
     *
     * @param status status 코드
     * @throws AuthenticationException
     */
    public void getStatusException(int status) throws AuthenticationException {
        if (status == 401) {
            throw new AuthenticationException("Username과 Password가 잘못되었습니다.");
        }
    }
}
