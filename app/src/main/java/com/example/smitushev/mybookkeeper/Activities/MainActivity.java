package com.example.smitushev.mybookkeeper.Activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smitushev.mybookkeeper.Activities.Base.BaseActivity;
import com.example.smitushev.mybookkeeper.Models.TokenModel;
import com.example.smitushev.mybookkeeper.Models.UserModel;

import com.example.smitushev.mybookkeeper.Controllers.UserController;
import com.example.smitushev.mybookkeeper.R;
import com.example.smitushev.mybookkeeper.WebServices.MyCallback;

public class MainActivity extends BaseActivity {

    private String authtoken;
    private TokenModel token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NetworkSecurityPolicy.getInstance().setCleartextTrafficPermitted((data.appInfo.flags & ApplicationInfo.FLAG_USES_CLEARTEXT_TRAFFIC) != 0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        UserController userController = new UserController(getSharedPreferences("MyBookKeeper", MODE_PRIVATE));
        UserModel userModel = new UserModel(0L,"stefan1", "123456789");

        userController.login("stefan", "123456789", new MyCallback<TokenModel>() {
            @Override
            public TokenModel onResponse(TokenModel response) {
                System.out.println("SUccessed login: " + response.getAccess_token());
                /*
                authtoken = response.getAccess_token();
                token = response;
                final Intent res = new Intent(MainActivity.this,MainActivity.class);
                res.putExtra(AccountManager.KEY_ACCOUNT_NAME, userName);
                res.putExtra(AccountManager.KEY_ACCOUNT_TYPE, "com.frine");
                res.putExtra(AccountManager.KEY_AUTHTOKEN, authtoken);
                res.putExtra(PARAM_USER_PASS, userPass);
                callback.onResponse(res);
                return response;
                */


                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Ërror: " + error.getMessage());
            }
        });

        startActivity(new Intent(this, LoginActivity.class));

        //userController.getCurrentLoggedUser();
    }

    @Override
    public void onTokenAcquired(String token) {
        super.onTokenAcquired(token);
    }

    @Override
    public void onError() {
        super.onError();
    }
}
