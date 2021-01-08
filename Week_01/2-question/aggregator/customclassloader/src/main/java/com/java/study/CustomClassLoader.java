package com.java.study;

import java.io.InputStream;
import java.lang.reflect.Method;

public class CustomClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception{
        Class clazz = new CustomClassLoader().loadClass("Hello");
        Object object = clazz.newInstance();
        Method hello = clazz.getDeclaredMethod("hello");
        hello.invoke(object);
    }

    @Override
    protected Class<?> findClass(String name) {
        String classFile = "Hello.xlass";
        InputStream is = CustomClassLoader.class.getClassLoader().getResourceAsStream(classFile);
        byte[] binaryBytes = null;
        try {
            binaryBytes = new byte[is.available()];
            is.read(binaryBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("binaryBytes=" + binaryBytes);
        for (int i = 0; i < binaryBytes.length; i ++){
            binaryBytes[i] = (byte) (~binaryBytes[i]);
        }
        Class clazz = defineClass(name, binaryBytes, 0, binaryBytes.length);
        return clazz;
    }
}
