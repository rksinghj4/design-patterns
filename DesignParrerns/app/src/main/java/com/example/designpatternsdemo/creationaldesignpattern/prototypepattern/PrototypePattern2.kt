package com.example.designpatternsdemo.creationaldesignpattern.prototypepattern


class Circle2D(private val color: String, private val radius: Int) {
    fun copy(color: String = "", radius: Int = 0): Circle2D {
        return if (color.isBlank() && radius == 0) {
            //Coloring is costly, so we use same color object of existing object
            Circle2D(this.color, this.radius)
        } else if (radius != 0) {
            Circle2D(this.color, radius)
        } else if (color.isNotBlank()) {
            Circle2D(color, this.radius)
        } else {
            Circle2D(color, this.radius)
        }
    }

    override fun toString(): String {
        return "Circle - $color, radius = $radius"
    }
}

private fun main() {
    val circle = Circle2D("Red", 3)
    println("Shape: $circle")
    println("Shape: ${circle.copy()}")
    println("Shape: ${circle.copy(color = "Black")}")
    println("Shape: ${circle.copy(color = "Black", radius = 4)}")
    println("Shape: ${circle.copy(radius = 4)}")
}

