package com.example.designpatternsdemo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * The Adapter design pattern is a structural design pattern that acts as a bridge,
 * allowing incompatible interfaces of existing classes to work together without changing their source code.
 * It acts as a wrapper or connector, converting the interface of a class (the "adaptee")
 * into another interface (the "target") that a client expects. This pattern is often used
 * to integrate legacy systems with new components
 */


//Target domain model
data class Person(val name: String, val age: Int)

//Target interface, which client understands
interface DataInfo {
    suspend fun getData(): Person
}
//Adapter: The class that implements the target interface and wraps the adaptee.
class PersonJsonTODataAdapter(private val personJSON: JSONInfo) : DataInfo {
    //Here we are adapting JSONInfo to DataInfo
    override suspend fun getData(): Person {
        val personJsonObject = personJSON.fetchJson()
        return Person(
            name = personJsonObject.optString("personName"),
            age = personJsonObject.optInt("personAge")
        )
    }
}

/**
 * Adaptee: The existing class or system with an incompatible interface that needs to be integrated
 * into the new system. It’s the class or system that the client code cannot directly use due to
 * interface mismatches.
 *
 */

interface JSONInfo {
    suspend fun fetchJson(): JSONObject
}

class PersonJSONInfo : JSONInfo {
    override suspend fun fetchJson(): JSONObject = withContext(Dispatchers.IO) {
        //Note JSONObject can be tested with emulator/real device
        //So AdapterPattern is tested using MainActivity
        val jsonObject = JSONObject()
        jsonObject.put("personName", "Mohan")
        jsonObject.put("personAge", 20)
        return@withContext jsonObject
    }
}

fun clientMain() = runBlocking {
    //Note JSONObject can't be initialize without emulator/real device
    //So AdapterPattern is tested using MainActivity, not with main method
    val personJSONInfo = PersonJSONInfo()
    val personJsonTODataAdapter = PersonJsonTODataAdapter(personJSONInfo)
    val person = CoroutineScope(Dispatchers.IO).async {
        personJsonTODataAdapter.getData()
    }.await()

    println("$person")
}