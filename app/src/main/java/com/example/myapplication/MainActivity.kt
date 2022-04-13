package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.json.*

val tabArray = arrayOf(
    "Spending",
    "Overview",
    "Budget"
)

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExpensePreferencesManager.with(this.application)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val mainView = binding.root
        setContentView(mainView)

        val viewPager = binding.mainViewPager
        val tabLayout = binding.tabLayout

        val adapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.text = tabArray[position]
        }.attach()
    }
}