package com.androidavanzado.minitwitt.services;

import com.androidavanzado.minitwitt.models.Request.RequestLogin;
import com.androidavanzado.minitwitt.models.Request.RequestSignUp;
import com.androidavanzado.minitwitt.models.Response.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("auth/signup")
    Call<ResponseAuth> doSignUp(@Body RequestSignUp requestSignUp);
}
