package com.example.designpatternsdemo.behavioraldp.observerpattern

/**
 * Observer pattern is also known as Publisher-Subscriber pattern.
 *
 */

interface Observer {
    //It could be some Custom object as parameter in update method
    fun update(msg: String)
}

class BasicUser : Observer {
    //It could be some Custom object as parameter in update method
    override fun update(msg: String) {
        println("Basic user: $msg received")
    }
}

class PremiumUser : Observer {
    override fun update(msg: String) {
        println("Premium user: $msg received")
    }
}

/**
 * Note: Subjects are the objects that maintain and notify observers about changes in their state,
 * while Observers are the entities that react to those changes.
 */

//Subject/Observable interface
interface Channel {
    //Subject is Channel here
    fun registerObserver(observer: Observer)
    fun unregisterObserver(observer: Observer)
    fun notifyObserver(msg: String)

    fun addNewContent(content: AddContent)
}

//Concrete Subject/Observable
class CodingChannel(private val name: String = "Coding chanel") : Channel {
    private val observerList = mutableListOf<Observer>()

    /**
     * The subject doesn’t need to know the specific classes of its observers, allowing for flexibility.
     * Concrete observers are abstract to Subject
     */
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

    //Whenever a new Content is added to Subject(i.e. CodingChannel), it's state is changed,
    // which is observed by observer
    override fun addNewContent(content: AddContent) {
        //Process the new content
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