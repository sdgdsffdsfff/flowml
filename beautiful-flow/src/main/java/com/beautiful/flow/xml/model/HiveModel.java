package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.HiveParamConvert;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 14:52
 **/
public class HiveModel extends XMLModel {


    @XmlField(name = "resource-manager")
    private String resourceManager;
    @XmlField(name = "name-node")
    private String nameNode;
    @XmlField(name = "script")
    private String script;
    @XmlField(name = "param", convertUsing = HiveParamConvert.class)
    private List<String> params;


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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
