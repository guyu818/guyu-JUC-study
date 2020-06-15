package com.guyu.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//道高一尺魔高一丈，最后的结果是这种方式单例还是可以破坏的
//但是枚举方式的单例是不会反射破坏的。
public class LazyMan {
    private static boolean guyu=false;//防止通过反射创建两个不同实例

    private LazyMan(){
        synchronized (LazyMan.class){
            if(guyu==false){
                guyu=true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏单例");
            }
        }

    }
    //防止指令重排
    private  volatile  static LazyMan lazyMan;
    //双重检测 懒汉式单例 DCL懒汉式
    private static LazyMan getInstance(){
        if (lazyMan==null){
            synchronized (LazyMan.class){
                if(lazyMan==null){
                    lazyMan=new LazyMan();//不是原子性操作
                    /**
                     * 1.分配空间
                     * 2.执行构造方法，初始化
                     * 3.把这个对象指向内存空间
                     *
                     * 理论上是123
                     * 指令重排可能导致132 出问题
                     */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //证明线程不安全
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                System.out.println(getInstance());
//            }).start();
//        }
        //反射破解
//        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> constructor = LazyMan.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);

        Field guyu = LazyMan.class.getDeclaredField("guyu");
        guyu.setAccessible(true);

        LazyMan instance1 = constructor.newInstance();

        guyu.set(instance1,false);

        LazyMan instance2 = constructor.newInstance();

        System.out.println(instance2);
        System.out.println(instance1);


    }

}
