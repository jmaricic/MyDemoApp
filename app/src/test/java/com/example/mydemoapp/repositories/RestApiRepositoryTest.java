package com.example.mydemoapp.repositories;

import com.example.mydemoapp.model.LoginRequest;
import com.example.mydemoapp.model.LoginResponse;
import com.example.mydemoapp.rest.RestClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

public class RestApiRepositoryTest {

    private RestApiRepository restApiRepository;

    @Mock
    private RestClient restClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        restApiRepository = new RestApiRepository(restClient);
    }

    @Test
    public void test_success() {
        String token = "token";
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse loginResponse = new LoginResponse();
        Mockito.when(restClient.postAuth(token, loginRequest)).thenReturn(Observable.just(loginResponse));

        restApiRepository.authenticate(token, loginRequest)
                .test()
                .assertValue(loginResponse);

    }
}