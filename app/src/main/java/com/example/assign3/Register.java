package com.example.assign3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    TextInputEditText etUsername,etPassword;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        MyDBHelper db=new MyDBHelper(Register.this);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                db.open();
                long records=db.register(username,password);
                db.close();
                startActivity(new Intent(Register.this,Login.class));

            }
        });

    }
    public void init(){
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnCreate=findViewById(R.id.btnCreate);
    }
}