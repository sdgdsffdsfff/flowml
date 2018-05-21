package com.beautiful.flow.oozie.builder;

import com.beautiful.flow.oozie.component.WorkAppComponent;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 18:03
 **/
public final class OozieComponentStepBuilder {

    private OozieComponentStepBuilder() {
    }

    public static WorkAppComponent newBuilder() {
        return new WorkAppComponent();
    }

    private static class ComponentSteps {


    }


}
