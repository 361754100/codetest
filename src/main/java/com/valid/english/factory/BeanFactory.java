package com.valid.english.factory;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory<T> {

    private static final Map<String, Object> BEAN_CONTAINER = new HashMap<String, Object>();

    private static BeanFactory instance;

    private static class HolderClass {
        private static BeanFactory factory = new BeanFactory();
    }

    public static BeanFactory getInstance() {
        if(instance == null) {
            instance = HolderClass.factory;
        }
        return instance;
    }

    public void loadBean(String basePackageName) {
        doScan(basePackageName);
        doDi();
    }

    public void doScan(String basePackageName) {
        if(basePackageName == null || "".equals(basePackageName)) {
            basePackageName = "com.valid.english";
        }
        Reflections reflection = new Reflections(basePackageName);

        // 获得此路径下所有带有自定义Service注解的类
        Set<Class<?>> typesAnnotatedWith = reflection.getTypesAnnotatedWith(Component.class);
        try {
            for(Class<?> claz: typesAnnotatedWith) {
                Object beanObj = claz.newInstance();
                BEAN_CONTAINER.put(claz.getName(), beanObj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void doDi() {
        Set<Map.Entry<String, Object>> beans = BEAN_CONTAINER.entrySet();

        for(Map.Entry<String, Object> entry: beans) {
            Object obj = entry.getValue();
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields) {
                Object injectObj = null;
                if(field.isAnnotationPresent(AutoWired.class)) {
                    String name = field.getType().getName();
                    injectObj = BEAN_CONTAINER.get(name);
                }
                // 通过反射注入到该属性中
                field.setAccessible(true);
                try {
                    field.set(obj, injectObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public T getBean(Class claz) {
        T tmpObj = null;
        try {
            tmpObj = (T) BEAN_CONTAINER.get(claz.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmpObj;
    }

    /*public static<T> T getBean(Class claz) {
        T tmpObj = null;
        try {
            tmpObj = (T) ClassLoader.getSystemClassLoader().loadClass(claz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return tmpObj;
    }*/


}
