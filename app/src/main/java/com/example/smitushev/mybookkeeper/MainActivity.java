package com.example.smitushev.mybookkeeper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smitushev.mybookkeeper.Models.UserModel;

import Controllers.UserController;
import WebServices.MyCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        UserController userController = new UserController();
        UserModel userModel = new UserModel(0L,"stefan1", "123456789");

        userController.register(userModel, new MyCallback<UserModel>() {
            @Override
            public UserModel onResponse(UserModel response) {
                System.out.println("Success: " + response);
                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error.getMessage());
            }
        });
    }

}
