package com.guyu.single;

//静态内部类,也是不安全的，可以被反射破坏
public class Holder {
    private Holder(){

    }
    private static Holder getInstance(){
        return InnerClass.HOLDER;
    }
    public static class InnerClass{
        private static final Holder HOLDER=new Holder();
    }
}
