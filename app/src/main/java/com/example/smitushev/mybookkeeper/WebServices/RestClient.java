package com.example.smitushev.mybookkeeper.WebServices;

import com.example.smitushev.mybookkeeper.Models.TokenModel;
import com.example.smitushev.mybookkeeper.Models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    /*
    @FormUrlEncoded
    @POST("/users/register")
    Call<UserModel> register(@Field("user") String user);

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<TokenModel> token(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password,
            @Header("Authorization") String authorization
    );

    @GET("/users/username")
    Call<UserModel> username(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/users/getUser")
    Call<UserModel> getUser(@Field("username") String username, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/users/updatepassword")
    Call<UserModel> updatepassword(@Field("oldpassword") String oldpassword, @Field("newpassword") String newpassword, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/users/follow")
    Call<Boolean> follow(@Query("username") String username, @Header("Authentication") String authHeader);

    @FormUrlEncoded
    @POST("/users/thanks")
    Call<Boolean> thanks(@Query("username") String username, @Header("Authorization") String authHeader);


    // ************************************************************************************************************************
// Category Controller requests
// ************************************************************************************************************************
    @GET("/categories/getCategories")
    Call<List<CategoryModel>> getCategories(@Header("Authorization") String authHeader);

    @GET("/categories/getCategory")
    Call<CategoryModel> getCategory(@Query("title") String title, @Header("Authorization") String authHeader);


    // ************************************************************************************************************************
// Threads Controller requests
// ************************************************************************************************************************
    @GET("/threads/getThreads")
    Call<List<ThreadModel>> getThreads(@Query("page") Integer page, @Query("count") Integer count, @Header("Authorization") String authHeader);

    //Only test
    @GET("/threads/getThread")
    Call<ThreadModel> getThread(@Query("title") String title, @Header("Authorization") String authHeader);

    @GET("/threads/getThreadsInCategory")
    Call<List<ThreadModel>> getThreadsInCategory(@Query("title") String title, @Query("page") int page, @Query("count") int count, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/threads/getThreadById")
    Call<ThreadModel> getThreadById(@Field("id") Long id, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/threads/createThread/{category}")
    Call<ThreadModel> createThread(@Field("thread") String thread, @Path("category") String category, @Header("Authorization") String authHeader);

    //TODO
    @FormUrlEncoded
    @POST("/threads/deleteThread")
    Call<Boolean> deleteThread(@Field("id") Long id, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/threads/getPopularThreadsAfterDate")
    Call<List<ThreadModel>> getPopularThreadsAfterDate(
            @Field("date") String date,
            @Field("page") Integer page,
            @Field("count") Integer count,
            @Header("Authorization") String authHeader
    );

    @FormUrlEncoded
    @POST("/threads/getThreadsAfterDate")
    Call<List<ThreadModel>> getThreadsAfterDate(
            @Field("date") String date,
            @Field("page") Integer page,
            @Field("count") Integer count,
            @Header("Authorization") String authHeader
    );

    @FormUrlEncoded
    @POST("/threads/getThreadsByUser")
    Call<List<ThreadModel>> getThreadsByUser(@Field("username") String user, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/threads/getThreadsSortedByDate")
    Call<List<ThreadModel>> getThreadsSortedByDate(@Field("page") Integer page, @Field("count") Integer count, @Header("Authorization") String authHeader);

    //TODO
    @FormUrlEncoded
    @POST("/threads/updateThread")
    Call<ThreadModel> updateThread(
            @Field("id") Long id

    );

    @FormUrlEncoded
    @POST("threads/view")
    void view(@Header("Authorization") String authHeader);


// ************************************************************************************************************************
// Comment Controller requests
// ************************************************************************************************************************

    @GET("/comments/comment/{id}")
    Call<List<CommentModel>> retrieveAllSubommentsInComment(@Path("id") int id, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/comments/comment/{id}")
    Call<CommentModel> addCommentInComment(@Path("id") int id, @Field("comment") String comment, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/comments/thread/{id}")
    Call<CommentModel> addCommentInThread(@Path("id") int id, @Field("comment") String comment, @Header("Authorization") String authHeader);

    @GET("/comments/thread/{id}")
    Call<List<CommentModel>> retrieveAllCommentsInThread(@Path("id") int id, @Header("Authorization") String authHeader);
    */
}

