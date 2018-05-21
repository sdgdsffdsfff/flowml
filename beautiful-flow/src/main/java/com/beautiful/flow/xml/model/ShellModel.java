package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.ShellArgumentConvert;
import com.beautiful.flow.xml.model.convert.ShellCaptureOutputConvert;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 15:01
 **/
public class ShellModel extends XMLModel {

    @XmlField(name = "resource-manager")
    private String resourceManager;
    @XmlField(name = "name-node")
    private String nameNode;
    @XmlField(name = "exec")
    private String exec;
    @XmlField(name = "argument", convertUsing = ShellArgumentConvert.class)
    private List<String> arguments;
    @XmlField(name = "capture-output", convertUsing = ShellCaptureOutputConvert.class)
    private boolean captureOutput;

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

    public String getExec() {
        return exec;
    }

    public void setExec(String exec) {
        this.exec = exec;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public boolean isCaptureOutput() {
        return captureOutput;
    }

    public void setCaptureOutput(boolean captureOutput) {
        this.captureOutput = captureOutput;
    }
}
