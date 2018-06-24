package com.example.smitushev.mybookkeeper.Activities.Entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smitushev.mybookkeeper.Activities.Account.LoginActivity;
import com.example.smitushev.mybookkeeper.Activities.Base.BaseActivity;
import com.example.smitushev.mybookkeeper.Models.Entry;
import com.example.smitushev.mybookkeeper.Models.TokenModel;

import com.example.smitushev.mybookkeeper.R;
import com.example.smitushev.mybookkeeper.WebServices.MyCallback;

import java.util.List;

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

        /*getEntryController().getAllEntriesByUserWithType(3L, true, 0, 10, new MyCallback<List<Entry>>() {
            @Override
            public List<Entry> onResponse(List<Entry> response) {
                System.out.println("Entries: " + response);
                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error);
            }
        });

        Entry entry = new Entry("Android Client","First Created",23.0,true);

        getEntryController().addEntry(entry, new MyCallback<Entry>() {
            @Override
            public Entry onResponse(Entry response) {
                System.out.println("Entry: " + response.getId());

                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error);
            }
        });

        getEntryController().getAllEntriesByCurrentUserWithType(true, 0, 20, new MyCallback<List<Entry>>() {
            @Override
            public List<Entry> onResponse(List<Entry> response) {
                System.out.println("Entries: " + response);
                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error);
            }
        });

        getEntryController().getSumOfAllEntriesWithType(true, new MyCallback<Double>() {
            @Override
            public Double onResponse(Double response) {
                System.out.println("Entries Sum: " + response);
                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error);
            }
        });*/

        startActivity(new Intent(this,AddEntryActivity.class));
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
