package com.beautiful.flow.oozie.model;

import java.io.Serializable;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-17 20:35
 **/
public class OozieJobAction implements Serializable {

    private String id;

    private String name;

    private String credential;

    private String type;

    private String conf;

    private JobActionStatus status;

    private Integer retries;

    private String userRetryCount;

    private String userRetryMax;

    private Integer userRetryInterval;

    private Long startTime;

    private Long endTime;

    private String transition;

    private String data;

    private String stats;

    private String externalChildIDs;

    private String externalId;

    private String externalStatus;

    private String trackerUri;

    private String consoleUrl;

    private String errorCode;

    private String errorMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public JobActionStatus getStatus() {
        return status;
    }

    public void setStatus(JobActionStatus status) {
        this.status = status;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public String getUserRetryCount() {
        return userRetryCount;
    }

    public void setUserRetryCount(String userRetryCount) {
        this.userRetryCount = userRetryCount;
    }

    public String getUserRetryMax() {
        return userRetryMax;
    }

    public void setUserRetryMax(String userRetryMax) {
        this.userRetryMax = userRetryMax;
    }

    public Integer getUserRetryInterval() {
        return userRetryInterval;
    }

    public void setUserRetryInterval(Integer userRetryInterval) {
        this.userRetryInterval = userRetryInterval;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getTransition() {
        return transition;
    }

    public void setTransition(String transition) {
        this.transition = transition;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getExternalChildIDs() {
        return externalChildIDs;
    }

    public void setExternalChildIDs(String externalChildIDs) {
        this.externalChildIDs = externalChildIDs;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalStatus() {
        return externalStatus;
    }

    public void setExternalStatus(String externalStatus) {
        this.externalStatus = externalStatus;
    }

    public String getTrackerUri() {
        return trackerUri;
    }

    public void setTrackerUri(String trackerUri) {
        this.trackerUri = trackerUri;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static enum JobActionStatus {
        PREP,
        RUNNING,
        OK,
        ERROR,
        USER_RETRY,
        START_RETRY,
        START_MANUAL,
        DONE,
        END_RETRY,
        END_MANUAL,
        KILLED,
        FAILED,
    }
}
