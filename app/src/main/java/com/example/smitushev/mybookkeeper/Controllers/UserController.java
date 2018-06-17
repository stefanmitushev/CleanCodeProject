package com.example.smitushev.mybookkeeper.Controllers;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.smitushev.mybookkeeper.Activities.Base.OnTokenListener;
import com.example.smitushev.mybookkeeper.Activities.Base.UserAccountManager;
import com.example.smitushev.mybookkeeper.Models.TokenModel;
import com.example.smitushev.mybookkeeper.Models.UserModel;

import java.io.IOException;
import java.util.Arrays;

import com.example.smitushev.mybookkeeper.WebServices.MyCallback;
import com.example.smitushev.mybookkeeper.WebServices.RestClient;

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
    private AccountManager accountManager;
    private UserAccountManager userAccountManager;
    private OnTokenListener onTokenListener;

    public UserController(SharedPreferences sharedPreferences) {

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

        this.userAccountManager = new UserAccountManager(sharedPreferences);
    }

    // REST Requests
    public void register(UserModel userModel, final MyCallback<UserModel> myCallback){
        RestClient client = retrofit.create(RestClient.class);
        Call<UserModel> userModelCall = client.register(userModel);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    myCallback.onResponse(response.body());
                }else{
                    System.out.println("Error while registering: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                myCallback.onError(t);
            }
        });
    }

    public void getCurrentLoggedUser(String token, final MyCallback<UserModel> myCallback){
        RestClient client = retrofit.create(RestClient.class);
        String authorization = "Bearer " + token;

        Call<UserModel> userModelCall = client.getLoggedUser(authorization);
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
        RestClient client = retrofit.create(RestClient.class);
        String authorization = "Bearer " + token;

        Call<UserModel> userModelCall = client.getUserById(authorization, id);
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
        RestClient client = retrofit.create(RestClient.class);
        byte[] credentials = "debateabout:darko96".getBytes();
        String authorization = "Basic " + Base64Utils.encodeToString(credentials);

        Call<TokenModel> tokenModelCall = client.provideToken("password", userName, password, authorization);
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

    //Utility methods
    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public Account getAccount(){
        if(this.accountManager.getAccountsByType("com.mybookkeeper")[0] != null) {
            return this.accountManager.getAccountsByType("com.mybookkeeper")[0];
        }else{
            return userAccountManager.getAccount();
        }
    }

    public OnTokenListener getOnTokenListener() {
        return onTokenListener;
    }

    public void setOnTokenListener(OnTokenListener onTokenListener) {
        this.onTokenListener = onTokenListener;
    }

    public void getToken(final Activity activity){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String authToken = userAccountManager.getToken();
                accountManager.setAuthToken(getAccount(),"Bearer",authToken);

                return authToken;
            }

            @Override
            protected void onPostExecute(String token) {
                if(token != null){
                    onTokenListener.onTokenAcquired(token);
                }
            }
        };
        task.execute();
    }
}
