package com.example.mytamagotchi.Ulities;

public class DateObj {
    public String dateCreated , timeCreated , lastTime , lastDate;
    public int hours , mins , milis;
    public DateObj(String dateCreated , String timeCreated ,String  lastTime , String lastDate , int hours , int mins , int milis){
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.lastTime = lastTime;
        this.lastDate = lastDate;
        this.hours = hours;
        this.mins = mins;
        this.milis = milis;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public void setMilis(int milis) {
        this.milis = milis;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getHours() {
        return hours;
    }

    public int getMilis() {
        return milis;
    }

    public int getMins() {
        return mins;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public String getLastTime() {
        return lastTime;
    }
}
