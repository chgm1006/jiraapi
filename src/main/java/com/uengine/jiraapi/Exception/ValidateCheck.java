package com.uengine.jiraapi.Exception;

import com.sun.jersey.api.client.ClientResponse;
import org.apache.commons.lang.StringUtils;

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
    public void checkNullValue(String auth, String url, String data) throws NullPointerException {
        if (StringUtils.isEmpty(auth)) {
            throw new NullPointerException(getNullMessage("auth"));
        }
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(getNullMessage("url"));
        }
        if (StringUtils.isEmpty(data)) {
            throw new NullPointerException(getNullMessage("data"));
        }
    }


    /**
     * 인증값, URL, data가 NULL인지 체크한다.
     *
     * @param auth 인증값. ex) admin:1234 (username:password)
     * @param url  JIRA 서버 URL
     */
    public void checkNullValue(String auth, String url) throws NullPointerException {
        if (StringUtils.isEmpty(auth)) {
            throw new NullPointerException(getNullMessage("auth"));
        }
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException(getNullMessage("url"));
        }
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
     * @param response 응답받은 response 객체
     * @throws AuthenticationException
     */
    public void getStatusException(ClientResponse response) throws AuthenticationException, RuntimeException {
        int status = response.getStatus();
        if (status == 400) {
            throw new RuntimeException("필수 입력 필드가 NULL입니다.");
        }
        if (status == 401) {
            throw new AuthenticationException("Username과 Password가 잘못되었습니다.");
        }
    }
}
