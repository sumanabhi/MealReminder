package com.animusabhi.meal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.animusabhi.meal.MealPlanActivity
import com.animusabhi.meal.R
import com.animusabhi.meal.model.Days
import com.animusabhi.meal.model.DietPlan
import com.animusabhi.meal.model.MealDetails
import com.animusabhi.meal.network.NotifyWithData
import java.util.concurrent.TimeUnit


class MealPlanAdapter(
    val mainActivity: MealPlanActivity,
    val dietPlan: DietPlan?,
    val days: String
) : RecyclerView.Adapter<MealPlanAdapter.MealListViewHolder>() {

    lateinit var mealDetails: ArrayList<MealDetails>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MealListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return updateUI(dietPlan, days).size
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        holder.mealTextView.text = String.format("Food :-  %s", mealDetails[position].food)
        holder.mealTimeTextView.text =
            String.format("Meal Time :-  %s", mealDetails[position].mealTime)

//        // Here we are Passing Data to MyWorkWithData class.
//        // We are also getting Data from MyWorkWithData class.
//        // After getting Data from MyWorkWithData class we are setting to TextView.
//        val data = Data.Builder()
//            .putString(NotifyWithData.EXTRA_TITLE, "Meal Reminder")
//            .putString(NotifyWithData.EXTRA_TEXT, "This is a gentle reminder to take "+ (mealDetails[position].food))
//            .build()
//
//        val oneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(NotifyWithData::class.java)
//                .setInitialDelay(60000, TimeUnit.MILLISECONDS)
//                .setInputData(data)
//                .build()
//
//        WorkManager.getInstance(mainActivity).enqueue(oneTimeWorkRequest)
//
//        WorkManager.getInstance(mainActivity).getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(mainActivity, Observer {
//            if (it != null && it.state.isFinished) {
//                val message: String =
//                    it.outputData.getString(NotifyWithData.EXTRA_OUTPUT_MESSAGE).toString()
//                Log.e("WorkManager Info", message);
//            }
//        })

    }

    private fun updateUI(dietPlan: DietPlan?, days: String): ArrayList<MealDetails> {
        mealDetails = when (days) {
            Days.Monday.toString() -> {
                dietPlan!!.weekDietData!!.monday
            }
            Days.Tuesday.toString() -> {
                dietPlan!!.weekDietData!!.tuesday
            }
            Days.Wednesday.toString()
            -> {
                dietPlan!!.weekDietData!!.wednesday
            }
            Days.Thursday.toString()
            -> {
                dietPlan!!.weekDietData!!.thursday
            }
            Days.Friday.toString()
            -> {
                dietPlan!!.weekDietData!!.friday
            }
            Days.Saturday.toString()
            -> {
                dietPlan!!.weekDietData!!.saturday
            }
            else -> {
                mealDetails = ArrayList<MealDetails>()
            }
        } as ArrayList<MealDetails>
        return mealDetails as ArrayList<MealDetails>
    }

    class MealListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mealTextView = itemView.findViewById(R.id.mealTV) as TextView
        var mealTimeTextView = itemView.findViewById(R.id.mealTimeTV) as TextView
    }
}