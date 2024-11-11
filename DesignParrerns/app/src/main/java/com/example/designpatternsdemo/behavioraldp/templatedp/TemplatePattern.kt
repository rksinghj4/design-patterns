package com.example.designpatternsdemo.behavioraldp.templatedp

//Template - Template means Preset format
abstract class PizzaOutletTemplate {
    //Each step/fun can be overridden by child
    protected abstract fun selectItemFromMenu()

    protected abstract fun callWaiter()

    protected abstract fun prepareItemInKitchen()

    protected abstract fun serveCookedItemToCustomer()

    private fun collectBills() {//Common to all child
        println("Collect bills in cash, UPI or card payment")
    }

    /**
     * Template method which consists of series of method calls (either abstract or concrete)
     * that make up the algorithm’s steps..
     */
    fun requestMenuThenOrderPizza() {//Algorithm steps are fixed by parent.
        selectItemFromMenu()
        callWaiter()
        prepareItemInKitchen()
        serveCookedItemToCustomer()
        collectBills()
    }
}

class PizzaHat : PizzaOutletTemplate() {
    override fun selectItemFromMenu() {
        println("PizzaHat menu: Pizza selected from the menu")
    }

    override fun callWaiter() {
        println("Called waiter and received plastic token of orders")
    }

    override fun prepareItemInKitchen() {
        println("In Kitchen Using PizzaHat's recipe to cook the pizza")
    }

    override fun serveCookedItemToCustomer() {
        println("Serving in Black and White Premium Wood Hexagon Multipurpose Serving Tray")
    }
}

class Domino : PizzaOutletTemplate() {
    override fun selectItemFromMenu() {
        println("Domino's menu: Pizza selected from the menu")
    }

    override fun callWaiter() {
        println("Called waiter and received paper bills token of orders")
    }

    override fun prepareItemInKitchen() {
        println("In kitchen Using Domino's recipe to cook the pizza")
    }

    override fun serveCookedItemToCustomer() {
        println("Serving in Elegant Dark Brown Rectangle Small Serving Tray")
    }
}

private fun main() {
    var pizzaOutletTemplate: PizzaOutletTemplate = PizzaHat()
    pizzaOutletTemplate.requestMenuThenOrderPizza()
    println("\n")
    pizzaOutletTemplate = Domino()
    pizzaOutletTemplate.requestMenuThenOrderPizza()
}

