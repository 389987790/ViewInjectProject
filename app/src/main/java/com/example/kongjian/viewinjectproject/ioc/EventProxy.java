package com.example.kongjian.viewinjectproject.ioc;

import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by user on 2018/3/20.
 */

public class EventProxy implements InvocationHandler {
    private Activity target;
    private Map<String, Method> methodMap;

    public EventProxy(Activity activity, Map<String, Method> methodMap) {
        this.target = activity;
        this.methodMap = methodMap;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method---->onclick
        // onClick---->mClick
        Method m = methodMap.get(method.getName());
        if (m != null) {
            //target----->activity
            return m.invoke(target, args);
        }
        return method.invoke(proxy, args);
    }
}
