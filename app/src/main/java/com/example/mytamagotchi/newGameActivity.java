package com.example.mytamagotchi;

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
import com.google.android.material.textfield.TextInputEditText;

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
    private String JSONs;
    private String jsonString;
    private JSONObject jsonObject,tempJsonObj;
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

        try {
            jsonObject = new JSONObject(tempString);
            JSONs = extras.getString("petsArray");
            jsonArray = new JSONArray(JSONs);

        } catch (JSONException e) {
           Log.d("newGameLog" ,"line 67 "+e.toString());
        }

        catImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {

                    jsonObject.put("Name",petNameTextEdit.getText());
                    jsonObject.put("Type","Cat");
                    jsonObject.put("Date",new TimeHandler().getCurrentDate());

                    jsonArray.put(jsonArray.length()+1,jsonObject);
                    jsonString = jsonArray.toString();
                    intent.putExtra("petsArray",jsonString);
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
                    jsonObject.put("Name",petNameTextEdit.getText());
                    jsonObject.put("Type","Dog");
                    jsonObject.put("Date",new TimeHandler().getCurrentDate());
                    jsonArray.put(jsonArray.length()+1,jsonObject);
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