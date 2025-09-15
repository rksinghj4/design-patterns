package com.example.designpatternsdemo.creationaldesignpattern.singletondp;

public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {
        // private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized ((Singleton.class)) {
                // check again as multiple thread can reach above step
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return  instance;
    }
}
