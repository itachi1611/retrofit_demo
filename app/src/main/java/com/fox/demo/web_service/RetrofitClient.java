package com.fox.demo.web_service;

import com.fox.demo.utlis.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends Constants {

    public static Retrofit retrofit;

    public static Retrofit initRetrofitClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
