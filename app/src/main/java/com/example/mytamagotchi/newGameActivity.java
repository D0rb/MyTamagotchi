package com.example.mytamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class newGameActivity extends AppCompatActivity {
    private Intent intent;
    private ImageView catImg , dogImg;
    private EditText petNameTextEdit;
    private String tempString = "    {\n" +
            "      \"Type\": \"\",\n" +
            "      \"Name\": \"\",\n" +
            "      \"Age\": 0,\n" +
            "      \"Hunger\": 0,\n" +
            "      \"Water\": 0,\n" +
            "      \"Happy\": 0,\n" +
            "      \"Health\": 0,\n" +
            "      \"Death\": 0\n" +
            "    }\n" +
            "  ";
    private String jsonString;
    private JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        catImg = (ImageView) findViewById(R.id.catImg);
        dogImg = (ImageView) findViewById(R.id.dogImg);
        intent = new Intent(getApplicationContext(), playActivity.class);
        petNameTextEdit = (EditText) findViewById(R.id.texitTextPetsName);
        try {
            jsonObject = new JSONObject(tempString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jsonObject.put("Name",petNameTextEdit.getText());
                    jsonObject.put("Type","Cat");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonString = jsonObject.toString();
                intent.putExtra("petsArray",jsonString);
                startActivity(intent);
            }
        });
        dogImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jsonObject.put("Name",petNameTextEdit.getText());
                    jsonObject.put("Type","Cat");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonString = jsonObject.toString();
                intent.putExtra("petsArray",jsonString);
                startActivity(intent);
            }
        });
    }

}