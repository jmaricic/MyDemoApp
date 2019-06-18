package com.example.mydemoapp.modules;


import com.example.mydemoapp.rest.RestClient;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module(includes = DataModule.class)
public class NetworkModule {
    public static String API_BASE_URL = "TBD";
    private static int CONNECT_TIMEOUT = 300000;
    private static int READ_TIMEOUT = 300000;
    private static int WRITE_TIMEOUT = 300000;

    @Provides
    @Singleton
    Retrofit provideRetrofit(ScalarsConverterFactory scalarsConverterFactory, GsonConverterFactory gsonConverterFactory, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(scalarsConverterFactory)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (!httpClient.interceptors().contains(httpLoggingInterceptor)) {
            httpClient.addInterceptor(httpLoggingInterceptor);
        }

        return httpClient.build();
    }

    @Provides
    ScalarsConverterFactory provideScalarsConverter() {
        return ScalarsConverterFactory.create();
    }

    @Provides
    GsonConverterFactory provideGsonConverter() {
        return GsonConverterFactory.create();
    }


    @Provides
    RestClient provideService(Retrofit retrofit) {
        return retrofit.create(RestClient.class);
    }

}
