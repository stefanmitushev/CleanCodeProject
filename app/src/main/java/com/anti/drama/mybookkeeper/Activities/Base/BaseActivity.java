package com.anti.drama.mybookkeeper.Activities.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.anti.drama.mybookkeeper.App.BaseApplication;
import com.anti.drama.mybookkeeper.Controllers.EntryController;
import com.anti.drama.mybookkeeper.Controllers.UserController;

@SuppressWarnings("deprecation")
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected UserController getUserController(){
        return getBaseApplication().getUserController();
    }

    protected String getUserToken(){
        return getBaseApplication().getUserToken();
    }

    protected void saveUserToken(String token){
        getBaseApplication().saveUserToken(token);
    }

    protected EntryController getEntryController(){
        return getBaseApplication().getEntryController();
    }

    private BaseApplication getBaseApplication(){
        return ((BaseApplication) getApplicationContext());
    }
}
