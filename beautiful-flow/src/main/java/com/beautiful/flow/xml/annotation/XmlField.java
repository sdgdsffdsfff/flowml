package com.beautiful.flow.xml.annotation;

import com.beautiful.flow.xml.model.convert.BasicConvert;
import com.beautiful.flow.xml.model.convert.Convert;

import java.lang.annotation.*;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 17:24
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XmlField {

    String name() default "";

    boolean convert() default true;

    Class<? extends Convert> convertUsing() default BasicConvert.class;


}
