package com.example.mytamagotchi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.mytamagotchi.Ulities.DateObj;
import com.example.mytamagotchi.Ulities.TimeHandler;
import com.example.mytamagotchi.Ulities.valuesObj;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class playActivity extends AppCompatActivity {
    private TextView ui_txt_age , ui_txt_hunger , ui_txt_water , ui_txt_happy , ui_txt_health , ui_txt_pets_name;
    private String v_type , v_name , v_date , v_time , v_lasTime , v_lastDate ,  currentTime , currentDate;
    private int pos;
    private int v_age , v_hunger , v_water,v_happy , v_health ;
    private ImageButton mealbutton , treatbutton ,waterbutton , playbutton , punishbutton , shotbutton;
    Bundle extras;
    private JSONArray petJson;
    private JSONObject jsonObject;
    private String tempString;
    private Intent intent;
    private TimeHandler timeHandler;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdtior;
    private Window window;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ui_txt_age = (TextView) findViewById(R.id.ageinttext);
        ui_txt_hunger = (TextView) findViewById(R.id.hungerinttext);
        ui_txt_water = (TextView) findViewById(R.id.waterinttext);
        ui_txt_happy = (TextView) findViewById(R.id.happyinttext);
        ui_txt_health = (TextView) findViewById(R.id.healthinttext);
        ui_txt_pets_name = (TextView) findViewById(R.id.loadedpetname);
        Objects.requireNonNull(getSupportActionBar()).hide();
        pref = getSharedPreferences("tama", Context.MODE_PRIVATE);
        extras = getIntent().getExtras();
        tempString = (String) extras.getString("petsArray");
        pos = extras.getInt("count");
        YoYo.with(Techniques.RotateIn)
                .duration(700)
                .repeat(5)
                .playOn(findViewById(R.id.waterinttext));
        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(5)
                .playOn(findViewById(R.id.loadedpetname));
        Log.d("Dozr",tempString);

        try {
            petJson = new JSONArray(tempString);
            jsonObject = petJson.getJSONObject(extras.getInt("pos"));
            TimeHandler timeHandler = new TimeHandler();
            currentDate = timeHandler.getCurrentDate();
            currentTime = timeHandler.getCurrentTime();
            v_date = jsonObject.getString("Date");
            v_lastDate = jsonObject.getString("Date");
            v_time = jsonObject.getString("Time");
            v_lasTime = jsonObject.getString("lastTime");
            Log.d("Dor","v_lastDate "+v_time);
            Log.d("Dor","currentTime "+currentTime);


            int HoursAndMins[] = timeHandler.caclTimeArray(timeHandler.stringToDate(v_time,1),timeHandler.stringToDate(currentTime,1));
            DateObj dateObj = new DateObj(v_date , v_time , v_lasTime , v_lastDate,
                    HoursAndMins[0],HoursAndMins[1],0
            );

            jsonObject.put("Age",(String.valueOf(timeHandler.calcDate(jsonObject.getString("Date")))));
            ui_txt_age.setText(jsonObject.getString("Age"));
            ui_txt_hunger.setText(jsonObject.getString("Hunger"));
            ui_txt_water.setText(jsonObject.getString("Water"));
            ui_txt_happy.setText(jsonObject.getString("Happy"));
            ui_txt_health.setText(jsonObject.getString("Health"));
            ui_txt_pets_name.setText(jsonObject.getString("Name"));


//            Date date2 = timeHandler.stringToDate(jsonObject.getString("Date"),2);
//            Log.d("Doriiii", String.valueOf(timeHandler.calcDate(String.valueOf(date2))));
            //DateObj dateObj = new DateObj(v_date , v_time , v_lasTime , v_lastDate , )

        } catch (JSONException e) {
            Log.d("Dor","error "+e.toString());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public valuesObj ValuesMmnipulation(JSONObject jsonObject
    ){
        try {
            String lastTime = jsonObject.getString("lastTime");
            String lastDate = jsonObject.getString("lastDate");

            if (lastDate!=null){
                Date date1 = timeHandler.stringToDate(timeHandler.getCurrentDate(),2);
                Date date2 = timeHandler.stringToDate(jsonObject.getString("Date"),2);
                DateObj dateObj = timeHandler.caclTimeObj(date1,date2);
                Log.d("test", String.valueOf(dateObj.hours));
                Log.d("test", String.valueOf(dateObj.mins));
            }
            valuesObj values = new valuesObj(
                    jsonObject.getInt("Age"),jsonObject.getInt("Water"),jsonObject.getInt("Hunger"),jsonObject.getInt("Happy"),jsonObject.getInt("Health")
            );
            values.setAge( values.getAge() );
            return values;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mealbutton:
                int temp = Integer.valueOf(String.valueOf(ui_txt_hunger.getText()));
                if(temp<100){
                    ui_txt_hunger.setText(String.valueOf(temp+10));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_happy.getText()));
                if(temp<100){
                    ui_txt_happy.setText(String.valueOf(temp+5));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_health.getText()));
                if(temp<100){
                    ui_txt_health.setText(String.valueOf(temp+5));
                }
                break;
            case R.id.treatbutton:
                temp = Integer.valueOf(String.valueOf(ui_txt_hunger.getText()));
                if(temp<100){
                    ui_txt_hunger.setText(String.valueOf(temp+5));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_happy.getText()));
                if(temp<100){
                    ui_txt_happy.setText(String.valueOf(temp+2));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_health.getText()));
                if(temp<100){
                    ui_txt_health.setText(String.valueOf(temp+2));
                }
                break;
            case R.id.waterbutton:
                temp = Integer.valueOf(String.valueOf(ui_txt_water.getText()));
                if(temp<100){
                    ui_txt_water.setText(String.valueOf(temp+20));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_happy.getText()));
                if(temp<100){
                    ui_txt_happy.setText(String.valueOf(temp+5));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_health.getText()));
                if(temp<100){
                    ui_txt_health.setText(String.valueOf(temp+5));
                }
                break;
            case R.id.playbutton:
                temp = Integer.valueOf(String.valueOf(ui_txt_happy.getText()));
                if(temp<100){
                    ui_txt_happy.setText(String.valueOf(temp+10));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_health.getText()));
                if(temp<100){
                    ui_txt_health.setText(String.valueOf(temp+10));
                }
                break;
            case R.id.punishbutton:
                temp = Integer.valueOf(String.valueOf(ui_txt_happy.getText()));
                if(temp<100 & temp>0){
                    ui_txt_happy.setText(String.valueOf(temp-10));
                }
                temp = Integer.valueOf(String.valueOf(ui_txt_health.getText()));
                if(temp<100 & temp>0){
                    ui_txt_health.setText(String.valueOf(temp-10));
                }
                break;
            case R.id.shotbutton:
                ui_txt_hunger.setText("100");
                ui_txt_water.setText("100");
                ui_txt_happy.setText("100");
                ui_txt_health.setText("100");
                break;

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        pref = getSharedPreferences("tama", Context.MODE_PRIVATE);
        prefEdtior = pref.edit();
        prefEdtior.putString("JSON",petJson.toString());
        prefEdtior.apply();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        pref = getSharedPreferences("tama", Context.MODE_PRIVATE);
        prefEdtior = pref.edit();
        prefEdtior.putString("JSON",petJson.toString());
        prefEdtior.apply();
        super.onDestroy();
    }
}
