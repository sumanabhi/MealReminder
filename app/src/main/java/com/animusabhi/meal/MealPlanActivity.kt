package com.animusabhi.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.animusabhi.meal.adapter.MealPlanAdapter
import com.animusabhi.meal.model.DietPlan
import kotlinx.android.synthetic.main.activity_meal_plan.*

class MealPlanActivity : AppCompatActivity() {
    var days = ""
    lateinit var dietPlan : DietPlan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_plan)

        getInentExtras()
        mealPlanRV.layoutManager = LinearLayoutManager(this)

        var mealPlanAdapter = MealPlanAdapter(this, dietPlan , days)
        mealPlanRV.adapter = mealPlanAdapter
    }

    private fun getInentExtras() {
        days = intent.getStringExtra("days")
        dietPlan = intent.getSerializableExtra("dietModel") as DietPlan
    }
}
