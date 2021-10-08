package com.example.mytamagotchi.Ulities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeHandler {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long calcDate(String date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        date = date.replaceAll("/","-");
        LocalDate eventDate = LocalDate.parse(date);
        Log.d("Doriiii", String.valueOf(ChronoUnit.DAYS.between(eventDate,today)));
        return ChronoUnit.DAYS.between(eventDate,today);
    }
    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long caclTime(Date startDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        calc2(endDate,startDate);
        long different = (endDate.getTime()) - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        long elapsedSeconds = different / secondsInMilli;
        return elapsedHours;
    }
    */

    public String caclTime(Date date1 , Date date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        long difference = date2.getTime() - date1.getTime();
        int days = (int) (difference / (1000 * 60 * 60 * 24));
        int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
        hours = (hours < 0 ? -hours : hours);

        long millis = date1.getTime() - date2.getTime();
        hours = (int) (millis / (1000 * 60 * 60));
        int mins = (int) ((millis / (1000 * 60)) % 60);
        String diff = hours + ":" + mins +":";
        return diff;

    }
    public int[] caclTimeArray(Date date1 , Date date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        long difference = date2.getTime() - date1.getTime();
        int days = (int) (difference / (1000 * 60 * 60 * 24));
        int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
        hours = (hours < 0 ? -hours : hours);

        long millis = date1.getTime() - date2.getTime();
        hours = (int) (millis / (1000 * 60 * 60));
        int mins = (int) ((millis / (1000 * 60)) % 60);
        String diff = hours + ":" + mins;
        String[] result_temp = diff.split(":");

        int[] result = {Integer.parseInt(result_temp[0]), Integer.parseInt(result_temp[1])};
        Log.d("Dor","result "+result[0]+" "+result[1]);
        return result;

    }
    public DateObj caclTimeObj(Date date1 , Date date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        DateObj dateObj = null;
        Date finalDate;
        long difference = date2.getTime() - date1.getTime();
        int days = (int) (difference / (1000 * 60 * 60 * 24));
        int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
        hours = (hours < 0 ? -hours : hours);
        long millis = date1.getTime() - date2.getTime();
        hours = (int) (millis / (1000 * 60 * 60));
        int mins = (int) ((millis / (1000 * 60)) % 60);
        dateObj.hours = hours;
        dateObj.mins = mins;
        return dateObj;

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCurrentDate(){
        //Log.d("Dori", String.valueOf(LocalDate.now(ZoneId.systemDefault())));
        return (LocalDate.now(ZoneId.systemDefault())).toString();
    }
    public String getCurrentTime(){
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        //Log.d("Dori",formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }
    public String getCurrentTimeDate() {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Log.d("Dori",formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }
    public Date stringToDate(String aDate,int action) {
        SimpleDateFormat formatter;
        ParsePosition pos = new ParsePosition(0);
        Date stringDate;
        if(aDate==null) return null;
        switch (action){
            case 1:
                 formatter = new SimpleDateFormat("HH:mm:ss");
                 stringDate = formatter.parse(aDate, pos);
                 return stringDate;
            case 2:
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                stringDate = formatter.parse(aDate, pos);
                return stringDate;

        }
        return null;
    }

}
