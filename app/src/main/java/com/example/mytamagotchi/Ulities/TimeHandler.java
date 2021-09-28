package com.example.mytamagotchi.Ulities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeHandler {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long calcDate(String date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat _year = new SimpleDateFormat("yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat _month = new SimpleDateFormat("MM");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat _day = new SimpleDateFormat("dd");
        try {
            Date year = _year.parse(date);
            Date month = _month.parse(date);
            Date day = _day.parse(date);
            String[] output = date.split("-");
            LocalDate today = LocalDate.now(ZoneId.systemDefault());
            //LocalDate eventDate = LocalDate.of(Integer.valueOf(output[0]),Integer.valueOf(output[1]),Integer.valueOf(output[2]));
            LocalDate eventDate = LocalDate.of(2021,9, 8);
            return ChronoUnit.DAYS.between(eventDate, today);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCurrentDate(){
        return (LocalDate.now(ZoneId.systemDefault())).toString();
    }
}
