package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 18:07
 **/
public interface Convert<I> {

    public Element convert(Element root, String tag, I input);


}
