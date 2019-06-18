package com.example.mydemoapp.login;

import android.arch.lifecycle.MutableLiveData;

import com.example.mydemoapp.model.LoginRequest;
import com.example.mydemoapp.model.LoginResponse;
import com.example.mydemoapp.repositories.RestApiRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

public class LoginViewModelTest {

    @Mock
    private RestApiRepository restApiRepository;

    private LoginViewModel loginViewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginViewModel = new LoginViewModel(restApiRepository);
    }


    @Test
    public void authenticate_success() {
        MutableLiveData<LoginResponse> mutableLiveData = Mockito.mock(MutableLiveData.class);
        String token = "token";
        LoginRequest loginRequest = new LoginRequest();
        Mockito.when(restApiRepository.authenticate(token, loginRequest)).thenReturn(Observable.just(new LoginResponse()));
        LoginResponse loginResponse = new LoginResponse();
        loginViewModel.auth(token, loginRequest);

        Mockito.verify(mutableLiveData).setValue(loginResponse);

    }

}