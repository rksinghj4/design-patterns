package com.example.designpatternsdemo.structuraldesgnpattern.proxypattern

/**
 * Proxy pattern: A structural design pattern
 * A client and real objects are connected through the proxy object.
 * Proxy object manages access to the real objects.
 * Before  sending request to real object, proxy object take care of additional tasks like logging, caching, security and lazy loading.
 */

//Subject interface
abstract class Network {
    open val blockedList = mutableListOf("abc.com", ".chn", "xyz.in", "xyz.com", "xyz.")
    open fun isUrlBlocked(url: String): Boolean {
        return blockedList.contains(url) || blockedList.contains(
            url.split(".")[0]
        ) || blockedList.contains(url.split(".")[1])
    }

    abstract fun fetch(url: String): String
}

//Real object implemented Subject interface
class WebService : Network() {
    private val server = HashMap<String, String>()

    init {
        server["aa.com"] = "aa"
        server["bb.com"] = "bb"
        server["cc.in"] = "cc"
    }

    override fun fetch(url: String): String = server[url] ?: ""
}

//Proxy object implemented Subject interface
class ProxyWebService : Network() {
    //private var webService: WebService = WebService()//It is not lazy loading
    private lateinit var webService: WebService

    private val cache = HashMap<String, String>()
    override fun fetch(url: String): String {
        if (!::webService.isInitialized) {
            webService = WebService()//Lazy loading
        }
        return if (url.isNotBlank() && isUrlBlocked(url = url)) {
            "$url is blocked"
        } else if (cache.contains(url)) {//Check if available in cache
            println("$url : ${cache[url]} data returning from cache")//Event logging
            cache[url].orEmpty()
        } else {//Use the real object to call the required method
            cache[url] = webService.fetch(url)
            cache[url].orEmpty()
        }
    }
}

//Client
private fun main() {
    val proxyWebService = ProxyWebService()
    //Lazy loading : On first fetch actual WebService object is created
    proxyWebService.fetch("")
    println(proxyWebService.fetch("aa.com"))
    println(proxyWebService.fetch("aa.com"))
    println(proxyWebService.fetch("abc.com"))
}