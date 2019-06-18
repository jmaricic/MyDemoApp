package com.example.mydemoapp.repositories;

import com.example.mydemoapp.model.LoginRequest;
import com.example.mydemoapp.model.LoginResponse;
import com.example.mydemoapp.rest.RestClient;

import io.reactivex.Observable;

public class RestApiRepository {

    private RestClient restClient;

    public RestApiRepository(RestClient srsRestClient) {
        this.restClient = srsRestClient;
    }


    public Observable<LoginResponse> authenticate(String header, LoginRequest requestEntity) {
        return restClient.postAuth(header, requestEntity);
    }

}
