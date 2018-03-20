package com.example.kongjian.viewinjectproject.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/3/20.
 */

public class InjectUtils {
    public static void inject(Activity activity) {
        injectLayout(activity);
        injectView(activity);
        injectEvent(activity);
    }
    /**
     *注入事件
     *
     */
    private static void injectEvent(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取方法上的注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<?> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }
                String listenerSetter = eventBase.listenerSetter();
                Class<?> listenerType = eventBase.listenerType();
                String callbackMethod = eventBase.callbackMethod();
                Map<String, Method> methodMap = new HashMap<>();
                methodMap.put(callbackMethod, method);

                try {
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    int[] viewIds = (int[]) valueMethod.invoke(annotation);
                    for (int viewId : viewIds) {
                        View view = activity.findViewById(viewId);
                        if (view == null) {
                            continue;
                        }
                        //获取setListener方法
                        Method setListenerMethod = view.getClass().getMethod(listenerSetter, listenerType);
                        EventProxy eventProxy = new EventProxy(activity, methodMap);
                        //代理对象（动态代理）
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[]{listenerType}, eventProxy);
                        setListenerMethod.invoke(view, proxy);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *注入视图
     *
     */
    private static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject != null) {
                int viewId = viewInject.value();
                View view = activity.findViewById(viewId);
                try {
                    //设置为可访问的
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     *注入布局
     *
     */
    private static void injectLayout(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView == null) {
            return;
        }
        int layoutId = contentView.value();
        activity.setContentView(layoutId);
    }
}
