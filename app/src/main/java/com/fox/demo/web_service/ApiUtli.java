package com.fox.demo.web_service;

public class ApiUtli {

    public static UserService getUserService() {
        return RetrofitClient.initRetrofitClient().create(UserService.class);
    }

}
