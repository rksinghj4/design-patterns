package com.example.designpatternsdemo.observerpattern

/**
 * Observer pattern is also known as Publisher-Subscriber pattern.
 *
 */

interface Observer {
    fun update(msg: String)
}

class BasicUser : Observer {
    override fun update(msg: String) {
        println("Basic user: $msg received")
    }
}

class PremiumUser : Observer {
    override fun update(msg: String) {
        println("Premium user: $msg received")
    }
}

interface Channel {
    //Subject is Channel here
    fun registerObserver(observer: Observer)
    fun unregisterObserver(observer: Observer)
    fun notifyObserver(msg: String)

    fun addNewContent(content: AddContent)
}

class CodingChannel(private val name: String = "Coding chanel") : Channel {
    private val observerList = mutableListOf<Observer>()

    override fun registerObserver(observer: Observer) {
        observerList.add(observer)
    }

    override fun unregisterObserver(observer: Observer) {
        observerList.remove(observer)
    }

    override fun notifyObserver(msg: String) {
        observerList.forEach {
            it.update(msg)
        }
    }

    override fun addNewContent(content: AddContent) {
        notifyObserver(msg = "${content.contentType} has arrived on $name")
    }
}

class DesignPatternChannel(private val name: String = "Design pattern chanel") : Channel {
    private val observerList = mutableListOf<Observer>()

    override fun registerObserver(observer: Observer) {
        observerList.add(observer)
    }

    override fun unregisterObserver(observer: Observer) {
        observerList.remove(observer)
    }

    override fun notifyObserver(msg: String) {
        observerList.forEach {
            it.update(msg)
        }
    }

    override fun addNewContent(content: AddContent) {
        notifyObserver(msg = "${content.contentType} has arrived on $name")
    }
}

interface AddContent {
    //ISP
    val contentType: String
    fun addContent()
}

interface DeleteContent {
    fun deleteContent()
}

class AddVideo(override val contentType: String = "Video") : AddContent {
    override fun addContent() {
        println("Video Added")
    }
}

class AddDocument(override val contentType: String = "Text") : AddContent {
    override fun addContent() {
        println("Document Added")
    }
}

private fun main() {
    val designPatternChannel = DesignPatternChannel()
    val codingChannel = CodingChannel()

    //No one get notification, because no one has subscribed
    designPatternChannel.addNewContent(AddVideo())
    val basicUser1 = BasicUser()
    val premiumUser1 = PremiumUser()
    //Register/Subscribe
    designPatternChannel.registerObserver(basicUser1)
    codingChannel.registerObserver(premiumUser1)

    designPatternChannel.addNewContent(AddVideo())//BasicUser gets notification

    codingChannel.addNewContent(AddDocument())
    codingChannel.addNewContent(AddVideo())

    designPatternChannel.addNewContent(AddDocument())

    //Unregister/Unsubscribe
    designPatternChannel.unregisterObserver(basicUser1)

    designPatternChannel.addNewContent(AddDocument())//No subscriber, so no one get updates
}