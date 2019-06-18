package com.example.mydemoapp.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mydemoapp.model.LoginRequest;
import com.example.mydemoapp.model.LoginResponse;
import com.example.mydemoapp.repositories.RestApiRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<LoginResponse> responseLiveData = new MutableLiveData<>();
    private RestApiRepository restApiRepository;

    public LoginViewModel(RestApiRepository restApiRepository) {
        this.restApiRepository = restApiRepository;
    }

    public MutableLiveData<LoginResponse> getResponseLiveData() {
        return responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }

    public void auth(String token, LoginRequest request) {
        disposable.add(restApiRepository.authenticate(token, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<LoginResponse>() {
                                   @Override
                                   public void onNext(LoginResponse authResponseEntity) {
                                       responseLiveData.setValue(authResponseEntity);
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       LoginResponse response = new LoginResponse();
                                       response.setErrorMessage(e.getMessage());
                                       responseLiveData.setValue(response);
                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               }
                ));

    }
}