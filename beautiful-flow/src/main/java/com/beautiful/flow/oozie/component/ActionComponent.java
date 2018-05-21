package com.beautiful.flow.oozie.component;

import com.beautiful.flow.xml.model.XMLModel;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 12:57
 **/
public abstract class ActionComponent extends NodeComponent {

    private XMLModel actionModel;

    private OkComponent okComponent;

    private ErrorComponent errorComponent;

    public ActionComponent(XMLModel actionModel) {
        this.actionModel = actionModel;
    }

    public XMLModel getActionModel() {
        return actionModel;
    }

    public void setActionModel(XMLModel actionModel) {
        this.actionModel = actionModel;
    }
}
