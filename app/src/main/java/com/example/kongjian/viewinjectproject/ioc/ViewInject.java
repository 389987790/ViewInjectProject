package com.example.kongjian.viewinjectproject.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by user on 2018/3/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface ViewInject {
    int value();
}
