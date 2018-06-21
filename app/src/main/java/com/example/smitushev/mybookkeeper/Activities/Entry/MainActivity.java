package com.example.smitushev.mybookkeeper.Activities.Entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smitushev.mybookkeeper.Activities.Account.LoginActivity;
import com.example.smitushev.mybookkeeper.Activities.Base.BaseActivity;
import com.example.smitushev.mybookkeeper.Models.TokenModel;

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
        //userController.getCurrentLoggedUser();
    }

    @Override
    protected void onResume() {
        if(getUserToken() == null){
            startActivity(new Intent(this, LoginActivity.class));
        }else if(getUserToken().equals("")){
            startActivity(new Intent(this, LoginActivity.class));
        }
        super.onResume();
    }
}
