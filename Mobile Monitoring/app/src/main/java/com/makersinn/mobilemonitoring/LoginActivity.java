package com.makersinn.mobilemonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    TextView signUpBtn;
    EditText userNameEt,pswdEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                break;
            case R.id.signUpBtn:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;

        }
    }
}
