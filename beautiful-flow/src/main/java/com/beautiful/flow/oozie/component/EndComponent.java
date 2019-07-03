package com.beautiful.flow.oozie.component;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 11:38
 **/
public class EndComponent extends ControlComponent {

    private String name = "end";

    public EndComponent() {
    }

    public EndComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
