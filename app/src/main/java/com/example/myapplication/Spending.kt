package com.example.myapplication;

import android.app.Activity;
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentSpendingBinding
import kotlinx.android.synthetic.main.fragment_spending.*

class Spending : Fragment()  {
    private var _binding: FragmentSpendingBinding? = null
    private val binding get() = _binding!!

    //lateinit promises kotlin we will initialize it later
    private lateinit var expenseAdapter : ExpenseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        _binding = FragmentSpendingBinding.inflate(inflater, container, false)
        val retrievedExpenseList = ExpensePreferencesManager.get<ExpenseList>("Expense_List")
        if(retrievedExpenseList != null){
            expenseAdapter = ExpenseAdapter(retrievedExpenseList)
        } else {
            expenseAdapter = ExpenseAdapter(ExpenseList(ArrayList()))
        }

        val view = binding.root

    return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //rvExpenseItems is the tag we set for the RecyclerView in the activity_main.xml

        rvExpenseItems.adapter = expenseAdapter
        rvExpenseItems.layoutManager = LinearLayoutManager(this.context)

        val spinner: Spinner = view.findViewById(spnrExpenseCategory.id)

        ArrayAdapter.createFromResource(
            this.requireContext(),
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

                println("expense added")
                ExpensePreferencesManager.put(expenseAdapter.adpter_expenses, "Expense_List")

            }
        }
    }
}
