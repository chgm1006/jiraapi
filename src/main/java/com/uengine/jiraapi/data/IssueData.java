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
        System.out.println(fields);
        JSONObject jsonArray = JSONObject.fromObject(fields);
        return jsonArray.toString();
    }

}
