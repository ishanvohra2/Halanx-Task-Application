package com.ishanvohra.halanxtask.Network;

import com.ishanvohra.halanxtask.Model.LoginBody;
import com.ishanvohra.halanxtask.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HalanxAPI {

    @POST("/rest-auth/login/")
    Call<LoginResponse> login(@Body LoginBody body);

}
