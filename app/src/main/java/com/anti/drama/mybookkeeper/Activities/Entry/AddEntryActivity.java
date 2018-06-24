package com.anti.drama.mybookkeeper.Activities.Entry;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.anti.drama.mybookkeeper.Activities.Base.AuthorizeActivity;
import com.anti.drama.mybookkeeper.Models.Entry;
import com.anti.drama.mybookkeeper.R;
import com.anti.drama.mybookkeeper.WebServices.MyCallback;

public class AddEntryActivity extends AuthorizeActivity {

    private EditText inputComment;
    private EditText inputDescription;
    private EditText inputValue;
    private RadioButton paying;
    private RadioButton receiving;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        initViews();
        initListeners();
    }

    private void initViews() {
        inputComment = (EditText)findViewById(R.id.input_comment);
        inputDescription = (EditText)findViewById(R.id.input_description);
        inputValue = (EditText)findViewById(R.id.input_value);
        paying = (RadioButton)findViewById(R.id.paying);
        receiving = (RadioButton)findViewById(R.id.receiving);
        submit = (Button)findViewById(R.id.btn_submit);
    }

    private void initListeners(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitEntry();
            }
        });
    }

    public void submitEntry(){
        if(validate()) {
            Entry entry = new Entry(inputDescription.getText().toString(), inputComment.getText().toString()
                    , Double.valueOf(inputValue.getText().toString()), paying.isChecked());
            addEntry(entry);
        }else{
            Toast.makeText(getBaseContext(), "Please fill all the input fields", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validate(){
        if(inputDescription.getText() == null || inputComment.getText() == null || inputValue.getText() == null){
            return false;
        }else if(inputDescription.getText().toString().equals("") || inputComment.getText().toString().equals("") || inputValue.getText().toString().equals("")) {
            return false;
        }

        return true;
    }


    private void addEntry(final Entry entry){

        getEntryController().addEntry(entry, new MyCallback<Entry>() {
            @Override
            public Entry onResponse(Entry response) {
                onSuccess(response);
                return response;
            }

            @Override
            public void onError(Throwable error) {
                onFail(error.getMessage());
            }
        });
    }

    private void onSuccess(Entry entry){
        Intent intent = new Intent(this,SingleEntryActivity.class);
        intent.putExtra("ENTRY_ID", entry.getId());
        startActivity(intent);
        finish();
    }

    private void onFail(String message){
        Toast.makeText(getBaseContext(), "Adding failed: " + message, Toast.LENGTH_LONG).show();
    }

}
