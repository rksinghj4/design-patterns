package com.example.designpatternsdemo.structuraldesgnpattern.decoratorpattern

/**
 * https://medium.com/@thecodebean/decorator-design-pattern-implementation-in-java-af632380e249
 * Decorator design pattern is a structural pattern that allows behavior/responsibility to be added to an
 * individual object dynamically, without affecting the behavior of other objects from the same class.
 *
 * Example of decorator: Pizza, Ice cream
 */
interface IceCream2 {
    val desc: String
     fun getDescription() : String
     fun cost(): Int
}

//Concrete Class/ We have only two concrete ice cream types
class ChocolatePlainIceCream2(override val desc: String) : IceCream2 {
    override fun getDescription(): String = desc
    override fun cost(): Int = 30
}

class ButterScotchPlainIceCream2(override val desc: String = "Butter Scotch") : IceCream2 {
    override fun getDescription(): String = desc
    override fun cost(): Int = 40
}


/**
 * To decorate ice cream we should have ice cream object inside decorator class
 *
 * Decorator/topping flavours is also extending same base class as concrete ice cream classes.
 */

//Note: Decorator has 'is-a' and 'has-a' both relationship with Product abstract class/ interface
class ChocoBarDecorator2(private val decoratedIceCream: IceCream2, private val decoratorName: String) :
     IceCream2 by decoratedIceCream {
         //We can skip any unnecessary implementation of interface methods, because implementation of
         //interface IceCream2 is delegated to decoratedIceCream object.
         //So here we can just over ride the method where we want to add additional feature.
    override fun getDescription(): String {
        return decoratedIceCream.getDescription() + " with $decoratorName"//My Topping name
    }

    override fun cost(): Int = decoratedIceCream.cost() + 20//My topping  additional charges
}

class FreshButterDecorator2(private val decoratedIceCream: IceCream2, private val decoratorName: String) :
    IceCream2 by decoratedIceCream  {
    override fun getDescription(): String {
        return decoratedIceCream.getDescription() + " with $decoratorName" //My Topping name
    }

    override fun cost(): Int = decoratedIceCream.cost() + 30//My topping  additional charges
}

private fun main() {
    //Ice cream 1
    val chocolateIceCream = ChocolatePlainIceCream2("ChocolateBar plain")
    println("${chocolateIceCream.getDescription()} of cost = ${chocolateIceCream.cost()}")

    //Topping 1 on Ice cream 2
    val chocobarTopping = ChocoBarDecorator2(chocolateIceCream, "Choco lava topping")
    println("${chocobarTopping.getDescription()} of cost = ${chocobarTopping.cost()}")

    //Topping 2 on Ice cream 2
    val freshButterTopping = FreshButterDecorator2(chocobarTopping, "Fresh Butter")
    println("${freshButterTopping.getDescription()} of cost = ${freshButterTopping.cost()}")

    //Topping 3 on Ice cream 2
    val freshButterTopping3 = FreshButterDecorator2(freshButterTopping, "Double Fresh Butter")
    println("${freshButterTopping3.getDescription()} of cost = ${freshButterTopping3.cost()}")

    //Ice cream 2
    val butterScotchIceCream = ButterScotchPlainIceCream2("Butter scotch plain")
    println("${butterScotchIceCream.getDescription()} of cost = ${butterScotchIceCream.cost()}")

    //Topping 1 on Ice cream 2
    val chocobarTopping1 = ChocoBarDecorator2(butterScotchIceCream, "Choco lava topping")
    println("${chocobarTopping1.getDescription()} of cost = ${chocobarTopping1.cost()}")

    //Topping 2 on Ice cream 2
    val freshButterTopping2 = FreshButterDecorator2(chocobarTopping1, "Fresh Butter")
    println("${freshButterTopping2.getDescription()} of cost = ${freshButterTopping2.cost()}")
}