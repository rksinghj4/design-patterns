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


class Singleton2{
    var data: String = "Default"

    @Volatile
    private  var instance: Singleton2? = null

    fun getSingleton(): Singleton2 {
        return instance ?: synchronized(this) {
            instance ?: Singleton2().also { instance = it }
        }
    }

}

object SingletonObject {
    var data: String = "Default"
}