package com.example.mytamagotchi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    Intent intent;
    String tempJson = "{\n" +
            "  \"settings\": {\n" +
            "    \"count\": 1\n" +
            "  },\n" +
            "  \"pets\": [\n" +
            "    {\n" +
            "      \"Type\": \"Dog\",\n" +
            "      \"Name\": \"Doggie\",\n" +
            "      \"Age\": 10,\n" +
            "      \"Hunger\": 10,\n" +
            "      \"Water\": 10,\n" +
            "      \"Happy\": 10,\n" +
            "      \"Health\": 10,\n" +
            "      \"Death\": 10\n" +
            "      \n" +
            "    },\n" +
            "    {\n" +
            "      \"Type\": \"Cat\",\n" +
            "      \"Name\": \"Cattie\",\n" +
            "      \"Age\": 10,\n" +
            "      \"Hunger\": 10,\n" +
            "      \"Water\": 10,\n" +
            "      \"Happy\": 10,\n" +
            "      \"Health\": 10,\n" +
            "      \"Death\": 10\n" +
            "    }\n" +
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



    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            intent = new Intent(getApplicationContext(), playActivity.class);
            intent.putExtra("game","1"); // not a new game
            try {
                intent.putExtra("petsArray", pets.getJSONObject(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(intent);

        }
    });
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnNew:
                intent = new Intent(this, newGameActivity.class);
                startActivity(intent);
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