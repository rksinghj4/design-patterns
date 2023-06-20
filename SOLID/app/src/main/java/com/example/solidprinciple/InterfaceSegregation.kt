package com.example.solidprinciple

/**
 * Interface Segregation: make fine grained interfaces that are client specific.
 */

interface Shape {
    fun calculateArea() : Double
    fun calculateVolume() : Double
}

class Rectangle: Shape {

    override fun calculateArea() : Double {
        TODO("Not yet implemented")
    }

    override fun calculateVolume() : Double {
        // Volume can't be calculated for 2D shape so unnecessarily we are overriding it.
        return 0.0
    }

}

class Cuboid : Shape {
    override fun calculateArea(): Double {
        TODO("Not yet implemented")
    }

    override fun calculateVolume(): Double {
        TODO("Not yet implemented")
    }

}

/**
 * Solution to above problem is interface segregation.
 */


interface TwoDShape {//SRP, OCP
    fun calculateArea() : Double
}

interface ThreeDShape {//SRP, OCP
    fun calculateVolume() : Double
}

class RectangleSolution: TwoDShape {
    override fun calculateArea(): Double {
        TODO("Not yet implemented")
    }
}

class CuboidSolution : TwoDShape, ThreeDShape {
    override fun calculateArea(): Double {
        TODO("Not yet implemented")
    }

    override fun calculateVolume(): Double {
        TODO("Not yet implemented")
    }
}