package com.beautiful.ui.core.model;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

public class DataFlow implements Serializable {

    @Id
    private String id;

    private String name;

    private Long createTime;

    private Long updateTime;//更新时间
    @Transient
    private TinkerGraph tinkerGraph;

    private String graphJson;

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

    public TinkerGraph getTinkerGraph() {
        return tinkerGraph;
    }

    public void setTinkerGraph(TinkerGraph tinkerGraph) {
        this.tinkerGraph = tinkerGraph;
    }

    public String getGraphJson() {
        return graphJson;
    }

    public void setGraphJson(String graphJson) {
        this.graphJson = graphJson;
    }
}
