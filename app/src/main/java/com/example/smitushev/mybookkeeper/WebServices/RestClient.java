package com.example.smitushev.mybookkeeper.WebServices;

import com.example.smitushev.mybookkeeper.Models.Entry;
import com.example.smitushev.mybookkeeper.Models.TokenModel;
import com.example.smitushev.mybookkeeper.Models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Stefan96100 on 7/26/2017.
 */

public interface RestClient {
    @Headers({
            "Accept: application/json",
    })

    // ************************************************************************************************************************
    // User Controller requests
    // ************************************************************************************************************************

    @POST("/users/register")
    Call<UserModel> register(@Body UserModel user);

    @POST("/users/me")
    Call<UserModel> getLoggedUser(@Header("Authorization") String authorization);

    @POST("/users/{id}")
    Call<UserModel> getUserById(@Header("Authorization") String authorization, @Path("id") Long id);

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<TokenModel> provideToken(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password,
            @Header("Authorization") String authorization
    );

    // ************************************************************************************************************************
    // Entry Controller requests
    // ************************************************************************************************************************
    @POST("/entries/add")
    Call<Entry> addEntry(@Body Entry entry);

    @POST("/entries/type/user/{user_id}")
    Call<List<Entry>> getAllEntriesByUserWithType(@Path("user_id") Long user_id, @Query("type") Boolean type, @Query("page") Integer page, @Query("count") Integer count);

    @POST("/entries/sum/{type}")
    Call<Double> getSumOfAllEntriesWithType(@Path("type")Boolean type);

    @POST("/entries/{type}")
    Call<List<Entry>> getAllEntriesByCurrentUserWithType(@Path("type") Boolean type,@Query("page") Integer page, @Query("count") Integer count);
}

