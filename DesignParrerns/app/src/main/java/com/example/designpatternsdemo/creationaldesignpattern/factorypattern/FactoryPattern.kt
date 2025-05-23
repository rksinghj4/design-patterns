package com.example.designpatternsdemo.creationaldesignpattern.factorypattern

/**
 * https://medium.com/@thecodebean/factory-design-pattern-implementation-in-java-bd16ebb012e2
 *
 * Factory Pattern is a creational design pattern that abstracts the process of object creation.
 * It allows you to create objects without specifying their exact types,
 * delegating the responsibility to subclasses or specialized factory classes.
 * This abstraction promotes flexibility and code maintainability.
 *
 * It separates the object creation logic from the code that uses the objects.
 *
 * https://www.youtube.com/watch?v=7g9S371qzwM&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=7
 *
 * Factory Pattern: Intent is object creation.
 * Strategy Pattern: Intent is  change of Algo/Strategy at run time.
 */

//Product interface
interface Dish {
    fun cook()
    fun serve()
}

//Concrete product implements Product interface
class Pizza : Dish {
    override fun cook() {
        println("Pizza : Cooking - > Cooked")
    }

    override fun serve() {
        println("Pizza : Serving -> Served")
    }
}

class Burger : Dish {
    override fun cook() {
        println("Burger: Cooking -> Cooked")
    }

    override fun serve() {
        println("Burger: Serving - > Served")
    }
}

//Here we can keeping adding more item in our menu

/**
 * The Factory Pattern adheres to the Open/Closed Principle by allowing new object types to be added
 *
 * Factory pattern allows:
 * we can add new products to our menu and corresponding factories to create these objects, without
 * altering the existing code.
 * It enhances the code extensibility.
 */

//Factory interface, here we have only one type of Factory interface i.e DishFactory
interface DishFactory {
    fun createDish(): Dish//1 method only.
}

class PizzaFactory : DishFactory {//Concrete Factory 1
    //Object creation is abstract inside the corresponding Factory.
    override fun createDish() = Pizza()//1 method only to crate 1 product/item.
}

class BurgerFactory : DishFactory {//Concrete Factory 2
    /**
     * The Factory Pattern encapsulates object creation logic,
     * ensuring it remains hidden from client code.
     * This promotes cleaner and more maintainable code.
     */
    override fun createDish(): Dish = Burger()//1 method only to crate 1 product/item.
}

private fun main() {
    /**
     * Here buyer can directly contact factory of required product.
     */
    val pizzaFactory: DishFactory = PizzaFactory()
    val pizza = pizzaFactory.createDish()
    pizza.cook()
    pizza.serve()

    val burgerFactory: DishFactory = BurgerFactory()
    val burger = burgerFactory.createDish()
    burger.cook()
    burger.serve()
}

