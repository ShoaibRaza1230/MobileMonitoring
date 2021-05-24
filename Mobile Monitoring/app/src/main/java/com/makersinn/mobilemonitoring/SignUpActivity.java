package com.makersinn.mobilemonitoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button signBtn;
    EditText userNameEts,pswdEts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userNameEts=findViewById(R.id.userNameEts);
        pswdEts=findViewById(R.id.pswdEts);
        signBtn = findViewById(R.id.signBtn);
        signBtn.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.signBtn:

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

      break;
        }}
    }

