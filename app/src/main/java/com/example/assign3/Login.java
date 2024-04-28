package com.example.assign3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextInputEditText etUsername,etPassword;
    Button btnLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                MyDBHelper db = new MyDBHelper(Login.this);
                db.open();

                if (db.checkUsername(username)) {
                    if (db.checkPassword(username, password)) {
                        startActivity(new Intent(Login.this, Items.class));
                    } else {
                        Toast.makeText(Login.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Username not found", Toast.LENGTH_SHORT).show();
                }

                db.close();
            }
        });
            }


    public void init(){
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogged=findViewById(R.id.btnLogged);

    }
}