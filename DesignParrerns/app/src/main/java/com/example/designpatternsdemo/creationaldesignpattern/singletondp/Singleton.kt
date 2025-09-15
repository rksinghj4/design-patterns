package com.example.designpatternsdemo.creationaldesignpattern.singletondp

class Singleton {
    var data: String = "Default"


    companion object {
        //Lazy initialization with thread-safety
        val instance: Singleton by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton()
        }
    }
}

object SingletonObject {
    var data: String = "Default"
}