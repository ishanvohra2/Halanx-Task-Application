package com.ishanvohra.halanxtask.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ishanvohra.halanxtask.Model.LoginResponse;
import com.ishanvohra.halanxtask.Repository.Repository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponse> mutableLiveData;
    private Repository repository;

    public void init(){
        if(mutableLiveData != null)
            return;

        repository = Repository.getInstance();
    }

    public LiveData<LoginResponse> login(String username,String password){
        mutableLiveData = repository.login(username, password);
        return mutableLiveData;
    }

}
