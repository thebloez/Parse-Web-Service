package com.netzme.test.repository;

import com.netzme.test.model.RandomUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ClientRepos {

    @Headers({"Accept: application/json"})
    @GET("api/")
    Call<RandomUser> getUser();
}
