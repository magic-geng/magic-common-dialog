package com.xunai.dialogdemo.utils;

import java.lang.reflect.ParameterizedType;

public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            ParameterizedType pType = (ParameterizedType) o.getClass().getGenericSuperclass();
            if (pType != null) {
                Class clazz = (Class) pType.getActualTypeArguments()[i];
                return (T) clazz.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}