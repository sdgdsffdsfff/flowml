package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 14:58
 **/
public class PySparkModel extends XMLModel {

    @XmlField(name = "resource-manager")
    private String resourceManager;
    @XmlField(name = "name-node")
    private String nameNode;
    @XmlField(name = "master")
    private String master;
    @XmlField(name = "name")
    private String name;
    @XmlField(name = "jar")
    private String jar;

    public String getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(String resourceManager) {
        this.resourceManager = resourceManager;
    }

    public String getNameNode() {
        return nameNode;
    }

    public void setNameNode(String nameNode) {
        this.nameNode = nameNode;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJar() {
        return jar;
    }

    public void setJar(String jar) {
        this.jar = jar;
    }
}
