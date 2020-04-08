package com.animusabhi.meal.network

import com.animusabhi.meal.model.DietPlan
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("/dummy/")
    fun doGetMealNotificationList(): Call<DietPlan?>?
}