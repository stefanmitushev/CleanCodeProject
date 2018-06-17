package com.example.smitushev.mybookkeeper.Activities.Base;

import android.accounts.Account;
import android.content.SharedPreferences;

public class UserAccountManager {
    private SharedPreferences sharedPreferences;

    public UserAccountManager(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public void saveUserAccount(String username, String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("token",token);

        editor.commit();
    }

    public Account getAccount(){
        String username = sharedPreferences.getString("username","");
        return new Account(username, "com.mybookkeeper");
    }

    public void removeAccount(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public String getToken(){
        return sharedPreferences.getString("token","");
    }

}
