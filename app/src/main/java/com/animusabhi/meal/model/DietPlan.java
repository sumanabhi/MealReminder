
package com.animusabhi.meal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DietPlan implements Serializable {

    @SerializedName("diet_duration")
    @Expose
    private Integer dietDuration;
    @SerializedName("week_diet_data")
    @Expose
    private WeekDietData weekDietData;

    public Integer getDietDuration() {
        return dietDuration;
    }

    public void setDietDuration(Integer dietDuration) {
        this.dietDuration = dietDuration;
    }

    public WeekDietData getWeekDietData() {
        return weekDietData;
    }

    public void setWeekDietData(WeekDietData weekDietData) {
        this.weekDietData = weekDietData;
    }

}
