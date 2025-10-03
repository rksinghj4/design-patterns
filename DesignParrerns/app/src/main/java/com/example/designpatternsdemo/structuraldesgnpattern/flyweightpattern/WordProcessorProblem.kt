package com.example.designpatternsdemo.structuraldesgnpattern.flyweightpattern

private class MyChar(
    val char: Char,
    val fontType: String,
    val size: Int,
    val row: Int,
    col: Int
)

private fun main() {
    //my name is rajkumar
    //m - 3, y - 1, n -1, a - 3, e - 1, i - 1, s - 1, r - 2, j - 1,  k -1, u - 1, space - 3

    val m = MyChar('m', "Arial", 14, 0, 0)
    val y = MyChar('y', "Arial", 14, 0, 1)
    val space1 = MyChar(' ', "Arial", 14, 0, 2)
    val n = MyChar('n', "Arial", 14, 0, 3)
    val a = MyChar('a', "Arial", 14, 0, 4)
    val m2 = MyChar('m', "Arial", 14, 0, 5) //Creating same object again
}