package com.example.designpatternsdemo.structuraldesgnpattern.flyweightpattern

interface CharI {
    fun display(row: Int, col: Int)
}

class Character(
    private val char: Char,
    private val fontType: String,
    private val size: Int,
    //val row: Int, //Removed - Extrinsic data
    //col: Int //Removed - Extrinsic data
) : CharI {
    override fun display(row: Int, col: Int) {//Extrinsic data is passed as method parameters
        println("$this : $char at $row, $col")
    }
}

object CharObjects {
    val a = lazy { Character('a', Font.ARIAL.name, 14) }
    val b = lazy { Character('b', Font.ARIAL.name, 14) }
    val c = lazy { Character('c', Font.ARIAL.name, 14) }
    val d = lazy { Character('d', Font.ARIAL.name, 14) }
    val e = lazy { Character('e', Font.ARIAL.name, 14) }
    val f = lazy { Character('f', Font.ARIAL.name, 14) }
    val g = lazy { Character('g', Font.ARIAL.name, 14) }
    val h = lazy { Character('h', Font.ARIAL.name, 14) }
    val i = lazy { Character('i', Font.ARIAL.name, 14) }
    val j = lazy { Character('j', Font.ARIAL.name, 14) }
    val k = lazy { Character('k', Font.ARIAL.name, 14) }
    val l = lazy { Character('l', Font.ARIAL.name, 14) }
    val m = lazy { Character('m', Font.ARIAL.name, 14) }
    val n = lazy { Character('n', Font.ARIAL.name, 14) }
    val o = lazy { Character('o', Font.ARIAL.name, 14) }
    val p = lazy { Character('p', Font.ARIAL.name, 14) }
    val q = lazy { Character('q', Font.ARIAL.name, 14) }
    val r = lazy { Character('r', Font.ARIAL.name, 14) }
    val s = lazy { Character('s', Font.ARIAL.name, 14) }
    val t = lazy { Character('t', Font.ARIAL.name, 14) }
    val u = lazy { Character('u', Font.ARIAL.name, 14) }
    val v = lazy { Character('v', Font.ARIAL.name, 14) }
    val w = lazy { Character('w', Font.ARIAL.name, 14) }
    val x = lazy { Character('x', Font.ARIAL.name, 14) }
    val y = lazy { Character('y', Font.ARIAL.name, 14) }
    val z = lazy { Character('z', Font.ARIAL.name, 14) }
    val space = lazy { Character(' ', Font.ARIAL.name, 14) }
}

class CharFactory {
    companion object {
        fun create(charType: CharType) = when (charType) {
            CharType.A -> CharObjects.a
            CharType.B -> CharObjects.b
            CharType.C -> CharObjects.c
            CharType.D -> CharObjects.d
            CharType.E -> CharObjects.e
            CharType.F -> CharObjects.f
            CharType.G -> CharObjects.g
            CharType.H -> CharObjects.h
            CharType.I -> CharObjects.i
            CharType.J -> CharObjects.j
            CharType.K -> CharObjects.k
            CharType.L -> CharObjects.l
            CharType.M -> CharObjects.m
            CharType.N -> CharObjects.n
            CharType.O -> CharObjects.o
            CharType.P -> CharObjects.p
            CharType.Q -> CharObjects.q
            CharType.R -> CharObjects.r
            CharType.S -> CharObjects.s
            CharType.T -> CharObjects.t
            CharType.U -> CharObjects.u
            CharType.V -> CharObjects.v
            CharType.W -> CharObjects.w
            CharType.X -> CharObjects.x
            CharType.Y -> CharObjects.y
            CharType.Z -> CharObjects.z
            CharType.SPACE -> CharObjects.space

        }
    }
}

enum class CharType {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, SPACE
}

enum class Font {
    ARIAL
}

private fun main() {
    //my name is rajkumar
    //m - 3, y - 1, n -1, a - 3, e - 1, i - 1, s - 1, r - 2, j - 1,  k -1, u - 1, space - 3

    val m = CharFactory.create(CharType.M)
    m.value.display(0, 0)

    val y = CharFactory.create(CharType.Y)
    y.value.display(0, 1)

    val space1 = CharFactory.create(CharType.SPACE)
    space1.value.display(0, 2)

    val n = CharFactory.create(CharType.N)
    n.value.display(0, 3)

    val a = CharFactory.create(CharType.A)
    a.value.display(0, 4)

    //Here reusing the existing m object and saving the memory
    val m2 = CharFactory.create(CharType.M)
    m2.value.display(0, 5)
}


