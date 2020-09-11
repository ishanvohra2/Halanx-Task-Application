package com.ishanvohra.halanxtask.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.LoginBody;
import com.ishanvohra.halanxtask.Model.LoginResponse;
import com.ishanvohra.halanxtask.Network.HalanxAPI;
import com.ishanvohra.halanxtask.Network.RetrofitService;

import okhttp3.ResponseBody;
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
        halanxAPI = RetrofitService.createService(HalanxAPI.class);
    }

    public Repository(String username, String password){
        halanxAPI = RetrofitService.createService(HalanxAPI.class, username, password);
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

    public  MutableLiveData<GetBillsResponse> getBills(){
        MutableLiveData<GetBillsResponse> billResponse = new MutableLiveData<>();

        halanxAPI.getBills().enqueue(new Callback<GetBillsResponse>() {
            @Override
            public void onResponse(Call<GetBillsResponse> call, Response<GetBillsResponse> response) {
                if(response.isSuccessful())
                    billResponse.setValue(response.body());

                Log.d(TAG, "Get Bills Response: " + response.code() + " " + response.message());
            }

            @Override
            public void onFailure(Call<GetBillsResponse> call, Throwable t) {
                Log.d(TAG, "Get Bills Failed " + t.getMessage());
                billResponse.setValue(null);
            }
        });

        return billResponse;
    }

    public MutableLiveData<ResponseBody> deleteBill(int id){
        MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();
        halanxAPI.deleteBill(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Bill delete successfully");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
