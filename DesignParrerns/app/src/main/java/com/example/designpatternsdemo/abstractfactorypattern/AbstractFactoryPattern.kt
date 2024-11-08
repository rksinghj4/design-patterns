package com.example.designpatternsdemo.abstractfactorypattern

/**
 * https://medium.com/@akshatsharma0610/abstract-factory-design-pattern-in-java-45a326c8fc9f
 *
 * The Abstract Factory design pattern is a creational design pattern
 * that provides an interface for creating families of related or dependent objects
 * without specifying their concrete classes.
 *
 * https://www.youtube.com/watch?v=7g9S371qzwM&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=7
 */

data class Phone(
    val brand: String,
    val model: String,
    val phoneSize: String,
    val bodyType: String
)

//Product interface
abstract class MobilePhone {
    abstract val phone: Phone
    abstract fun buildPhone(): Phone

    fun getDescription() =
        "${phone.brand}, ${phone.model}, ${phone.phoneSize}, ${phone.bodyType}"
}

//Concrete products
class IPhone13 : MobilePhone() {
    override lateinit var phone: Phone
    override fun buildPhone(): Phone {
        println("Building OnePlus 13")
        phone = Phone(
            brand = "IPhone",
            model = "Iphone 13",
            phoneSize = "5.5",
            bodyType = "Metallic gary"
        )
        println("OnePlus 13 is ready")
        return phone
    }
}

class IPhone14 : MobilePhone() {
    override lateinit var phone: Phone

    override fun buildPhone(): Phone {
        println("Building OnePlus 14")
        phone = Phone(
            brand = "Iphone",
            model = "Iphone 14",
            phoneSize = "6.0",
            bodyType = "Metallic White"
        )
        println("OnePlus 14 is ready")
        return phone
    }
}

class OnePlus11 : MobilePhone() {
    override lateinit var phone: Phone

    override fun buildPhone(): Phone {
        println("Building OnePlus 11")
        phone = Phone(
            brand = "OnePlus",
            model = "OnePlus 11",
            phoneSize = "5.4",
            bodyType = "Metallic black"
        )
        println("OnePlus 11 is ready")
        return phone
    }
}

class OnePlus12 : MobilePhone() {
    override lateinit var phone: Phone

    override fun buildPhone(): Phone {
        println("Building OnePlus 12")
        phone = Phone(
            brand = "OnePlus",
            model = "OnePlus 12",
            phoneSize = "6.0",
            bodyType = "Metallic White"
        )
        println("OnePlus 12 is ready")
        return phone
    }
}

class NoSuchPhone : MobilePhone() {
    override lateinit var phone: Phone
    override fun buildPhone(): Phone {
        phone = Phone(
            brand = "No Such Phone",
            model = "No such model",
            phoneSize = "",
            bodyType = ""
        )
        return phone
    }
}
//Factory interface,
// In case of abstract factory pattern we have multiple kind of factory interfaces

interface MobileFactory
interface AppleFactory : MobileFactory {
    fun createPhone(): MobilePhone
}

class Iphone13Factory : AppleFactory {
    override fun createPhone(): MobilePhone {
        val iPhone13 = IPhone13()//In factory, object creation is hidden from the client.
        iPhone13.buildPhone()
        return iPhone13
    }
}

class Iphone14Factory : AppleFactory {
    override fun createPhone(): MobilePhone {
        val iPhone14 = IPhone14()//In factory, object creation is hidden from the client.
        iPhone14.buildPhone()
        return iPhone14
    }
}

interface OnePlusFactory : MobileFactory {
    fun createPhone(): MobilePhone
}

class OnePlus11Factory : OnePlusFactory {
    override fun createPhone(): MobilePhone {
        val onePlus11 = OnePlus11()//In factory, object creation is hidden from the client.
        onePlus11.buildPhone()
        return onePlus11
    }
}

class OnePlus12Factory : OnePlusFactory {
    override fun createPhone(): MobilePhone {
        val onePlus12 = OnePlus12()//In factory, object creation is hidden from the client.
        onePlus12.buildPhone()
        return onePlus12
    }
}

enum class Model {
    IPHONE_13, IPHONE_14, ONE_PLUS_11, ONE_PLUS_12
}

enum class Brand {
    APPLE, ONE_PLUS
}


//Abstract Factory interface
interface AbstractMobileFactory {
    fun orderPhone(brand: Brand, model: Model): MobilePhone
}

//Concrete class of Abstract Factory
class PhoneStore : AbstractMobileFactory {
    override fun orderPhone(brand: Brand, model: Model): MobilePhone {
        return when (brand) {
            Brand.APPLE -> {
                when (model) {
                    Model.IPHONE_13 -> {
                        //Here object creation of factory is also abstract.
                        Iphone13Factory().createPhone()
                    }

                    Model.IPHONE_14 -> {
                        Iphone14Factory().createPhone()
                    }

                    else -> {
                        val noSuchPhone = NoSuchPhone()
                        noSuchPhone.buildPhone()
                        noSuchPhone
                    }
                }
            }

            Brand.ONE_PLUS -> {
                when (model) {
                    Model.ONE_PLUS_11 -> {
                        OnePlus11Factory().createPhone()
                    }

                    Model.ONE_PLUS_12 -> {
                        OnePlus12Factory().createPhone()
                    }

                    else -> {
                        val noSuchPhone = NoSuchPhone()
                        noSuchPhone.buildPhone()
                        noSuchPhone
                    }
                }
            }
        }
    }
}

//Client/buyer of mobile is unaware of Factory object,
// because abstract factory has encapsulated object creation logic of concrete factories

private fun main() {
    /**
     * Here buyer can't directly contact to factory of required product.
     * Here buyer has to contact to phone store(Implementing Abstract factory),
     * Abstract factory will delegate responsibility to required factory.
     */
    val phoneStore = PhoneStore()
    //Buyer of mobile is just need to tell Brand and model name
    val mobile1 = phoneStore.orderPhone(Brand.APPLE, Model.IPHONE_14)
    //Buyer has rights to know what exactly he is buying, complete description
    println("Hand over to client : ${mobile1.getDescription()}")

    val mobile2 = phoneStore.orderPhone(Brand.ONE_PLUS, Model.ONE_PLUS_12)
    println("Hand over to client: ${mobile2.getDescription()}")
}