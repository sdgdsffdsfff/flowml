package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 15:07
 **/
public class ShellCaptureOutputConvert implements Convert<Boolean> {
    public Element convert(Element root, String tag, Boolean input) {
        if (input) {
            root.addElement(tag);
        }
        return root;
    }
}
