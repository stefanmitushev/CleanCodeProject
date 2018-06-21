package com.example.smitushev.mybookkeeper.Activities.Account;

//import android.app.ProgressDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smitushev.mybookkeeper.Activities.Base.BaseActivity;
import com.example.smitushev.mybookkeeper.Activities.Entry.MainActivity;
import com.example.smitushev.mybookkeeper.Models.TokenModel;
import com.example.smitushev.mybookkeeper.Models.UserModel;
import com.example.smitushev.mybookkeeper.R;
import com.example.smitushev.mybookkeeper.WebServices.MyCallback;

@SuppressWarnings("deprecation")
public class SignupActivity extends BaseActivity {
    private static final String TAG = "Create Account";


    private EditText _emailText;
    private EditText _passwordText;
    private Button _signupButton;
    private TextView _loginLink;

    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _emailText = (EditText)findViewById(R.id.input_email);
        _passwordText = (EditText)findViewById(R.id.input_password);
        _signupButton = (Button)findViewById(R.id.btn_signup);
        _loginLink = (TextView)findViewById(R.id.link_login);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        UserModel user = new UserModel(username,password);

        getUserController().register(user, new MyCallback<UserModel>() {
            @Override
            public UserModel onResponse(UserModel response) {
                getUserController().login(response.getUserName(), response.getPassword(), new MyCallback<TokenModel>() {
                    @Override
                    public TokenModel onResponse(TokenModel response) {
                        saveUserToken(response.getAccess_token());
                        progressDialog.dismiss();
                        onSignupSuccess();

                        return null;
                    }

                    @Override
                    public void onError(Throwable error) {
                        progressDialog.dismiss();
                        onSignupFailed();
                    }
                });

                return null;
            }

            @Override
            public void onError(Throwable error) {
                progressDialog.dismiss();
                onSignupFailed();
            }
        });

    }


    public void onSignupSuccess() {

        startActivity(new Intent(SignupActivity.this, MainActivity.class));
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Failed Register", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }
}