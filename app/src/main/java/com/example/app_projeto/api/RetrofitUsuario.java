package com.example.app_projeto.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.0.2/Crud/";
    private static RetrofitClient myClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized  RetrofitClient getInstance() {
        if(myClient == null) {
            myClient = new RetrofitClient();
        }
        return myClient;
    }

    public BancoService getApi(){
        return retrofit.create(BancoService.class);
    }
}
