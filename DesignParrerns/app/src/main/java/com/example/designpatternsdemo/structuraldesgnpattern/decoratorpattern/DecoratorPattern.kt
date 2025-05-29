package com.example.designpatternsdemo.structuraldesgnpattern.decoratorpattern

/**
 * https://medium.com/@thecodebean/decorator-design-pattern-implementation-in-java-af632380e249
 *
 * Example of decorator: Pizza, Ice cream
 */
abstract class IceCream(private val description: String = "") {
    open fun getDescription() = description
    abstract fun cost(): Int
}

//Concrete Class/ We have only two concrete ice cream types
class ChocolatePlainIceCream(name: String) : IceCream(name) {
    override fun cost(): Int = 30
}

class ButterScotchPlainIceCream(name: String = "Butter Scotch") : IceCream(name) {
    override fun cost(): Int = 40
}


/**
 * To decorate ice cream we should have ice cream object inside decorator class
 *
 * Decorator/topping flavours is also extending same base class as concrete ice cream classes.
 */
//Note: Decorator has 'is-a' and 'has-a' both relationship with Product abstract class/ interface
abstract class IceCreamDecorator(val decoratedIceCream: IceCream) : IceCream()


class ChocoBarDecorator(iceCream: IceCream, private val decoratorName: String) :
    IceCreamDecorator(iceCream) {

    override fun getDescription(): String {
        return decoratedIceCream.getDescription() + " with $decoratorName"//My Topping name
    }

    override fun cost(): Int = decoratedIceCream.cost() + 20//My topping  additional charges
}

class FreshButterDecorator(iceCream: IceCream, private val decoratorName: String) :
    IceCreamDecorator(iceCream) {
    override fun getDescription(): String {
        return decoratedIceCream.getDescription() + " with $decoratorName" //My Topping name
    }

    override fun cost(): Int = decoratedIceCream.cost() + 30//My topping  additional charges
}

private fun main() {
    //Ice cream 1
    val chocolateIceCream = ChocolatePlainIceCream("ChocolateBar plain")
    println("${chocolateIceCream.getDescription()} of cost = ${chocolateIceCream.cost()}")

    //Topping 1 on Ice cream 2
    val chocobarTopping = ChocoBarDecorator(chocolateIceCream, "Choco lava topping")
    println("${chocobarTopping.getDescription()} of cost = ${chocobarTopping.cost()}")

    //Topping 2 on Ice cream 2
    val freshButterTopping = FreshButterDecorator(chocobarTopping, "Fresh Butter")
    println("${freshButterTopping.getDescription()} of cost = ${freshButterTopping.cost()}")

    //Topping 3 on Ice cream 2
    val freshButterTopping3 = FreshButterDecorator(freshButterTopping, "Double Fresh Butter")
    println("${freshButterTopping3.getDescription()} of cost = ${freshButterTopping3.cost()}")

    //Ice cream 2
    val butterScotchIceCream = ButterScotchPlainIceCream("Butter scotch plain")
    println("${butterScotchIceCream.getDescription()} of cost = ${butterScotchIceCream.cost()}")

    //Topping 1 on Ice cream 2
    val chocobarTopping1 = ChocoBarDecorator(butterScotchIceCream, "Choco lava topping")
    println("${chocobarTopping1.getDescription()} of cost = ${chocobarTopping1.cost()}")

    //Topping 2 on Ice cream 2
    val freshButterTopping2 = FreshButterDecorator(chocobarTopping1, "Fresh Butter")
    println("${freshButterTopping2.getDescription()} of cost = ${freshButterTopping2.cost()}")
}