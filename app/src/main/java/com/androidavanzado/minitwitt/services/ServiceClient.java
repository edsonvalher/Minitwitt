package com.androidavanzado.minitwitt.services;

import com.androidavanzado.minitwitt.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static ServiceClient instance;

    private AuthService authService;
    private Retrofit retrofit;

    public ServiceClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authService = retrofit.create(AuthService.class);
    }
    //patron singleton
    public static ServiceClient getInstance(){
        if(instance == null){
            instance = new ServiceClient();
        }
        return instance;
    }
    public AuthService getAuthService(){
        return authService;
    }
}
