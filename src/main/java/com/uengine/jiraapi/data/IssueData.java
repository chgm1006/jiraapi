package com.uengine.jiraapi.data;

import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class IssueData {
    private ValidateCheck validateCheck = new ValidateCheck();

    /**
     * 생성할 이슈의 데이터를 JSON 형식으로 반환한다.
     *
     * @param projectKey  필수값. JIRA Project의 프로젝트 KEY. 예) JIRA
     * @param summary     필수값. 이슈 제목
     * @param priority    옵션값. 우선 순위. 형식) highest, medium, low, lowest
     * @param description 옵션값. 이슈 설명.
     * @return JSON 형식의 문자열을 반환
     */
    public String getCreateIssueData(String projectKey, String summary, String priority, String description) {
        String fields = "{fields: {";
        if (StringUtils.isEmpty(projectKey)) {
            throw new NullPointerException(validateCheck.getNullMessage("projectKey"));
        } else {
            fields += "project: {key: \"" + projectKey + "\"}";
        }
        if (StringUtils.isEmpty(summary)) {
            throw new NullPointerException(validateCheck.getNullMessage("summary"));
        } else {
            fields += ",summary: \"" + summary + "\""
                    + ",issuetype: {name: \"Task\"}";
        }
        if (StringUtils.isNotEmpty(priority)) {
            if (priority.toLowerCase().equals("highest")) fields += ",priority: {name: \"Highest\"}";
            else if (priority.toLowerCase().equals("low")) fields += ",priority: {name: \"Low\"}";
            else if (priority.toLowerCase().equals("lowest")) fields += ",priority: {name: \"Lowest\"}";
            else fields += ",priority: {name: \"Medium\"}";
        }
        if (StringUtils.isNotEmpty(description)) {
            fields += ",description: \"" + description + "\"}";
        }

        fields += "}}";
        JSONObject jsonObject = JSONObject.fromObject(fields);
        return jsonObject.toString();
    }

    /**
     * 생성할 코멘트의 데이터를 JSON 형식으로 반환한다.
     *
     * @param body     필수. 코멘트 내용
     * @param roleName 필수. Project Role Name.
     *                 Default Role Name) Administrators, Developers, Tempo Project Managers, Users
     *                 이외에 다른 role을 지정해주고 싶다면 JIRA 서버로 들어가서 Project Role을 생성해 주어야 한다.
     * @return JSON 형식의 문자열을 반환
     */
    public String getCreateCommentData(String body, String roleName) {
        String fields = "{" +
                "body: \"" + body + "\"," +
                "visibility: {" +
                "type: \"role\"" +
                ",value: \"" + roleName + "\"}" +
                "}";
        if (StringUtils.isEmpty(body)) {
            throw new NullPointerException(validateCheck.getNullMessage("body"));
        }
        if (StringUtils.isEmpty(roleName)) {
            throw new NullPointerException(validateCheck.getNullMessage("roleName"));
        }
        return JSONObject.fromObject(fields).toString();
    }

    public String getUpdateIssueData() {
        String fields = "{" +
                "\"update\": {" +
                "\"summary\": [" +
                "{" +
                "\"set\": \"Bug in business logic\"" +
                "}" +
                "]," +
                "\"timetracking\": [" +
                "{" +
                "\"edit\": {" +
                "\"originalEstimate\": \"1w 1d\"," +
                "\"remainingEstimate\": \"4d\"" +
                "}" +
                "}" +
                "]," +
                "\"labels\": [" +
                "{" +
                "\"add\": \"test\"" +
                "}," +
                "{" +
                "\"remove\": \"test\"" +
                "}" +
                "]" +
                "}}";

        return fields;
    }
}
