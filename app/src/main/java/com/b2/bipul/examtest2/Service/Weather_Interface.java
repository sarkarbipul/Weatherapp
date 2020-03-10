package com.b2.bipul.examtest2.Service;

import com.b2.bipul.examtest2.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Weather_Interface {
    @GET("current?access_key=MY_API_KEY")
    Call<WeatherData> listRepos(@Query("query") String key);
}
