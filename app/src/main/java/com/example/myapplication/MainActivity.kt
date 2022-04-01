package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //lateinit promises kotlin we will initialize it later
    private lateinit var expenseAdapter : ExpenseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expenseAdapter = ExpenseAdapter(mutableListOf())

        //rvExpenseItems is the tag we set for the RecyclerView in the activity_main.xml
        rvExpenseItems.adapter = expenseAdapter
        rvExpenseItems.layoutManager = LinearLayoutManager(this)

        val spinner: Spinner = findViewById(spnrExpenseCategory.id)

        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            //specify the loayout to use
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter;
        }

        btnAddExpense.setOnClickListener{
            val expenseName = ExpenseNameIn.text.toString()
            val expenseCost = ExpenseCostIn.text.toString().toFloat()
            val expenseCategory = spnrExpenseCategory.selectedItem.toString()
            if(expenseName.isNotEmpty() && expenseCost > 0 && expenseCategory.isNotEmpty()){
                val expense = Expense(expenseName, expenseCost, expenseCategory)
                expenseAdapter.addExpense(expense)
                ExpenseNameIn.text.clear()
                ExpenseCostIn.text.clear()
            }
        }
    }
}