package com.example.myapplication

import org.json.JSONObject

class Expense (
//data class holds data.  Will contain a property and constructors.  No logic
    //val = const,  var = variable
    val name: String,
    val cost: Double,
    //might need to change to be an Enum.
    val category: String,
    ) {
    fun toJson(): JSONObject? {
        val json = JSONObject()
        json.put("name", name)
        json.put("cost", cost)
        json.put("category", category)
        return json
    }
}