package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.*

class MainActivity : AppCompatActivity() {

    //lateinit promises kotlin we will initialize it later
    private lateinit var expenseAdapter : ExpenseAdapter

    private lateinit var sp : SharedPreferences
    //private lateinit var expensesArray : ExpenseList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExpensePreferencesManager.with(this.application)
        setContentView(R.layout.activity_main)

        val retrievedExpenseList = ExpensePreferencesManager.get<ExpenseList>("Expense_List")
        if(retrievedExpenseList != null){
            expenseAdapter = ExpenseAdapter(retrievedExpenseList)
        } else {
            expenseAdapter = ExpenseAdapter(ExpenseList(ArrayList()))
        }
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
            val expenseCost = ExpenseCostIn.text.toString().toDouble()
            val expenseCategory = spnrExpenseCategory.selectedItem.toString()
            if(expenseName.isNotEmpty() && expenseCost > 0 && expenseCategory.isNotEmpty()){
                val expense = Expense(expenseName, expenseCost, expenseCategory)
                expenseAdapter.addExpense(expense)

                ExpensePreferencesManager.put(expenseAdapter.adpter_expenses, "Expense_List")

            }
        }
    }
}