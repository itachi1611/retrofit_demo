package com.fox.demo.web_service;

import com.fox.demo.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("users")
    Call<User> getAllUser(@Query("page") String page);
}
