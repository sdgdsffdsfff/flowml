package com.beautiful.flow.xml.model.convert;

import com.beautiful.common.model.TuplePair;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 11:51
 **/
public class PrepareOperationConvert implements Convert<List<TuplePair<String, Map<String, String>>>> {
    public Element convert(Element root, String tag, List<TuplePair<String, Map<String, String>>> inputs) {
        Element element = root.addElement(tag);
        for (TuplePair<String, Map<String, String>> input :
                inputs) {
            String operation = input.getLeft();
            Map<String, String> propertys = input.getRight();
            Element operationEle = element.addElement(operation);
            for (Map.Entry<String, String> stringEntry :
                    propertys.entrySet()) {
                operationEle.addAttribute(stringEntry.getKey(), stringEntry.getValue());
            }

        }
        return root;
    }
}
