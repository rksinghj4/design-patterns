package com.example.designpatternsdemo.structuraldesgnpattern.compositepattern

class File(private val fileName: String) {
    fun ls() {
        println("File name: $fileName")
    }
}

class Directory(private val dirName: String) {
    private val directories: MutableList<Any> = lazy { mutableListOf<Any>() }.value

    fun add(obj: Any) {
        directories.add(obj)
    }

    fun ls() {
        println("Dir name: $dirName")
        directories.forEach {

            if (it is Directory) {
                it.ls()
            } else if (it is File) {
                it.ls()
            }

            /**
             * Problem here is we can't directly execute ls method without if else ladder
             */
            //it.ls()
        }

    }
}

private fun main() {
    val moviesDir = Directory(dirName = "Movies")
    val hollywoodDir = Directory(dirName = "Hollywood")
    hollywoodDir.add(File(fileName = "Troy"))
    hollywoodDir.add(File(fileName = "300"))

    val bollywoodDir = Directory(dirName = "Bollywood")
    bollywoodDir.add(File(fileName = "Pushpa"))
    bollywoodDir.add(File(fileName = "Vijaya path"))

    moviesDir.add(hollywoodDir)
    moviesDir.add(bollywoodDir)

    moviesDir.ls()
}