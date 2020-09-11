package com.ishanvohra.halanxtask.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Network.HalanxAPI;
import com.ishanvohra.halanxtask.Repository.Repository;

public class GetBillsViewModel extends ViewModel {

    private MutableLiveData<GetBillsResponse> mutableLiveData;
    private Repository repository;

    public void init(){
        if(mutableLiveData != null)
            return;

        repository = Repository.getInstance();
    }

    public LiveData<GetBillsResponse> getBills(String username, String password){
        repository = new Repository(username, password);
        mutableLiveData = repository.getBills();
        return mutableLiveData;
    }

}
