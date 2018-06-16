package Controllers;

import android.content.SharedPreferences;

import com.example.smitushev.mybookkeeper.Models.UserModel;

import org.json.JSONException;

import java.io.IOException;

import WebServices.MyCallback;
import WebServices.RestClient;
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
    //private AccountManager accountManager;
    private UserModel userModel;
    //private UserAccountManager userAccountManager;
    //private OnTokenListener onTokenListener;

    public UserController() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        retrofit =
                builder
                        .client(
                                client
                        )
                        .build();

        //this.userAccountManager = new UserAccountManager(sharedPreferences,getAccountManager());
    }

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
}
