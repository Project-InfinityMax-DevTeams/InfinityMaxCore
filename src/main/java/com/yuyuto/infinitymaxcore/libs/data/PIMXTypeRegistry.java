package com.yuyuto.infinitymaxcore.libs.data;

import java.util.HashMap;
import java.util.Map;

/**
 * PIMXEntryとかClassがジェネリクス型
 * そのための文字列型名⇔Java Classに変換するテーブル
 */
public class PIMXTypeRegistry {
    private static final Map<String,Class<?>> NAME_TO_CLASS = new HashMap<>();
    private static final Map<Class<?>, String> CLASS_TO_NAME = new HashMap<>();

    static {
        register("int");
        register("double");
        register("string");
        register("boolean");
        register("long");
    }

    private static void register(String name){
        NAME_TO_CLASS.put(name, Integer.class);
        CLASS_TO_NAME.put(Integer.class,name);
    }

    public static Class<?> getClass(String name){
        return NAME_TO_CLASS.get(name);
    }

    public static String getName(Class<?> clazz){
        return CLASS_TO_NAME.get(clazz);
    }
}
