package com.beautiful.flow.oozie.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-17 19:27
 **/
public class OozieJob implements Serializable {

    private String id;

    private String userId;//当前用户

    private String graphXML;

    private String graphJson;

    private String appName;

    private String appPath;

    private Long createTime;

    private Long updateTime;

    private String jobId;

    private Date startTime;

    private Date endTime;

    private JobState jobState;

    private String conf;

    private Long LastModifiedTime;

    private String user;

    private String acl;

    private String consoleUrl;

    private String parentId;

    private List<OozieJobAction> jobAction;

    private String externalId;

    public String getGraphXML() {
        return graphXML;
    }

    public void setGraphXML(String graphXML) {
        this.graphXML = graphXML;
    }

    public String getGraphJson() {
        return graphJson;
    }

    public void setGraphJson(String graphJson) {
        this.graphJson = graphJson;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public JobState getJobState() {
        return jobState;
    }

    public void setJobState(JobState jobState) {
        this.jobState = jobState;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public Long getLastModifiedTime() {
        return LastModifiedTime;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
        LastModifiedTime = lastModifiedTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<OozieJobAction> getJobAction() {
        return jobAction;
    }

    public void setJobAction(List<OozieJobAction> jobAction) {
        this.jobAction = jobAction;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public static enum JobState {
        PREP, RUNNING, SUCCEEDED, KILLED, FAILED, SUSPENDED;

    }
}
