package com.anti.drama.mybookkeeper.Activities.Entry;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anti.drama.mybookkeeper.Activities.Base.AuthorizeActivity;
import com.anti.drama.mybookkeeper.Models.Entry;
import com.anti.drama.mybookkeeper.R;
import com.anti.drama.mybookkeeper.WebServices.MyCallback;

public class SingleEntryActivity extends AuthorizeActivity {

    private Long id;
    private TextView comment;
    private TextView description;
    private TextView value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_entry);
        id = getIntent().getLongExtra("ENTRY_ID",1);
        initViews();
        loadEntry();
    }

    private void initViews(){
        comment = (TextView)findViewById(R.id.comment_text);
        description = (TextView)findViewById(R.id.description_text);
        value = (TextView)findViewById(R.id.value_text);
    }

    @SuppressLint("SetTextI18n")
    private void initData(String comment, String description, String value, Boolean type){
        this.comment.setText(comment);
        this.description.setText(description);
        this.value.setText("Value: " + value);
        if(type){
            this.value.setTextColor(Color.RED);
        }else{
            this.value.setTextColor(Color.GREEN);
        }
        this.comment.setVisibility(View.VISIBLE);
        this.description.setVisibility(View.VISIBLE);
        this.value.setVisibility(View.VISIBLE);
    }

    private void loadEntry(){
        getEntryController().getEntryById(id, new MyCallback<Entry>() {
            @Override
            public Entry onResponse(Entry response) {
                initData(response.getComment(),response.getDescription(),response.getValue().toString(),response.getType());
                return response;
            }

            @Override
            public void onError(Throwable error) {
                onFail(error.getMessage());
            }
        });
    }

    private void onFail(String message){
        Toast.makeText(getBaseContext(), "Getting Entry Failed: " + message, Toast.LENGTH_LONG).show();
    }


}
