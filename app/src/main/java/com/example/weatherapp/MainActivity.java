package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    JSONObject jsonObject;
    EditText zipcode;
    Button find;
    String apikey = "10c1754c53dfba02d8c77202822d47ed";
    TextView longitude;
    TextView latitude;
    TextView city;

    TextView time1,time2, time3, time4;
    TextView weather1, weather2, weather3, weather4;
    TextView des1, des2, des3, des4;
    ImageView image1, image2, image3, image4;

    TextView current, currentTemp;
    ImageView currentImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipcode = findViewById(R.id.zipcode);
        find = findViewById(R.id.find);
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        city = findViewById(R.id.city);

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);

        weather1 = findViewById(R.id.weather1);
        weather2 = findViewById(R.id.weather2);
        weather3 = findViewById(R.id.weather3);
        weather4 = findViewById(R.id.weather4);

        des1 = findViewById(R.id.des1);
        des2 = findViewById(R.id.des2);
        des3 = findViewById(R.id.des3);
        des4 = findViewById(R.id.des4);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image5);

        current = findViewById(R.id.current);
        currentTemp = findViewById(R.id.currentTemp);
        currentImage = findViewById(R.id.currentImage);


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncThread1 thread = new AsyncThread1();
                thread.execute();

                weather1.setVisibility(View.VISIBLE);
                weather2.setVisibility(View.VISIBLE);
                weather3.setVisibility(View.VISIBLE);
                weather4.setVisibility(View.VISIBLE);

                time1.setVisibility(View.VISIBLE);
                time2.setVisibility(View.VISIBLE);
                time3.setVisibility(View.VISIBLE);
                time4.setVisibility(View.VISIBLE);

                des1.setVisibility(View.VISIBLE);
                des2.setVisibility(View.VISIBLE);
                des3.setVisibility(View.VISIBLE);
                des4.setVisibility(View.VISIBLE);

                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);
                image4.setVisibility(View.VISIBLE);

                currentImage.setVisibility(View.VISIBLE);

            }
        });
    }


    //ASYNC THREAD
    public class AsyncThread1 extends AsyncTask<String, String, String> {

        //Method to get DATA
        public String runUrl(String s) {
            String data = "";

            try {
                URL url;
                url = new URL(s);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream;
                BufferedReader bufferedReader;
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));

                String line = "";
                while(line != null) {
                    line = bufferedReader.readLine();

                    if ( line != null ) {
                        data = data + line;
                        Log.d("tag11", line);
                    }
                }

            }catch (MalformedURLException e) {
                data = "";
                e.printStackTrace();
            }
            catch (IOException e) {
                data = "";
                e.printStackTrace();
            }
            return data;
        }

        //EPOCH to Date method
        public String convertDate(String s) {
            long num = Integer.parseInt(s);
            String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (num * 1000));
            String[] temp = date.split(" ");
            String time = temp[1];
            temp = time.split(":");
            String hour = temp[0];
            String rest1 = temp[1];

            String addon = " am";

            int hr = Integer.parseInt(hour);
            if(hr > 12) {
                hr -=12;
                addon = " pm";
            }
            if(hr == 0) {
                hr = 12;
            }
            time = hr + ":" + rest1 + addon;
            return time;
        }

        //Kelvin to F
        public String convertTemp(String s) {
            double k = Double.parseDouble(s);
            double temperature = (((k - 273.15) * 9)/5) +32;

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);

            String rt1 = df.format( temperature );

            String sTemp = "" + rt1;
            return sTemp;
        }

        //What Image to print
        public void setImage(ImageView i, String id) {
            switch (id) {
                case"01d" :
                    i.setImageResource(R.drawable.oned);
                    break;
                case"01n" :
                    i.setImageResource(R.drawable.onen);
                    break;
                case"02d" :
                    i.setImageResource(R.drawable.twod);
                    break;
                case"02n" :
                    i.setImageResource(R.drawable.twon);
                    break;
                case"03d" :
                    i.setImageResource(R.drawable.threedn);
                    break;
                case"03n" :
                    i.setImageResource(R.drawable.threedn);
                    break;
                case"04d" :
                    i.setImageResource(R.drawable.fourdn);
                    break;
                case"04n" :
                    i.setImageResource(R.drawable.fourdn);
                    break;
                case"09d" :
                    i.setImageResource(R.drawable.ninedn);
                    break;
                case"09n" :
                    i.setImageResource(R.drawable.ninedn);
                    break;
                case"10d" :
                    i.setImageResource(R.drawable.tend);
                    break;
                case"10n" :
                    i.setImageResource(R.drawable.tenn);
                    break;
                case"11d" :
                    i.setImageResource(R.drawable.elevendn);
                    break;
                case"11n" :
                    i.setImageResource(R.drawable.elevendn);
                    break;
                case"13d" :
                    i.setImageResource(R.drawable.thirteendn);
                    break;
                case"13n" :
                    i.setImageResource(R.drawable.thirteendn);
                    break;
                case"50d" :
                    i.setImageResource(R.drawable.fiftydn);
                    break;
                case"50n" :
                    i.setImageResource(R.drawable.fiftydn);
                    break;
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = "";
            String baseUrl = "https://api.openweathermap.org/geo/1.0";
            String res1 = runUrl( baseUrl  + "/zip?zip=" + zipcode.getText().toString() + ",us" + "&appid=" + apikey);

            if ( res1 != null && res1 != "" ) {
                String lat = "";
                String lon = "";
                try {
                    JSONObject json = new JSONObject(res1);
                    lat = json.getString("lat");
                    lon = json.getString("lon");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                baseUrl = "https://api.openweathermap.org/data/2.5/onecall";
                String res2 = runUrl(baseUrl + "?lat=" + lat + "&lon=" + lon + "&exclude=alerts,minutely,current,daily&appid=" + apikey);

                try {
                    JSONObject json = new JSONObject(res1);
                    Log.d("JSON Length: ", String.valueOf(json.length()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                data = res1 + "#" + res2;
                Log.d("DATA", data);
            }


            return data;
        }

        protected void onPostExecute(String s) {

            if (s == null || s == "" )
                return;

            String[] parts = s.split("#");
            String loc = parts[0];
            String weather = parts[1];

            Log.d("LOC", loc);
            Log.d("WEATHER", weather);


            JSONObject jsonLoc;
            JSONObject jsonWeather;
            try {
                jsonLoc = new JSONObject(loc);
                String lat = jsonLoc.getString("lat");
                String lon = jsonLoc.getString("lon");
                String name = jsonLoc.getString("name");
                latitude.setText("Lat: " +lat);
                longitude.setText("Log: " +lon);
                city.setText("City: " + name);

                jsonWeather = new JSONObject(weather);

                JSONArray hourly = jsonWeather.getJSONArray("hourly");

                String [] time = new String[5];
                String [] temperature = new String[5];
                for(int i = 0; i < 5; i++) {
                    JSONObject jsonObj = hourly.getJSONObject(i);

                    String epochTime = jsonObj.getString("dt");
                    time[i] = convertDate(epochTime);

                    String K = jsonObj.getString("temp");
                    temperature[i] = convertTemp(K);
                }
                time1.setText(time[1]);
                time2.setText(time[2]);
                time3.setText(time[3]);
                time4.setText(time[4]);

                weather1.setText(temperature[1] + "°");
                currentTemp.setText(temperature[0] + "°");
                weather2.setText(temperature[2] + "°");
                weather3.setText(temperature[3] + "°");
                weather4.setText(temperature[4] + "°");




                String [] description = new String[5];
                String [] id = new String [5];

                for(int i = 0; i < 5; i++) {
                    JSONObject jsonObj = hourly.getJSONObject(i);
                    JSONArray JSONWeather = jsonObj.getJSONArray("weather");
                    JSONObject jsonMain = JSONWeather.getJSONObject(0);

                    description[i] = jsonMain.getString("description");
                    id[i] = jsonMain.getString("icon");


                }
                des1.setText(description[1]);
                des2.setText(description[2]);
                des3.setText(description[3]);
                des4.setText(description[4]);

                setImage(image1, id[1]);
                setImage(currentImage, id[0]);
                setImage(image2, id[2]);
                setImage(image3, id[3]);
                setImage(image4, id[4]);




                } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}