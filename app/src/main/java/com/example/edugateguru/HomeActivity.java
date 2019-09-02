package com.example.edugateguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    CardView menu_tugas,menu_about,menu_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu_tugas = findViewById(R.id.menuTugas);
        menu_tugas.setOnClickListener(this);
        menu_about = findViewById(R.id.menuAbout);
        menu_about.setOnClickListener(this);
        menu_profil = findViewById(R.id.menuProfil);
        menu_profil.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.menuAbout:
                Intent about = new Intent(HomeActivity.this,AboutActivity.class);
                startActivity(about);
                break;
            case R.id.menuProfil:
                Intent profil = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(profil);
                break;
            case R.id.menuTugas:
                Intent Tugas = new Intent(HomeActivity.this,AboutActivity.class);
                startActivity(Tugas);
                break;
        }

    }
}

