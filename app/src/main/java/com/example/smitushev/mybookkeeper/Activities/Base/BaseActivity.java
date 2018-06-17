package com.example.smitushev.mybookkeeper.Activities.Base;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.smitushev.mybookkeeper.Controllers.UserController;

public class BaseActivity extends AppCompatActivity implements OnTokenListener {
    private AccountManager accountManager;
    private UserController userController;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        accountManager = AccountManager.get(getBaseContext());

        sharedPreferences = this.getSharedPreferences("MyBookKeeper", MODE_PRIVATE);

        userController = new UserController(sharedPreferences);
        userController.setAccountManager(accountManager);

        //userController.getToken(this);
        userController.setOnTokenListener(this);


        super.onCreate(savedInstanceState);
    }

    @Override
    public void onTokenAcquired(String token) {

    }

    @Override
    public void onError() {

    }
}
