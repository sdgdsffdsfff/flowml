package com.beautiful.flow.oozie.component;

import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 11:32
 **/
public class WorkAppComponent implements Component {

    private StartComponent startComponent;

    private EndComponent endComponent;

    private Map<String, ActionComponent> actionComponents;

    private Map<String, ControlComponent> controlComponents;

    public String toXml() {
        return null;
    }

    public String toJson() {
        return null;
    }
}
