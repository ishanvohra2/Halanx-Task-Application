package com.ishanvohra.halanxtask.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ishanvohra.halanxtask.Model.Category;
import com.ishanvohra.halanxtask.Model.CreateBillBody;
import com.ishanvohra.halanxtask.Model.CreateBillResponse;
import com.ishanvohra.halanxtask.Model.GetHouseResponse;
import com.ishanvohra.halanxtask.Model.GetTenantResponse;
import com.ishanvohra.halanxtask.Repository.Repository;

import java.util.List;

public class CreateBillViewModel extends ViewModel {

    private Repository repository;

    public void init(String username, String password){
        repository = Repository.getInstance();
        repository = new Repository(username, password);
    }

    public LiveData<List<Category>> getCategories(){
        return repository.getCategories();
    }

    public LiveData<GetHouseResponse> getHouses(){
        return repository.getHouses();
    }

    public LiveData<List<GetTenantResponse>> getTenants(){
        return repository.getTenants();
    }

    public LiveData<CreateBillResponse> createBill(CreateBillBody billBody){
        return repository.createBill(billBody);
    }
}
