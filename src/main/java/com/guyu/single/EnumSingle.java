package com.guyu.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//enum是什么？本身也是一个class类
public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
}
class Test{
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //正常方式创建
        EnumSingle instance = EnumSingle.INSTANCE;
        //反射方式创建
        Constructor<EnumSingle> constructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        //会报错Cannot reflectively create enum objects，枚举不支持反射
        EnumSingle instance1 = constructor.newInstance();

        System.out.println(instance);
        System.out.println(instance1);

    }
}


