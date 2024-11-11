package com.example.designpatternsdemo.structuraldesgnpattern.facadepattern

data class Product(val prodId: Int, val name: String, val desc: String, val price: Float)
class AmazonDao {
    private val listOfProduct = mutableListOf(
        Product(1, "Pen", "Black", 100f),
        Product(2, "Stylus", "Black", 120f),
        Product(3, "Remarkable", "White", 130f),
        Product(4, "Kobo", "White", 140f),
    )

    fun getProduct(prodId: Int): Product? = listOfProduct.firstOrNull { it.prodId == prodId }
}

class Payment {
    fun makePayment(price: Float) {
        println("Payment done of Rs. $price/-")
    }
}

class Invoice {
    fun generateInvoice(product: Product) {
        println("Invoice generated for product:  $product")
    }
}

class Notification {
    fun sendNotification(product: Product) {
        println("Notification is sent to customer mobile for product: $product")
    }
}

/**
 * In Factory pattern object creation was encapsulated.
 * In Facade, not only object creation but also method invocation is encapsulated.
 */

//Facade - a simplified interface to a complex system, making it easier to use.
class OrderFacade {
    private val amazonDao = AmazonDao()
    private val payment = Payment()
    private val invoice = Invoice()
    private val notification = Notification()

    //Facade is responsible for handling all the complexity behind the order placement.
    //In Facade pattern method invocation is encapsulated.
    //Sequence of method calls matters here, but client is free from all responsibilities.
    fun placeOrder(prodId: Int) {
        //Tomorrow if new step is introduce then just Facade will change but not the entire client code
        //Tomorrow if one method's return type is changed then just facade will changes
        // and convert the new return type to what client is already using.
        val product = amazonDao.getProduct(prodId)
        if (product == null) {
            println("Product not found")
        } else {
            println("$product is found")
            payment.makePayment(product.price)
            invoice.generateInvoice(product)
            notification.sendNotification(product)
        }
    }
}

//Client
private fun main() {
    val orderFacade = OrderFacade()
    orderFacade.placeOrder(2)

    orderFacade.placeOrder(10)
}

