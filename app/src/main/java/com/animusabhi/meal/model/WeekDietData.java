
package com.animusabhi.meal.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeekDietData implements Serializable {

    @SerializedName("thursday")
    @Expose
    private List<MealDetails> thursday = null;
    @SerializedName("wednesday")
    @Expose
    private List<MealDetails> wednesday = null;
    @SerializedName("monday")
    @Expose
    private List<MealDetails> monday = null;
    private List<MealDetails> tuesday = null;
    private List<MealDetails> friday = null;
    private List<MealDetails> saturday = null;

    public List<MealDetails> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<MealDetails> tuesday) {
        this.tuesday = tuesday;
    }

    public List<MealDetails> getFriday() {
        return friday;
    }

    public void setFriday(List<MealDetails> friday) {
        this.friday = friday;
    }

    public List<MealDetails> getSaturday() {
        return saturday;
    }

    public void setSaturday(List<MealDetails> saturday) {
        this.saturday = saturday;
    }

    public List<MealDetails> getThursday() {
        return thursday;
    }

    public void setThursday(List<MealDetails> thursday) {
        this.thursday = thursday;
    }

    public List<MealDetails> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<MealDetails> wednesday) {
        this.wednesday = wednesday;
    }

    public List<MealDetails> getMonday() {
        return monday;
    }

    public void setMonday(List<MealDetails> monday) {
        this.monday = monday;
    }
}
