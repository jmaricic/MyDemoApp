package com.example.mydemoapp;

import com.example.mydemoapp.login.LoginActivity;
import com.example.mydemoapp.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(LoginActivity activity);
}
