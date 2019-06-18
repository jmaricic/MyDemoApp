package com.example.mydemoapp.modules;


import android.arch.lifecycle.ViewModelProvider;

import com.example.mydemoapp.repositories.RestApiRepository;
import com.example.mydemoapp.repositories.ViewModelFactory;
import com.example.mydemoapp.rest.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    RestApiRepository getRepository(RestClient restClient) {
        return new RestApiRepository(restClient);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(RestApiRepository myRepository) {
        return new ViewModelFactory(myRepository);
    }


}
