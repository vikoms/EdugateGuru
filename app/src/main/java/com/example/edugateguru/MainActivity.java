package com.example.edugateguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_started);
        btnStart.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
