package com.beautiful.ui.core.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 18:01
 **/
public abstract class DataNode implements Serializable {

    private DataNodeType nodeType;

    private Long createTime;

    private Long updateTime;

    private Map<String, String> props = Maps.newHashMap();

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

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

    public DataNodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(DataNodeType nodeType) {
        this.nodeType = nodeType;
    }
}
