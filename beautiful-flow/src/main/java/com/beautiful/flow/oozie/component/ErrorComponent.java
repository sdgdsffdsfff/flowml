package com.beautiful.flow.oozie.component;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 18:21
 **/
public class ErrorComponent extends NodeComponent {

    private String toNode;

    public ErrorComponent(String toNode) {
        this.toNode = toNode;
    }

    public String getToNode() {
        return toNode;
    }

    public void setToNode(String toNode) {
        this.toNode = toNode;
    }


}
