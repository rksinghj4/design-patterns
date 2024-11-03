package com.example.designpatternsdemo.factorypattern

/**
 * https://medium.com/@thecodebean/factory-design-pattern-implementation-in-java-bd16ebb012e2
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
 * Factory pattern allows:
 * we can add new products to our menu and corresponding factories to create these objects, without
 * altering the existing code.
 * It enhances the code extensibility.
 */

//Factory interface
interface DishFactory {
    fun createDish(): Dish
}

class PizzaFactory : DishFactory {
    //Object creation is abstract inside the corresponding Factory
    override fun createDish() = Pizza()
}

class BurgerFactory : DishFactory {
    /**
     * The Factory Pattern encapsulates object creation logic,
     * ensuring it remains hidden from client code.
     * This promotes cleaner and more maintainable code.
     */
    override fun createDish(): Dish = Burger()
}

private fun main() {
    val pizzaFactory: DishFactory = PizzaFactory()
    val pizza = pizzaFactory.createDish()
    pizza.cook()
    pizza.serve()

    val burgerFactory: DishFactory = BurgerFactory()
    val burger = burgerFactory.createDish()
    burger.cook()
    burger.serve()
}

