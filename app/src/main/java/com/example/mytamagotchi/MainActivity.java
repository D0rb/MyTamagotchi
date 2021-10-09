package com.example.mytamagotchi;

import android.content.Context;
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
import java.util.Objects;

// TODO: 09/10/2021 צריך לתקן את הטעינה של המשתנים כגון אוכל וכו' ביציאה מהאפליקציה וחזרה  ולבדוק באג שקשור בכך שהמשתנים לא נטענים ממשחק חדש
// TODO: 09/10/2021 לסדר את המיניפולציה על הערכים
// TODO: 09/10/2021 להכניס את הגרפיקה ולעדכן את ה UI
// TODO: 09/10/2021 לסדר נראות במסך הראשי ובמסך של משחק חדש
// TODO: 09/10/2021 להסגר על ההורשה וכו' האם הולכים על זה או לא ואם כן אז איך
// TODO: 09/10/2021 לשנות במקצת את הנראות של המסך משחק  ( למחוק כפתורים לא רצויים , לשנות צבעים , להוריד את הפרפר? )
// TODO: 09/10/2021 לנסות להכניס סאונד למשחק
// TODO: 09/10/2021 לנקות את הקוד

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdtior;
    private JSONObject settings;
    private JSONArray pets;
    private String[] petsArray;
    private TimeHandler timeHandler = new TimeHandler();
    private String[] maintitle;
    private boolean firstRun = true;
    Integer[] imgid= {
            R.drawable.cat, R.drawable.dog,
    };
            Intent intent;
    String testJson = "{\n" +
            "  \"settings\": {\n" +
            "    \"count\": 3\n" +
            "  },\n" +
            "  \"pets\": [\n" +
            "    {\n" +
            "      \"count\": 0,\n" +
            "      \"Type\": \"Dog\",\n" +
            "      \"Name\": \"Doggie\",\n" +
            "      \"Date\": \"2021/10/03\",\n" +
            "      \"Time\": \"16:31:38\",\n" +
            "      \"lastTime\": \"16:31:38\",\n" +
            "      \"lastDate\": \"2021/10/13\",\n" +
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
            "      \"lastTime\": \"16:31:38\",\n" +
            "      \"lastDate\": \"2021/10/13\",\n" +
            "      \"Age\": 10,\n" +
            "      \"Hunger\": 10,\n" +
            "      \"Water\": 10,\n" +
            "      \"Happy\": 10,\n" +
            "      \"Health\": 10,\n" +
            "      \"Death\": 10,\n" +
            "      \"count\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"Type\": \"Cat\",\n" +
            "      \"Name\": \"Cattie\",\n" +
            "      \"Date\": \"2021/10/03\",\n" +
            "      \"Time\": \"16:31:38\",\n" +
            "      \"lastTime\": \"16:31:38\",\n" +
            "      \"lastDate\": \"2021/10/13\",\n" +
            "      \"Age\": 10,\n" +
            "      \"Hunger\": 10,\n" +
            "      \"Water\": 10,\n" +
            "      \"Happy\": 10,\n" +
            "      \"Health\": 10,\n" +
            "      \"Death\": 10,\n" +
            "      \"count\": 2\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String mainJSON = "{\n" +
            "  \"settings\": {\n" +
            "    \"count\": 0\n" +
            "  },\n" +
            "  \"pets\": []\n" +
            "}";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        Log.d("test",timeHandler.getCurrentDate());
        Log.d("test",timeHandler.getCurrentTime());
        Objects.requireNonNull(getSupportActionBar()).hide();
        pref = getSharedPreferences("tama", Context.MODE_PRIVATE);
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
        Log.d("JSONPRING",mainJSON);
        try {

//            prefEdtior = pref.edit();
//            prefEdtior.putBoolean("firstrun",true);
//            prefEdtior.apply();

            if(pref.getBoolean("firstrun",firstRun)){
                intent = new Intent(this, newGameActivity.class);
                intent.putExtra("mainJson",mainJSON);
                Log.d("JSONPRING",mainJSON);
                startActivity(intent);
                Log.d("newGameLog","Main Activity "+"true");
            }else{
                mainJSON = pref.getString("JSON", "0");
                Log.d("newGameLog","Main Activity "+mainJSON);
            }
            settings = new JSONObject(mainJSON).getJSONObject("settings");
            pets = new JSONObject(mainJSON).getJSONArray("pets");
            petsArray = new String[pets.length()];
            for (int i = 0; i < pets.length(); i++) {
                petsArray[i] = pets.getJSONObject(i).getString("Name");

            }

        } catch (JSONException e) {
            Log.d("newGameLog",e.toString());
        }
        try {


//            if (mainJSON.equals("0")) {
//                prefEdtior = pref.edit();
//                prefEdtior.putString("JSON",mainJSON);
//                prefEdtior.apply();
//                mainJSON = pref.getString("JSON", "0");
//            }

            try {
                maintitle = new String[petsArray.length];
                for(int i=0;i<petsArray.length;i++){
                    maintitle[i] = pets.getJSONObject(i).getString("Name");
                    Log.d("newGameLog",maintitle[i]);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            listViewAdapter adapter = new listViewAdapter(this, pets, maintitle);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            {

            }
        } catch (NullPointerException e) {
            Log.d("dor", "NullPointerException");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                intent = new Intent(getApplicationContext(), playActivity.class);
                intent.putExtra("game", "1"); // not a new game
                intent.putExtra("mainJson", mainJSON);
                intent.putExtra("pos", position);
                startActivity(intent);

            }
        });

    }
    public void onClick(View v) throws JSONException {
        switch (v.getId()) {
            case R.id.btnNew:
                intent = new Intent(this, newGameActivity.class);
                intent.putExtra("mainJson",mainJSON);
                intent.putExtra("pos", settings.getInt("count"));
                Log.d("JSONPRING",mainJSON);
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
    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}