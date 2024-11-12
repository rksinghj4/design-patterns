package com.example.designpatternsdemo.creationaldesignpattern.prototypepattern

/**
 *  Prototype Pattern: A creational design pattern
 *
 *  When object creation is a time-consuming, and costly, resource consuming and heavy operation
 *  then we create objects by coping the existing object itself by tweaking some of it's properties.
 */

//Problem: Adding copy methods for all possible combinations of parameters is overhead.
//Just for under standing we have done this. In kotlin we can use data clas,
// which provide the copy method

//Prototype
interface Shape {
    fun copy(): Shape
    fun copy(color: String, a: Int): Shape
    fun copy(color: String, a: Int, b: Int): Shape
}

//Concrete class of prototype
class Circle(private val color: String, private val radius: Int) : Shape {
    //By default, copy() performs a shallow copy.
    //A shallow copy means we only copy references to existing objects, not the actual objects or values.
    //If we have an object with a complex schema, for example, having nested objects at multiple levels,
    //and we only copy the top layer of fields, that is also a kind of shallow copy.

    //Meanwhile, deep copy means creating a copy that actually creates a new object for each field,
    // not just a reference copy.
    override fun copy(color: String, a: Int, b: Int): Shape =
        Circle(color, radius = a)

    override fun copy(): Shape = Circle(this.color, this.radius)
    override fun copy(color: String, a: Int): Shape = Circle(this.color, a)

    override fun toString(): String {
        return "Circle - $color, radius = $radius"
    }
}

//Concrete class of prototype
class Rectangle(private val color: String, private val a: Int, private val b: Int) : Shape {
    //New object with all different properties
    override fun copy(color: String, a: Int, b: Int): Shape =
        Rectangle(color, a, b)

    //New object with same properties
    override fun copy(): Shape = Rectangle(this.color, a, b)

    //New object with some different properties
    override fun copy(color: String, a: Int): Shape = copy(this.color, a, this.b)

    override fun toString(): String {
        return "Rectangle - $color, a = $a, b= $b"
    }
}

private fun main() {
    var shape: Shape = Circle("Red", 3)
    println("Shape: $shape")
    shape = shape.copy("White", 2)
    println("Shape: $shape")

    shape = Rectangle("Black", 2, 3)
    println("Shape: $shape")
    shape = shape.copy("Green", 4)
    println("Shape: $shape")
}
