package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.SqoopFileConvert;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 14:41
 **/
public class SqoopModel extends XMLModel {
    @XmlField(name = "resource-manager")
    private String resourceManager;
    @XmlField(name = "name-node")
    private String nameNode;
    @XmlField(name = "command")
    private String command;
    @XmlField(name = "file", convertUsing = SqoopFileConvert.class)
    private List<String> files;


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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
