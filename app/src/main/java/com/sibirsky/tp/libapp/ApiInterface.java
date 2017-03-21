package com.sibirsky.tp.libapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sibirsky on 21.03.17.
 */

public interface ApiInterface {
    @GET("/community/{id}")
    Call<UserList> loadData(@Path("id") String id);
}
