package com.example.myapplication

data class Expense (
//data class holds data.  Will contain a property and constructors.  No logic
    //val = const,  var = variable
    val name: String,
    val cost: Float,
    //might need to change to be an Enum.
    val category: String,

    )