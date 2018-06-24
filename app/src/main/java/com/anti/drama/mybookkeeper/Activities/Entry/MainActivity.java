package com.anti.drama.mybookkeeper.Activities.Entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.anti.drama.mybookkeeper.Activities.Account.LoginActivity;
import com.anti.drama.mybookkeeper.Activities.Base.AuthorizeActivity;
import com.anti.drama.mybookkeeper.Activities.Base.BaseActivity;
import com.anti.drama.mybookkeeper.Adapter.EntryAdapter;
import com.anti.drama.mybookkeeper.Models.Entry;
import com.anti.drama.mybookkeeper.Models.TokenModel;

import com.anti.drama.mybookkeeper.R;
import com.anti.drama.mybookkeeper.WebServices.MyCallback;

import java.util.List;

public class MainActivity extends AuthorizeActivity {

    private String authtoken;
    private TokenModel token;
    private List<Entry> entries;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private EntryAdapter entryAdapter;

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
                Intent intent = new Intent(MainActivity.this, EntriesActivity.class);
                finish();
                startActivity(intent);
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

        /*getEntryController().getEntryById(1L, new MyCallback<Entry>() {
            @Override
            public Entry onResponse(Entry response) {
                System.out.println("Success Entry: " + response);
                return null;
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error Entry: " + error);
            }
        });*/

        startActivity(new Intent(this,AddEntryActivity.class));
    }

}
