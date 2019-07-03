package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 14:29
 **/
public class JavaArgConvert implements Convert<List<String>> {

    public Element convert(Element root, String tag, List<String> input) {
        for (String arg :
                input) {
            root.addElement(tag).setText(arg);
        }
        return root;
    }
}
