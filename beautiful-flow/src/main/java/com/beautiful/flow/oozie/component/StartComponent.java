package com.beautiful.flow.oozie.component;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 11:34
 **/
public class StartComponent extends ControlComponent {

    private String toNode;

    public StartComponent(String toNode) {
        this.toNode = toNode;
    }

    public String getToNode() {
        return toNode;
    }

    public void setToNode(String toNode) {
        this.toNode = toNode;
    }
}
