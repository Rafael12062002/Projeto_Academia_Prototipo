package com.example.app_projeto.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitUsuario {
    private static final String BASE_URL = "http://10.0.0.242/Crud/";
    private static RetrofitUsuario myUsuario;
    private Retrofit retrofit;

    private RetrofitUsuario() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitUsuario getInstance() {
        if(myUsuario == null) {
            myUsuario = new RetrofitUsuario();
        }
        return myUsuario;
    }

    public BancoService getApi(){
        return retrofit.create(BancoService.class);
    }
}
