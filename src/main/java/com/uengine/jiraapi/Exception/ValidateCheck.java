package com.uengine.jiraapi.Exception;

/**
 * Created by Forrest G. Choi on 2015-05-13.
 */
public class ValidateCheck {
    public void nullCheckValue(String auth, String url, String data) {
        if (!nullCheckAuth(auth)) {
            throw new NullPointerException(getNullMessage("auth"));
        }
        if (!nullCheckURL(url)) {
            throw new NullPointerException(getNullMessage("url"));
        }
        if (!nullCheckData(data)) {
            throw new NullPointerException(getNullMessage("data"));
        }
    }


    public void nullCheckValue(String auth, String url) {
        if (!nullCheckAuth(auth)) {
            throw new NullPointerException(getNullMessage("auth"));
        }
        if (!nullCheckURL(url)) {
            throw new NullPointerException(getNullMessage("url"));
        }
    }

    public boolean nullCheckURL(String url) {
        if (url == null || "".equals(url)) {
            return false;
        }
        return true;
    }

    public boolean nullCheckAuth(String auth) {
        if (auth == null || "".equals(auth)) {
            return false;
        }
        return true;
    }

    public boolean nullCheckKey(String key) {
        if (key == null || "".equals(key)) {
            return false;
        }
        return true;
    }

    public boolean nullCheckData(String data) {
        if (data == null || "".equals(data)) {
            return false;
        }
        return true;
    }

    public String getNullMessage(String msg) {
        return msg.toUpperCase() + "값이 NULL입니다.";
    }
}
