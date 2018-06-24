package com.anti.drama.mybookkeeper.Activities.Entry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anti.drama.mybookkeeper.Activities.Base.AuthorizeActivity;
import com.anti.drama.mybookkeeper.R;

public class AddEntryActivity extends AuthorizeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
    }

}
