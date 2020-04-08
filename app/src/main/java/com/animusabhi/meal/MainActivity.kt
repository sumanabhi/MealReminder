package com.animusabhi.meal

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.animusabhi.meal.adapter.MealListAdapter
import com.animusabhi.meal.model.Days
import com.animusabhi.meal.model.DietPlan
import com.animusabhi.meal.model.MealDetails
import com.animusabhi.meal.network.APIClient
import com.animusabhi.meal.network.APIInterface
import com.animusabhi.meal.services.AlarmNotificationReceiver
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var apiInterface: APIInterface? = null
    var mealData: ArrayList<MealDetails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = APIClient.client?.create(APIInterface::class.java)
        mealListRV.layoutManager = LinearLayoutManager(this)

        val call: Call<DietPlan?>? = apiInterface?.doGetMealNotificationList()

        call?.enqueue(object : Callback<DietPlan?> {
            override fun onFailure(call: Call<DietPlan?>, t: Throwable) {
                Log.e("FAILURE :- ", t.message.toString())
            }

            override fun onResponse(call: Call<DietPlan?>, response: Response<DietPlan?>) {
                if (response.isSuccessful) {
                    var dietPlan = response.body()
                    var mealListAdapter = MealListAdapter(this@MainActivity, dietPlan)
                    mealListRV.adapter = mealListAdapter
                    updateUI(dietPlan)
                }
                Log.e("RESPONSE :- ", response.body().toString())
            }

        })
    }

    private fun updateUI(dietPlan: DietPlan?) {
        var mealDetails = when (getCurrentDays()) {
            Days.Monday.toString() -> {
                dietPlan!!.weekDietData!!.monday
                scheduleNotification(dietPlan!!.weekDietData!!.monday, Days.Monday.toString())
            }
            Days.Tuesday.toString() -> {
                dietPlan!!.weekDietData!!.tuesday
                scheduleNotification(dietPlan!!.weekDietData!!.tuesday, Days.Tuesday.toString())
            }
            Days.Wednesday.toString()
            -> {
                dietPlan!!.weekDietData!!.wednesday
                scheduleNotification(dietPlan!!.weekDietData!!.wednesday, Days.Wednesday.toString())
            }
            Days.Thursday.toString()
            -> {
                dietPlan!!.weekDietData!!.thursday
                scheduleNotification(dietPlan!!.weekDietData!!.thursday, Days.Thursday.toString())
            }
            Days.Friday.toString()
            -> {
                dietPlan!!.weekDietData!!.friday
                scheduleNotification(dietPlan!!.weekDietData!!.friday, Days.Friday.toString())
            }
            Days.Saturday.toString()
            -> {
                dietPlan!!.weekDietData!!.saturday
                scheduleNotification(dietPlan!!.weekDietData!!.saturday, Days.Saturday.toString())
            }
            else -> " "
        }
    }

    private fun scheduleNotification(
        mealDetails: MutableList<MealDetails>,
        days: String
    ) {
        for (item in mealDetails) {
            startAlarm(item.mealTime, days)
        }
    }

    private fun startAlarm(mealTime: String, days: String) {
        val manager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // SET TIME HERE
        val calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, mealTime.split(":")[0].toInt());
        calendar.set(Calendar.MINUTE, mealTime.split(":")[1].toInt());

        val myIntent = Intent(this, AlarmNotificationReceiver::class.java);
        var pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        when {
            VERSION.SDK_INT >= 23 -> {
                pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
                manager.setRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
            VERSION.SDK_INT in 19..22 -> {
                pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
                manager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
            else -> {
                manager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
        }
    }

    private fun getCurrentDays(): String {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        return sdf.format(d)
    }
}
