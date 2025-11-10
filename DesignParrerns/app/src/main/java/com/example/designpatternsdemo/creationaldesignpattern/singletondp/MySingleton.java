package com.example.designpatternsdemo.creationaldesignpattern.singletondp;

public class MySingleton {
    private static volatile MySingleton instance = null;
    private MySingleton() {
        // private constructor to prevent instantiation
    }

    public static MySingleton getInstance() {
        if(instance == null) {
            synchronized (MySingleton.class) {
                // check again as multiple thread can reach above step
                if(instance == null) {
                    instance = new MySingleton();
                }
            }
        }
        return  instance;
    }
}
