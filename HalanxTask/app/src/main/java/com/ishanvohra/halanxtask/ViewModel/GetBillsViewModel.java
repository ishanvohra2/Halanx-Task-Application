package com.ishanvohra.halanxtask.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ishanvohra.halanxtask.Model.GetBillResponse;
import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Network.HalanxAPI;
import com.ishanvohra.halanxtask.Repository.Repository;

public class GetBillsViewModel extends ViewModel {

    private MutableLiveData<GetBillsResponse> mutableLiveData;
    private Repository repository;

    public void init(String username, String password){
        if(mutableLiveData != null)
            return;

        repository = Repository.getInstance();
        repository = new Repository(username, password);
    }

    public LiveData<GetBillsResponse> getBills(){
        return repository.getBills();
    }

    public LiveData<GetBillResponse> getBill(int id){
        return repository.getBill(id);
    }

}
