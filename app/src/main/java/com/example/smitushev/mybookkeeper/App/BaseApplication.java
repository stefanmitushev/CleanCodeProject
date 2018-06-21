package com.example.smitushev.mybookkeeper.App;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.smitushev.mybookkeeper.Controllers.EntryController;
import com.example.smitushev.mybookkeeper.Controllers.UserController;
import com.example.smitushev.mybookkeeper.Models.Entry;

/**
 * Created on 6/21/18 by root.
 */

public class BaseApplication extends Application {

    public static String PREFERENCES_NAME = "com.anti.drama.mybookkeeper";

    private SharedPreferences preferences;

    private UserController userController;

    private EntryController entryController;

    private SharedPreferences getPreferences() {
        if (preferences == null) {
            this.preferences = this.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    public String getUserToken() {
        SharedPreferences userTokenPreferences = getPreferences();

        return userTokenPreferences.getString("token","");
    }

    public void saveUserToken(String token){
        SharedPreferences userTokenPreferences = getPreferences();

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = userTokenPreferences.edit();

        editor.putString("token",token);
        editor.apply();
    }

    public UserController getUserController(){
        if(this.userController == null){
            this.userController = new UserController();
        }

        return this.userController;
    }

    public EntryController getEntryController(){
        if(this.entryController == null){
            this.entryController = new EntryController(getUserToken());
        }

        return this.entryController;
    }
}
