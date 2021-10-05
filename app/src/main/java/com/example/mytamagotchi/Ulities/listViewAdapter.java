package com.example.mytamagotchi.Ulities;

import android.app.Activity;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.mytamagotchi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

public class listViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final JSONArray jsonObject;
    private TimeHandler timeHandler;
    public listViewAdapter(Activity context, JSONArray jsonObject,String[] maintitle) {
        super(context, R.layout.listview, maintitle);
        this.context = context;
        this.jsonObject = jsonObject;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View view, ViewGroup parent) {
        String age;
        timeHandler = new TimeHandler();
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null,true);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        imageView.setImageResource(R.drawable.cat);
        try {

            titleText.setText("Name: " + jsonObject.getJSONObject(position).getString("Name"));
            imageView.setImageResource(R.drawable.cat);
            switch (jsonObject.getJSONObject(position).getString("Type")) {
                case "Dog":
                    imageView.setImageResource(R.drawable.dog);
                    break;
                case "Cat":
                    imageView.setImageResource(R.drawable.cat);
                    break;
            }
           // Log.d("Dork",jsonObject.getJSONObject(position).getString("Age"));
            age = String.valueOf(timeHandler.calcDate(jsonObject.getJSONObject(position).getString("Date")));
            //Log.d("Dork", String.valueOf("Date calc" +timeHandler.calcDate("2021/10/10")));
            jsonObject.getJSONObject(position).put("Age",age);
            subtitleText.setText("Age: " +age);
            return rowView;
        } catch (JSONException e) {
            Log.d("Dor",e.toString());
            e.printStackTrace();
        }
        return rowView;
    }
}