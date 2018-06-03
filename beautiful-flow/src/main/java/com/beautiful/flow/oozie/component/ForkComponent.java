package com.beautiful.flow.oozie.component;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 19:55
 **/
public class ForkComponent extends ControlComponent {

    private String name;

    private List<String> nodeList;

    public ForkComponent(String name, List<String> nodeList) {
        this.name = name;
        this.nodeList = nodeList;
    }

    public List<String> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<String> nodeList) {
        this.nodeList = nodeList;
    }
}
