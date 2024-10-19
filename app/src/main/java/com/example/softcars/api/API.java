package com.example.softcars.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static Retrofit retrofit;

    public static Retrofit getUrl(){
        if(retrofit.equals(null)){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/WSSoftCars/services/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}