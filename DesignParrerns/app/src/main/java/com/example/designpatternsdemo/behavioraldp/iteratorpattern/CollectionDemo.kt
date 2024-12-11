package com.example.designpatternsdemo.behavioraldp.iteratorpattern

import java.util.PriorityQueue
import java.util.TreeSet

/**
 * Iterator pattern - it flattens our complex data structure into a simple sequence of elements.
 * the order of the elements and what element to ignore is for iterator to decide.
 *
 */
class LinkedHashSetDemo {
    private val collection = lazy { LinkedHashSet<Int>(20, .8F) }.value
    fun add(item: Int) {
        collection.add(item)
    }

    fun display() {
        print("LinkedHashSet: ")
        //Iterator logic is same on all collection type
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            print(" ${iterator.next()}")
        }
        println()
    }
}

class TreeSetDemo {
    private val collection = lazy { TreeSet<Int>() }.value
    fun add(item: Int) {
        collection.add(item)
    }

    fun display() {
        print("TreeSet: ")

        //Iterator logic is same on all collection type
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            print(" ${iterator.next()}")
        }
        println()
    }
}

class PriorityQueueDemo {
    private val collection = lazy { PriorityQueue<Int>() }.value
    fun add(item: Int) {
        collection.add(item)
    }

    fun display() {
        print("PriorityQueue: ")
        //Iterator logic is same on all collection type
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            print(" ${iterator.next()}")
        }
        println()
    }
}

class ArrayQueueDemo {
    private val collection = lazy { ArrayDeque<Int>() }.value
    fun add(item: Int) {
        collection.add(item)
    }

    fun display() {
        print("ArrayDeque: ")
        //Iterator logic is same on all collection type
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            print(" ${iterator.next()}")
        }
        println()
    }
}


private fun main() {
    val linkedHashSetDemo = LinkedHashSetDemo()
    linkedHashSetDemo.add(1)
    linkedHashSetDemo.add(2)
    linkedHashSetDemo.add(3)
    linkedHashSetDemo.display()

    val treeSetDemo = TreeSetDemo()
    treeSetDemo.add(1)
    treeSetDemo.add(2)
    treeSetDemo.add(3)
    treeSetDemo.display()

    val priorityQueueDemo = PriorityQueueDemo()
    priorityQueueDemo.add(1)
    priorityQueueDemo.add(2)
    priorityQueueDemo.add(3)
    priorityQueueDemo.display()

    val arrayQueueDemo = ArrayQueueDemo()
    arrayQueueDemo.add(1)
    arrayQueueDemo.add(2)
    arrayQueueDemo.add(3)
    arrayQueueDemo.display()
}

