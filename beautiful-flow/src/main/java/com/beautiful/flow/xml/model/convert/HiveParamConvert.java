package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 14:54
 **/
public class HiveParamConvert implements Convert<List<String>> {

    public Element convert(Element root, String tag, List<String> inputs) {
        for (String input :
                inputs) {
            root.addElement(tag).setText(input);
        }
        return root;
    }
}
