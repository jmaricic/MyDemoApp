package com.example.mydemoapp.rest;

import com.example.mydemoapp.model.LoginRequest;
import com.example.mydemoapp.model.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestClient {

    @POST("login")
    Observable<LoginResponse> postAuth(@Header("Authorization") String authKey, @Body LoginRequest requestEntity);

}
