package com.example.designpatternsdemo.behavioraldp.iteratorpattern

import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Bicycle
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Bike
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.FlameThrower
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Rifle
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.StormTrooper2
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Trooper2
import com.example.designpatternsdemo.structuraldesgnpattern.compositepattern.Squad

private fun main() {
    val trooper1 = StormTrooper2(Rifle(), Bike())
    val trooper2 = StormTrooper2(FlameThrower(), Bicycle())

    val squad1 = Squad(trooper1, trooper1.copy())
    val platoon = Squad(squad1, trooper2, squad1.copy())//Composite objects
    //If Squad doesn't have iterator then Error - For-loop range must have an 'iterator()' method
    //for(trooper in platoon)
    platoon.displayDetails()
}