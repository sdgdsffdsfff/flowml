package com.beautiful.ui.core.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Map;

public class Connection implements Serializable {
    @Autowired
    private String id;

    private String sourceId;//源节点

    private String targetId;//目标节点

    private Map<String, String> props;//可以扩展属性

    private Long createTime;

    private Long updateTime;//更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
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
}
