package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edugateguru.Models.Guru;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser userInfo;
    DatabaseReference database;
    TextView txtEmail,txtNama,txtPhone,txtNip,txtIdGuru,txtKota;
    String pelajaran;
    ImageView userPhoto;
    FrameLayout frameProfile;
    Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        userInfo = mAuth.getCurrentUser();
        txtEmail = findViewById(R.id.txtEmail);
        txtNama = findViewById(R.id.txtNama);
        txtPhone = findViewById(R.id.txtPhone);
        txtNip = findViewById(R.id.txtNIP);
        txtIdGuru = findViewById(R.id.txtIdGuru);
        txtKota = findViewById(R.id.txtAlamat);
        userPhoto = findViewById(R.id.photo_profile);
        frameProfile = findViewById(R.id.frame_profile);
        btnEditProfile = findViewById(R.id.editprofile);
        database = FirebaseDatabase.getInstance().getReference().child("Users").child("Guru").child(userInfo.getUid());

      database.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              frameProfile.setVisibility(View.INVISIBLE);
              btnEditProfile.setVisibility(View.VISIBLE);
            String nama = dataSnapshot.child("nama").getValue().toString();
            txtNama.setText(nama);
            String nip = dataSnapshot.child("nip").getValue().toString();
            txtNip.setText(nip);
            txtIdGuru.setText(nip);
            String kota = dataSnapshot.child("kota").getValue().toString();
            txtKota.setText(kota);
            String telp = dataSnapshot.child("telp").getValue().toString();
            txtPhone.setText(telp);
            pelajaran = dataSnapshot.child("pelajaran").getValue().toString();
//              Toast.makeText(ProfileActivity.this, pelajaran, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edtProfileIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                edtProfileIntent.putExtra(EditProfileActivity.EXTRA_NAMA,txtNama.getText().toString());
                edtProfileIntent.putExtra(EditProfileActivity.EXTRA_KOTA,txtKota.getText().toString());
                edtProfileIntent.putExtra(EditProfileActivity.EXTRA_EMAIL,txtEmail.getText().toString());
                edtProfileIntent.putExtra(EditProfileActivity.EXTRA_TELP,txtPhone.getText().toString());
                edtProfileIntent.putExtra(EditProfileActivity.EXTRA_PELAJARAN,pelajaran);
                edtProfileIntent.putExtra(EditProfileActivity.EXTRA_NIP,txtNip.getText().toString());
                startActivity(edtProfileIntent);
            }
        });

        txtEmail.setText(userInfo.getEmail());
        Glide.with(ProfileActivity.this).load(userInfo.getPhotoUrl()).into(userPhoto);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
