package com.ishanvohra.halanxtask.Network;

import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.LoginBody;
import com.ishanvohra.halanxtask.Model.LoginResponse;

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

}
