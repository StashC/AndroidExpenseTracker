package com.example.myapplication

import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expense.view.*

class ExpenseAdapter(
    private val expenses: MutableList<Expense>

) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>(){

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_expense,
                parent,
                false
            )
        )
    }

    fun addExpense(expense: Expense){
        expenses.add(expense);
        notifyItemInserted(expenses.size - 1)
    }

    fun deleteExpense(expense: Expense){
        var i = expenses.indexOf(expense)
        expenses.remove(expense);
        notifyItemRemoved(i)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val curExpense = expenses[position]
        holder.itemView.apply {
            tvExpenseName.text = curExpense.name
            tvExpenseCost.text = curExpense.cost.toString()
            //tvExpenseCost.text = getString(R.string.expense_cost, curExpense.cost);
            tvExpenseCategory.text = curExpense.category
        }
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}