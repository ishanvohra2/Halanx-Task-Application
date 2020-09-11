package com.ishanvohra.halanxtask.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ishanvohra.halanxtask.Model.LoginBody;
import com.ishanvohra.halanxtask.Model.LoginResponse;
import com.ishanvohra.halanxtask.Network.HalanxAPI;
import com.ishanvohra.halanxtask.Network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository repository;
    private static String TAG = "Repository";

    public static Repository getInstance(){
        if(repository == null)
            repository = new Repository();

        return repository;
    }

    private HalanxAPI halanxAPI;

    public Repository(){
        halanxAPI = RetrofitService.cteateService(HalanxAPI.class);
    }

    public MutableLiveData<LoginResponse> login(String phone, String password){
        final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
        halanxAPI.login(new LoginBody(phone, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful())
                    loginResponse.setValue(response.body());

                Log.d(TAG, "Login Response: " + response.code() + " " + response.message());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "Login Failed " + t.getMessage());
                loginResponse.setValue(null);
            }
        });

        return loginResponse;
    }

}
