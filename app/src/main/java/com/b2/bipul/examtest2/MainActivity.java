package com.b2.bipul.examtest2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.b2.bipul.examtest2.Service.Weather_Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private LinearLayout linearLayout;
    private TextView tempareture, wind_speed, wind_degree, wind_dir, pressure, precip, humidity, cloudcover, feelslike, uv_index, visibility, is_day;

    private String[] country = {
            "Dhaka", "KABUL", "CANBERRA", "NEW DELHI", "ISLAMABAD", "WASHINGTON, D.C.", "LONDON"

    };

    Weather_Interface service;
    Call<WeatherData> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner_id);
        linearLayout = findViewById(R.id.linearLayout_id);
        tempareture = findViewById(R.id.temperature_view_id);
        wind_speed = findViewById(R.id.wind_speed_view_id);
        wind_degree = findViewById(R.id.wind_degree_view_id);
        wind_dir = findViewById(R.id.wind_dir_view_id);
        pressure = findViewById(R.id.pressure_view_id);
        precip = findViewById(R.id.precip_view_id);
        humidity = findViewById(R.id.humidity_view_id);
        cloudcover = findViewById(R.id.cloudcover_view_id);
        feelslike = findViewById(R.id.feelslike_view_id);
        uv_index = findViewById(R.id.uv_index_view_id);
        visibility = findViewById(R.id.visibility_view_id);
        is_day = findViewById(R.id.is_day_view_id);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, country);

        spinner.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Weather_Interface.class);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = country[position];
                repos = service.listRepos(key);
                repos.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        tempareture.setText(response.body().getCurrent().getTemperature()+"");
                        wind_speed.setText(response.body().getCurrent().getWind_speed()+"");
                        wind_degree.setText(response.body().getCurrent().getWind_degree()+"");
                        wind_dir.setText(response.body().getCurrent().getWind_dir());
                        pressure.setText(response.body().getCurrent().getPressure()+"");
                        precip.setText(response.body().getCurrent().getPrecip()+"");
                        humidity.setText(response.body().getCurrent().getHumidity()+"");
                        cloudcover.setText(response.body().getCurrent().getCloudcover()+"");
                        feelslike.setText(response.body().getCurrent().getFeelslike()+"");
                        uv_index.setText(response.body().getCurrent().getUv_index()+"");
                        visibility.setText(response.body().getCurrent().getVisibility()+"");
                        is_day.setText(response.body().getCurrent().getIs_day());
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

