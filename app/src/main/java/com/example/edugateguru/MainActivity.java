package com.example.edugateguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;
    private static final int TIME_LOADING = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_started);
        btnStart.setOnClickListener(this);

        btnStart.setVisibility(View.INVISIBLE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {
            btnStart.setVisibility(View.INVISIBLE);
            Intent moveToHome = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(moveToHome);
            finish();
        }  else if (user == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(login);
                    finish();
                }
            },TIME_LOADING);
        }



    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
