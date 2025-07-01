package com.example.solidprinciple

/**
 * Interface Segregation: make fine grained interfaces that are client specific.
 *
 * Interface should have client specific functionality only
 * so that client won't have to implement unnecessary functionality.
 */

interface Shape {
    fun calculateArea() : Double
    fun calculateVolume() : Double
}

class Rectangle: Shape {

    override fun calculateArea() : Double {
        TODO("Not yet implemented")
        return 0.0
    }

    override fun calculateVolume() : Double {
        // Volume can't be calculated for 2D shape. Client is unnecessarily overriding it.
        return 0.0
    }

}

class Cuboid : Shape {
    override fun calculateArea(): Double {
        TODO("Not yet implemented")
        return 0.0
    }

    override fun calculateVolume(): Double {
        //TODO("Not yet implemented")
        return 0.0
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
        //TODO("Not yet implemented")
        return 0.0
    }
}

class CuboidSolution : TwoDShape, ThreeDShape {
    override fun calculateArea(): Double {
        //TODO("Not yet implemented")
        return 0.0
    }

    override fun calculateVolume(): Double {
        //TODO("Not yet implemented")
        return 0.0
    }
}