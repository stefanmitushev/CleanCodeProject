package com.anti.drama.mybookkeeper.Activities.Base;

import android.content.Intent;

import com.anti.drama.mybookkeeper.Activities.Account.LoginActivity;

/**
 * Created by root on 6/24/18.
 */

public class AuthorizeActivity extends BaseActivity {

    @Override
    protected void onResume() {
        super.onResume();
        if(getUserToken() == null){
            startActivity(new Intent(this, LoginActivity.class));
        }else if(getUserToken().equals("")){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
