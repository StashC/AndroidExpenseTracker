package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expense.view.*

class ExpenseAdapter(
    var adpter_expenses: ExpenseList

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
        adpter_expenses.expenses.add(expense);
        notifyItemInserted(adpter_expenses.expenses.size - 1)
    }

    fun deleteExpense(expense: Expense){
        var i = adpter_expenses.expenses.indexOf(expense)
        adpter_expenses.expenses.remove(expense);
        notifyItemRemoved(i)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val curExpense = adpter_expenses.expenses[position]
        holder.itemView.apply {
            tvExpenseName.text = curExpense.name
            tvExpenseCost.text = curExpense.cost.toString()
            //tvExpenseCost.text = getString(R.string.expense_cost, curExpense.cost);
            tvExpenseCategory.text = curExpense.category
            btnRemove.setOnClickListener{
                deleteExpense(curExpense)
                ExpensePreferencesManager.put(adpter_expenses, "Expense_List")
            }
        }
    }

    override fun getItemCount(): Int {
        return adpter_expenses.expenses.size
    }
}