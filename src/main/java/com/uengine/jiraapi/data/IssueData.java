package com.uengine.jiraapi.data;

import com.uengine.jiraapi.Exception.ValidateCheck;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * Create, Update, Delete에 사용될 필드를 생성한다.
 */
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

        return JSONObject.fromObject(fields).toString();
    }

    /**
     * 생성할 코멘트의 데이터를 JSON 형식으로 반환한다.
     *
     * @param body     필수. 코멘트 내용
     * @param roleName 필수. Project Role Name.
     *                 Default Role Name) Administrators, Developers, Tempo Project Managers, Users
     *                 이외에 다른 role을 지정해주고 싶다면 JIRA 서버로 들어가서 Project Role을 생성해 주어야 한다.
     * @return JSON 형식의 문자열을 반환
     * @throws NullPointerException 모든 데이터가 NULL일 경우 NullPointerException을 반환
     */
    public String getCommentData(String body, String roleName) throws NullPointerException {
        if (StringUtils.isEmpty(body.trim()) &&
                StringUtils.isEmpty(roleName)) {
            throw new NullPointerException("데이터가 모두 NULL일 수는 없습니다.");
        }

        String fields = "{" +
                "body: \"" + body + "\"," +
                "visibility: {" +
                "type: \"role\"" +
                ",value: \"" + roleName + "\"}" +
                "}";
        if (StringUtils.isEmpty(body.trim())) {
            throw new NullPointerException(validateCheck.getNullMessage("body"));
        }
        if (StringUtils.isEmpty(roleName.trim())) {
            throw new NullPointerException(validateCheck.getNullMessage("roleName"));
        }
        return JSONObject.fromObject(fields).toString();
    }

    /**
     * 업데이트 할 이슈내용을 JSON 형식으로 반환한다.
     *
     * @param title             옵션. 이슈의 타이틀
     * @param originalEstimate  옵션. 처리 예상 시간
     * @param remainingEstimate 옵션. 처리까지 남은 시간
     * @param addLabel          옵션. 추가할 Label
     * @param removeLavel       옵션. 삭제할 Label
     * @return JSON 형식의 문자열을 반환
     * @throws NullPointerException 모든 데이터가 NULL일 경우 NullPointerException을 반환
     */
    public String getUpdateIssueData(String title, String originalEstimate, String remainingEstimate, String addLabel, String removeLavel) throws NullPointerException {
        if (StringUtils.isEmpty(title.trim()) &&
                StringUtils.isEmpty(originalEstimate.trim()) &&
                StringUtils.isEmpty(remainingEstimate.trim()) &&
                StringUtils.isEmpty(addLabel.trim()) &&
                StringUtils.isEmpty(removeLavel.trim())) {
            throw new NullPointerException("데이터가 모두 NULL일 수는 없습니다.");
        }

        String fields = "{\"update\": {";

        if (StringUtils.isNotEmpty(title.trim())) {
            fields += "\"summary\": [{\"set\": \"" + title + "\"}]";
        }
        if (StringUtils.isNotEmpty(originalEstimate.trim()) || StringUtils.isNotEmpty(remainingEstimate.trim())) {
            if (StringUtils.isNotEmpty(originalEstimate) && StringUtils.isNotEmpty(remainingEstimate)) {
                fields += ",\"timetracking\": [{\"edit\": {\"originalEstimate\": \"" + originalEstimate + "\",\"remainingEstimate\": \"" + remainingEstimate + "\"}}]";
            } else if (StringUtils.isNotEmpty(originalEstimate)) {
                fields += ",\"timetracking\": [{\"edit\": {\"originalEstimate\": \"" + originalEstimate + "\"}}]";
            } else if (StringUtils.isNotEmpty(remainingEstimate)) {
                fields += ",\"timetracking\": [{\"edit\": {\"remainingEstimate\": \"" + remainingEstimate + "\"}}]";
            }
        }

        if (StringUtils.isNotEmpty(addLabel.trim()) || StringUtils.isNotEmpty(removeLavel.trim())) {
            if (StringUtils.isNotEmpty(addLabel.trim()) && StringUtils.isNotEmpty(removeLavel.trim())) {
                fields += ",\"labels\": [{\"add\": \"" + addLabel + "\"},{\"remove\": \"" + removeLavel + "\"}]";
            } else if (StringUtils.isNotEmpty(addLabel.trim())) {
                fields += ",\"labels\": [{\"add\": \"" + addLabel + "\"}]";
            } else if (StringUtils.isNotEmpty(removeLavel.trim())) {
                fields += ",\"labels\": [{\"remove\": \"" + removeLavel + "\"}]";
            }
        }
        fields += "}}";

        return JSONObject.fromObject(fields).toString();
    }
}
