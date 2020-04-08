package com.animusabhi.meal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.animusabhi.meal.MainActivity
import com.animusabhi.meal.MealPlanActivity
import com.animusabhi.meal.R
import com.animusabhi.meal.model.Days
import com.animusabhi.meal.model.DietPlan


class MealListAdapter(
    val mainActivity: MainActivity,
    val dietPlan: DietPlan?
) : RecyclerView.Adapter<MealListAdapter.MealListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return MealListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Days.values().size
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        holder.textView.text = Days.values()[position].toString()

        holder.textView.setOnClickListener(View.OnClickListener {
            var intent = Intent(mainActivity , MealPlanActivity::class.java)
            intent.putExtra("days", holder.textView.text.toString())
            intent.putExtra("dietModel", dietPlan)
            mainActivity.startActivity(intent)
        })
    }


    class MealListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView = itemView.findViewById(R.id.textView2) as TextView
    }
}