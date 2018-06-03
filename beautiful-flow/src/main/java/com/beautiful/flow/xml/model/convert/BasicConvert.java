package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

public class BasicConvert<I> implements Convert<I> {


    public Element convert(Element root, String tag, I input) {
        Element element = root.addElement(tag);
        element.setText(input.toString());
        return root;
    }


}
