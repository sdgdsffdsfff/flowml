package com.beautiful.ui.core.pagemodel;

import com.beautiful.ui.core.model.Node;
import com.beautiful.ui.core.model.NodeGroup;

import java.io.Serializable;

public class NodeVO implements Serializable {

    private Node node;

    private NodeGroup nodeGroup;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public NodeGroup getNodeGroup() {
        return nodeGroup;
    }

    public void setNodeGroup(NodeGroup nodeGroup) {
        this.nodeGroup = nodeGroup;
    }
}
