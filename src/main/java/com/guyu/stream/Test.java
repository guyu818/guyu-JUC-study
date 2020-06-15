package com.guyu.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：一分钟内完成此题，只能用一行代码实现
 * 现有5个用户！帅选
 * 1.ID必须式偶数的
 * 2.年龄必须大于23岁
 * 3.用户名转为大写字母
 * 4.用户名字母倒着排序
 * 5.只能输出一个用户
 */
public class Test {
    public static void main(String[] args) {
        User u1=new User(1,"a",21);
        User u2=new User(2,"b",22);
        User u3=new User(3,"c",23);
        User u4=new User(4,"d",24);
        User u5=new User(6,"e",25);
        //集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        //计算交给stream流
        //lambda表达式、链式编程、函数式接口、stream流式计算体现的淋漓尽致
        list.stream()
                .filter(user -> {return user.getId()%2==0;})    //id为偶数的
                .filter(user -> {return user.getAge()>23;})     //年龄大于23
                .map(user -> {return user.getName().toUpperCase();})//使用map进行映射转换
                .sorted((user1,user2)->{return user2.compareTo(user1);})//排序进行倒序
                .limit(1)                                           //进行分页，只输出1个
                .forEach(System.out::println);
    }
}
