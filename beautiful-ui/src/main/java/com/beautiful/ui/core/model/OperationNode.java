package com.beautiful.ui.core.model;

import com.beautiful.ui.core.model.type.Node;
import org.springframework.data.annotation.Id;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 18:08
 **/
public class OperationNode extends DataNode {

    @Id
    private String id;
    private String uid;
    private Node reference;//模式

    public OperationNode() {
        setNodeType(DataNodeType.OPERATION);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Node getReference() {
        return reference;
    }

    public void setReference(Node reference) {
        this.reference = reference;
    }
}
