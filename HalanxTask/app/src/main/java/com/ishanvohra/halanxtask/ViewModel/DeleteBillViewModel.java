package com.ishanvohra.halanxtask.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ishanvohra.halanxtask.Repository.Repository;

import okhttp3.ResponseBody;

public class DeleteBillViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<ResponseBody> mutableLiveData;

    public void init(){
        if(mutableLiveData != null)
            return;

        repository = Repository.getInstance();
    }

    public LiveData<ResponseBody> deleteBill(String username, String password, int id){
        repository = new Repository(username, password);
        mutableLiveData = repository.deleteBill(id);
        return mutableLiveData;
    }

}
