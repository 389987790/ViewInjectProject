package com.example.kongjian.viewinjectproject.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by user on 2018/3/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)//表示该注解在另外一个注解上使用
/**
 * 给控件注入事件，需要三个要素
 *1、设置事件监听方法
 *2、设置监听类型
 *3、事件触发之后，执行的回调方法
 * */
public @interface EventBase {
    /**
     * 设置事件监听的方法
     * @return
     * */
    String listenerSetter();
    /**
     * 设置监听的类型
     * @return
     * */
    Class<?> listenerType();
    /**
     * 回调方法
     * @return
     * */
    String callbackMethod();
}
