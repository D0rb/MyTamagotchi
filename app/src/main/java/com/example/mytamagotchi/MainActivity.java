package com.example.mytamagotchi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.mytamagotchi.Ulities.TimeHandler;
import com.example.mytamagotchi.Ulities.listViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnNew;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ArrayAdapter<String> adapter;
    private JSONObject settings;
    private JSONArray pets;
    private String[] petsArray;
    private TimeHandler timeHandler = new TimeHandler();
    Integer[] imgid= {
            R.drawable.cat, R.drawable.dog,
    };
            Intent intent;
    String tempJson = "{\n" +
            "  \"settings\": {\n" +
            "    \"count\": 1\n" +
            "  },\n" +
            "  \"pets\": [\n" +
            "    {\n" +
            "      \"Type\": \"Dog\",\n" +
            "      \"Name\": \"Doggie\",\n" +
            "      \"Date\": \"2021/10/03\",\n" +
            "      \"Time\": \"16:31:38\",\n" +
            "      \"lastTime\": \"\",\n" +
            "      \"lastDate\": \"\",\n" +
            "      \"Age\": 10,\n" +
            "      \"Hunger\": 10,\n" +
            "      \"Water\": 10,\n" +
            "      \"Happy\": 10,\n" +
            "      \"Health\": 10,\n" +
            "      \"Death\": 10\n" +
            "    },\n" +
            "    {\n" +
            "      \"Type\": \"Cat\",\n" +
            "      \"Name\": \"Cattie\",\n" +
            "      \"Date\": \"2021/10/03\",\n" +
            "      \"Time\": \"16:31:38\",\n" +
            "      \"lastTime\": \"\",\n" +
            "      \"lastDate\": \"\",\n" +
            "      \"Age\": 10,\n" +
            "      \"Hunger\": 10,\n" +
            "      \"Water\": 10,\n" +
            "      \"Happy\": 10,\n" +
            "      \"Health\": 10,\n" +
            "      \"Death\": 10\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    String[] maintitle ={
            "Title 1","Title 2",
            "Title 3","Title 4",
            "Title 5",
    };


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        btnNew = (Button) findViewById(R.id.btnNew);
        Log.d("test",timeHandler.getCurrentDate());
        Log.d("test",timeHandler.getCurrentTime());
        /*
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String date = timeHandler.getCurrentTime();
        String date2 = "20:03:59";
        Log.d("test", "asdasdasd");
        Date expiredDate = stringToDate(date, "HH:mm:ss");
        Date expiredDate1 = stringToDate(date2, "HH:mm:ss");
        date = formatter.format(expiredDate.getTime());
        date2 = formatter.format(expiredDate1.getTime());
        Log.d("test",(timeHandler.calc2(expiredDate,expiredDate1)));
        Log.d("test", "asdasdasd");
        */
        try {

            settings = new JSONObject(tempJson).getJSONObject("settings");
            pets = new JSONObject(tempJson).getJSONArray("pets");
            petsArray = new String[pets.length()];
            for (int i = 0; i < pets.length(); i++) {
                petsArray[i] = pets.getJSONObject(i).getString("Name");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            String strJson = pref.getString("JSON", "0");
            if (strJson != null) {
                try {
                    JSONObject response = new JSONObject(strJson);
                    String[] mStrings = new String[10];
                    listViewAdapter adapter = new listViewAdapter(this, pets,maintitle);
                    listView = (ListView) findViewById(R.id.listView);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.d("dor", "null");
                }
            } else {
                listViewAdapter adapter = new listViewAdapter(this, pets,maintitle);
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        } catch (NullPointerException e) {
            listViewAdapter adapter = new listViewAdapter(this, pets,maintitle);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            Log.d("dor", "NullPointerException");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                intent = new Intent(getApplicationContext(), playActivity.class);
                intent.putExtra("game", "1"); // not a new game
                try {
                    intent.putExtra("petsArray", pets.getJSONObject(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);

            }
        });

    }
    public void onClick(View v) {
        switch (v.getId()) {
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
    private Date stringToDate(String aDate,String aFormat) {
        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}