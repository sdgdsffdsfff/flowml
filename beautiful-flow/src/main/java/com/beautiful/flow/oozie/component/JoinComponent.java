package com.beautiful.flow.oozie.component;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 19:55
 **/
public class JoinComponent extends ControlComponent {

    private String name;

    private String toNode;

    public JoinComponent(String name, String toNode) {
        this.name = name;
        this.toNode = toNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToNode() {
        return toNode;
    }

    public void setToNode(String toNode) {
        this.toNode = toNode;
    }
}
