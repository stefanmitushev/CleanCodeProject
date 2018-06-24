package com.anti.drama.mybookkeeper.Controllers;


import com.anti.drama.mybookkeeper.Models.TokenModel;
import com.anti.drama.mybookkeeper.Models.UserModel;

import java.io.IOException;
import java.util.Arrays;

import com.anti.drama.mybookkeeper.WebServices.MyCallback;
import com.anti.drama.mybookkeeper.WebServices.RestClient;

import org.springframework.util.Base64Utils;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserController {
    private Retrofit retrofit;
    private String API_BASE_URL = "http://87.98.217.10:4444/";
    private RestClient restClient;


    public UserController() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "")
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT, ConnectionSpec.COMPATIBLE_TLS))
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(client).build();
        restClient = retrofit.create(RestClient.class);


    }

    // REST Requests
    public void register(UserModel userModel, final MyCallback<UserModel> myCallback){
        Call<UserModel> userModelCall = restClient.register(userModel);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    myCallback.onResponse(response.body());
                }else{
                    System.out.println("Error while registering: " + response.errorBody().toString());
                    try {
                        myCallback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        myCallback.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                myCallback.onError(t);
            }
        });
    }

    public void getCurrentLoggedUser(String token, final MyCallback<UserModel> myCallback){
        String authorization = "Bearer " + token;

        Call<UserModel> userModelCall = restClient.getLoggedUser(authorization);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    myCallback.onResponse(response.body());
                }else{
                    try {
                        myCallback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        myCallback.onError(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                myCallback.onError(t);
            }
        });
    }

    public void getUserById(String token, Long id, final MyCallback<UserModel> myCallback){
        String authorization = "Bearer " + token;

        Call<UserModel> userModelCall = restClient.getUserById(authorization, id);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    myCallback.onResponse(response.body());
                }else{
                    try {
                        myCallback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        myCallback.onError(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                myCallback.onError(t);
            }
        });
    }

    public void login(String userName, String password, final MyCallback<TokenModel> myCallback){
        byte[] credentials = "debateabout:darko96".getBytes();
        String authorization = "Basic " + Base64Utils.encodeToString(credentials);

        Call<TokenModel> tokenModelCall = restClient.provideToken("password", userName, password, authorization);
        tokenModelCall.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if(response.isSuccessful()){
                    myCallback.onResponse(response.body());
                }else{
                    try {
                        myCallback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        myCallback.onError(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {
                myCallback.onError(t);
            }
        });
    }
}
