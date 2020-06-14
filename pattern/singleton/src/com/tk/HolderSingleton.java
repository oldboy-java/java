package com.tk;

/**
 * Created by Administrator on 2020-6-14.
 * 使用Holder方式实现单列：使用延迟类加载方式
 * 此种方式在HolderSingleton类加载的时候，没有实例化对象，实例变量不会占用内存
 */
public final class HolderSingleton {

    //实例变量
    private  byte[] data = new byte[10*1024*1024];

    /**
     * 构造器私有化
     */
    private HolderSingleton(){}


    /**
     * 使用Holder实例化单列对象
     */
    private static class Holder {
        private  static HolderSingleton instance = new HolderSingleton();

    }

    public static HolderSingleton getInstance(){
        return Holder.instance;
    }
}
