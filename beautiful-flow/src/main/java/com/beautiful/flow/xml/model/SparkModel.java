package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.SparkArgFlatternConvert;
import com.beautiful.flow.xml.model.convert.SparkOptionConvert;

import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 16:57
 **/
public class SparkModel extends XMLModel {

    @XmlField(name = "resource-manager")
    private String resourceManager;
    @XmlField(name = "name-node")
    private String nameNode;
    @XmlField(name = "master")
    private String master;
    @XmlField(name = "mode")
    private String mode;
    @XmlField(name = "name")
    private String name;
    @XmlField(name = "class")
    private String clazz;
    @XmlField(name = "jar")
    private String jar;
    @XmlField(name = "spark-opts", convertUsing = SparkOptionConvert.class)
    private Map<String, String> sparkOpts;
    @XmlField(name = "arg", convertUsing = SparkArgFlatternConvert.class)
    private Map<String, String> args;


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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getJar() {
        return jar;
    }

    public void setJar(String jar) {
        this.jar = jar;
    }

    public Map<String, String> getSparkOpts() {
        return sparkOpts;
    }

    public void setSparkOpts(Map<String, String> sparkOpts) {
        this.sparkOpts = sparkOpts;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
