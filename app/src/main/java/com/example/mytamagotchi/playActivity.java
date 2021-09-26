package com.example.mytamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class playActivity extends AppCompatActivity {
    private TextView ui_txt_age , ui_txt_hunger , ui_txt_water , ui_txt_happy , ui_txt_health , ui_txt_pets_name;
    Bundle extras;
    private JSONArray petJson;
    private JSONObject jsonObject;
    private String tempString;
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
        extras = getIntent().getExtras();
        tempString = (String) extras.getString("petsArray");
        Log.d("Dor",tempString);
        try {
            jsonObject = new JSONObject(tempString);
            ui_txt_age.setText(jsonObject.getString("Age"));
            ui_txt_hunger.setText(jsonObject.getString("Hunger"));
            ui_txt_water.setText(jsonObject.getString("Water"));
            ui_txt_happy.setText(jsonObject.getString("Happy"));
            ui_txt_health.setText(jsonObject.getString("Health"));
            ui_txt_pets_name.setText(jsonObject.getString("Name"));

        } catch (JSONException e) {
            Log.d("Dor","error "+e.toString());
        }


    }

}