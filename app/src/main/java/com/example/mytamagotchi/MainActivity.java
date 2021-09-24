package com.example.mytamagotchi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnNew;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ArrayAdapter<String> adapter;
    private JSONObject settings;
    private JSONArray pets;
    private String[] petsArray;
    String tempJson = "{\n" +
            "  \"settings\": {\n" +
            "    \"count\": 1\n" +
            "  },\n" +
            "  \"pets\": [\n" +
            "    {\n" +
            "      \"Type\": \"Dog\",\n" +
            "      \"Name\": \"Doggie\",\n" +
            "      \"Age\": 10\n" +
            "    },\n" +
            "     {\n" +
            "      \"Type\": \"Cat\",\n" +
            "      \"Name\": \"Catie\",\n" +
            "      \"Age\": 9\n" +
            "    }\n" +
            "    \n" +
            "  ]\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        btnNew = (Button) findViewById(R.id.btnNew);
        try {
            settings = new JSONObject(tempJson).getJSONObject("settings");
            pets = new JSONObject(tempJson).getJSONArray("pets");
            petsArray = new String[pets.length()];
            for(int i=0 ; i < pets.length(); i++){
                petsArray[i] = pets.getJSONObject(i).getString("Name");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            String strJson = pref.getString("JSON","0");
            if (strJson != null) {
                try {
                    JSONObject response = new JSONObject(strJson);
                    String[] mStrings = new String[10];
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1, petsArray);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.d("dor","null");
                }
            }else{
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, petsArray);
                listView.setAdapter(adapter);
            }
        }catch (NullPointerException e){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, petsArray);
            listView.setAdapter(adapter);
            Log.d("dor","NullPointerException");
        }




    }
    private void onClick(View v){
        switch (v.getId()){
            case R.id.btnNew:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}