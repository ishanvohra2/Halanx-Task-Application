package com.ishanvohra.halanxtask.Network;

import com.ishanvohra.halanxtask.Model.Category;
import com.ishanvohra.halanxtask.Model.CreateBillBody;
import com.ishanvohra.halanxtask.Model.CreateBillResponse;
import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.GetHouseResponse;
import com.ishanvohra.halanxtask.Model.GetTenantResponse;
import com.ishanvohra.halanxtask.Model.LoginBody;
import com.ishanvohra.halanxtask.Model.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HalanxAPI {

    @POST("/rest-auth/login/")
    Call<LoginResponse> login(@Body LoginBody body);

    @GET("/homes/owners/bills/")
    Call<GetBillsResponse> getBills();

    @DELETE("/homes/owners/bills/{id}/")
    Call<ResponseBody> deleteBill(@Path("id") int id);

    @GET("/homes/houses/bills/categories/")
    Call<List<Category>> getCategories();

    @GET("/homes/owners/houses/")
    Call<GetHouseResponse> getHouses();

    @GET("/homes/tenants/?moved-out=False&invitation-status=accepted")
    Call<List<GetTenantResponse>> getTenants();

    @POST("/homes/owners/bills/")
    Call<CreateBillResponse> createBill(@Body CreateBillBody billBody);

}
