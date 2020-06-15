package com.guyu.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//java.util.ConcurrentModificationException线程不安全也是会报这个错
//解决办法
//1.ap<String,String> map= Collections.synchronizedMap(new HashMap<>());
//2.JUC 下的Map<String,String> map=new ConcurrentHashMap<>();
public class MapTest {
    public static void main(String[] args) {
        //map是这样用的嘛？不是，实际工作中不这么用
        //默认等价什么Map<String,String> map=new HashMap<>(16,0.75);
        //初始化容量 加载因子
//        Map<String,String> map=new HashMap<>();
//        Map<String,String> map= Collections.synchronizedMap(new HashMap<>());
        //**************去官网的中文api文档上面看*******
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
