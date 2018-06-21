package com.example.smitushev.mybookkeeper.Controllers;

import com.example.smitushev.mybookkeeper.Models.Entry;
import com.example.smitushev.mybookkeeper.WebServices.MyCallback;
import com.example.smitushev.mybookkeeper.WebServices.RestClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EntryController {

    private Retrofit retrofit;
    private String API_BASE_URL = "http://87.98.217.10:4444/";
    private RestClient restClient;

    public EntryController(final String token){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
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

    public void addEntry(Entry entry, final MyCallback<Entry> callback){
        /*
                RestClient client = retrofit.create(RestClient.class);
        Call<UserModel> userModelCall = client.register(userModel);
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
         */
        Call<Entry> addEntryCall = restClient.addEntry(entry);
        addEntryCall.enqueue(new retrofit2.Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    System.out.println("Error while registering: " + response.errorBody().toString());
                    try {
                        callback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void getAllEntriesByUserWithType(Long userId, Boolean type, Integer page, Integer count, final MyCallback<List<Entry>> callback){
        Call<List<Entry>> entriesCall = restClient.getAllEntriesByUserWithType(userId,type,page,count);
        entriesCall.enqueue(new retrofit2.Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    System.out.println("Error while registering: " + response.errorBody().toString());
                    try {
                        callback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
    //    Call<Double> getSumOfAllEntriesWithType(@Path("type")Boolean type);

    public void getSumOfAllEntriesWithType(Boolean type, final MyCallback<Double> callback){
        Call<Double> sumCall = restClient.getSumOfAllEntriesWithType(type);

        sumCall.enqueue(new retrofit2.Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    System.out.println("Error while registering: " + response.errorBody().toString());
                    try {
                        callback.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

}
