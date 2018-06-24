package com.anti.drama.mybookkeeper.Activities.Entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.anti.drama.mybookkeeper.Activities.Base.BaseActivity;
import com.anti.drama.mybookkeeper.Adapter.EntryAdapter;
import com.anti.drama.mybookkeeper.App.EntryClickListener;
import com.anti.drama.mybookkeeper.Models.Entry;
import com.anti.drama.mybookkeeper.R;
import com.anti.drama.mybookkeeper.WebServices.MyCallback;

import java.util.ArrayList;
import java.util.List;

public class EntriesActivity extends BaseActivity {

    private List<Entry> entries;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private EntryAdapter entryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entries = new ArrayList<>();
        entryAdapter = new EntryAdapter();
        entryAdapter.setEntryClickListener(new EntryClickListener() {
            @Override
            public void onClick(Long id) {
                Intent intent = new Intent(EntriesActivity.this, SingleEntryActivity.class);
                intent.putExtra("ENTRY_ID", id);
                startActivity(intent);
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);//TODO change Grid to LinearManager
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(entryAdapter);

        getEntryController().getAllEntriesByCurrentUserWithType(false, 0, 10, new MyCallback<List<Entry>>() {
            @Override
            public List<Entry> onResponse(List<Entry> response) {
                entries.addAll(response);
                entryAdapter.addEntries(entries);
                return response;
            }

            @Override
            public void onError(Throwable error) {

            }
        });

    }

}
