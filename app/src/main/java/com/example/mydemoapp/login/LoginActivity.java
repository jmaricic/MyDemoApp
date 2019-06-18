package com.example.mydemoapp.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydemoapp.MyApp;
import com.example.mydemoapp.R;
import com.example.mydemoapp.model.LoginRequest;
import com.example.mydemoapp.model.LoginResponse;
import com.example.mydemoapp.repositories.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;


    @BindView(R.id.testButton)
    Button testButton;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        viewModel.getResponseLiveData().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable LoginResponse loginResponse) {
                if (loginResponse != null) {
                    Toast.makeText(LoginActivity.this, loginResponse.getErrorMessage() != null ?
                            loginResponse.getErrorMessage() : "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.testButton)
    public void onTestButtonClick() {
        String token = "token";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        viewModel.auth(token, loginRequest);
    }
}
