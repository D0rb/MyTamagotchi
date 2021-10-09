package com.example.mytamagotchi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytamagotchi.Ulities.TimeHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class newGameActivity extends AppCompatActivity {
    private Intent intent;
    private ImageView catImg , dogImg;
    private EditText petNameTextEdit;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdtior;
    private String tempString = "{\n" +
            "      \"count\": 0,\n" +
            "      \"Type\": \"\",\n" +
            "      \"Name\": \"\",\n" +
            "      \"Date\": \"\",\n" +
            "      \"Time\": \"\",\n" +
            "      \"lastTime\": \"16:31:38\",\n" +
            "      \"lastDate\": \"2021/10/13\",\n" +
            "      \"Age\": 0,\n" +
            "      \"Hunger\": 0,\n" +
            "      \"Water\": 0,\n" +
            "      \"Happy\": 0,\n" +
            "      \"Health\": 0,\n" +
            "      \"Death\": 0\n" +
            "    }\n";
    private String mainJsonString;
    private String jsonString;
    private JSONObject tempJsonObj,jsonObject;
    private JSONArray jsonArray;
    private Bundle extras;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        catImg = (ImageView) findViewById(R.id.catImg);
        dogImg = (ImageView) findViewById(R.id.dogImg);
        intent = new Intent(getApplicationContext(), playActivity.class);
        petNameTextEdit = (EditText) findViewById(R.id.texitTextPetsName);
        extras = getIntent().getExtras();
        count = Integer.valueOf(extras.getInt("pos"));
        pref = getSharedPreferences("tama", Context.MODE_PRIVATE);
        try {
            tempJsonObj = new JSONObject(tempString);
            mainJsonString = extras.getString("mainJson");
            jsonObject = new JSONObject(extras.getString("mainJson"));
            jsonArray = jsonObject.getJSONArray("pets");
            Log.d("newGameLog" ,"New Game Activity "+String.valueOf(mainJsonString));
        } catch (JSONException e) {
           Log.d("newGameLog" ,"line 67 "+e.toString());
        }

        catImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    pref = getSharedPreferences("tama", Context.MODE_PRIVATE);
                    prefEdtior = pref.edit();
                    tempJsonObj.put("Name",petNameTextEdit.getText());
                    tempJsonObj.put("Type","Cat");
                    tempJsonObj.put("Date",new TimeHandler().getCurrentDate());
                    tempJsonObj.put("Time",new TimeHandler().getCurrentTime());
                    if(jsonArray.isNull(0)){
                        Log.d("newGameLog" , "if");
                        jsonObject.getJSONArray("pets").put(tempJsonObj);
                    }else{
                        Log.d("newGameLog" , "else");
                        jsonObject.getJSONArray("pets").put(tempJsonObj);
                    }

                    jsonString = jsonObject.toString();
                    intent.putExtra("mainJSON",jsonString);
                    intent.putExtra("pos",0);
                    prefEdtior.putString("JSON",jsonObject.toString());
                    prefEdtior.putBoolean("firstrun",false);
                    prefEdtior.apply();
                    Log.d("newGameLog","New Game Activity "+jsonObject.toString());
                    startActivity(intent);
                } catch (JSONException e) {
                    Log.d("newGameLog" ,"line 82 "+e.toString());
                }
            }
        });
        dogImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    tempJsonObj.put("Name",petNameTextEdit.getText());
                    tempJsonObj.put("Type","Dog");
                    tempJsonObj.put("Date",new TimeHandler().getCurrentDate());
                    jsonArray.put(jsonArray.length()+1,tempJsonObj);
                    jsonString = jsonArray.toString();
                    intent.putExtra("petsArray",jsonString);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}