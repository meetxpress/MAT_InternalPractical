package com.example.internalpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Q1_Login extends AppCompatActivity {

    EditText q1Uname, q1Pass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1__login);

        q1Uname = findViewById(R.id.q1Uname);
        q1Pass = findViewById(R.id.q1Pass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1Uname.getText().toString().equals("") || (q1Pass.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Enter values first!", Toast.LENGTH_SHORT).show();
                } else {
                    if (q1Uname.getText().toString().equals("admin") || (q1Pass.getText().toString().equals("abc"))) {
                        startActivity(new Intent(getApplicationContext(), Q1_Display.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}