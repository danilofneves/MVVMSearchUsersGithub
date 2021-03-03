package com.github.users.data.service;



import com.github.users.data.model.Response;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("search/users")
    Single<Response> searchUsers(@Query("q") String query);

}
