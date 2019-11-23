package com.liuli.properties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019-7-7.
 */
public class Test {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        test();
    }
    public static  void test() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
       T1 t1 = T1.class.newInstance();
       Method m = T1.class.getDeclaredMethod("sayHello", String.class);
       m.invoke(t1,"zzfsfsd");
    }



}

class T1 {
    protected void sayHello(String msg){
        System.err.println("msg:"+msg);
    }
    public T1(){

    }
}