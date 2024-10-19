package com.example.softcars.services;

import com.example.softcars.model.Usuarios;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    //Interface para dar de alta usuarios
    @FormUrlEncoded
    @POST("username/sign-in/")
    Call<ResponseBody> insertUser(@Field("userData") String userData);

    //Interface para login
    @FormUrlEncoded
    @POST("username/login/")
    Call<Usuarios> login(@Field("userData") String userData);
}