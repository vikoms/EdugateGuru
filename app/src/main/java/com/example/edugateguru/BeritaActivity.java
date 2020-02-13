package com.example.edugateguru;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.edugateguru.Models.Berita;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BeritaActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView photoBerita;
    EditText editJudul, editDescription;
    Button btnChoose, btnSimpan;
    String judul, description;
    public static final int pReqCode = 1;
    public static final int REQUESCODE = 1;
    private Uri imgUri;
    ProgressBar pgBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        initComponent();
        initListener();
    }

    private void initListener() {

        btnChoose.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
    }

    private void initComponent() {
        photoBerita = findViewById(R.id.image_berita);
        editJudul = findViewById(R.id.edit_judul);
        editDescription = findViewById(R.id.edit_description);
        btnChoose = findViewById(R.id.btn_choose);
        btnSimpan = findViewById(R.id.btn_simpan);
        pgBerita = findViewById(R.id.pg_berita);

        if(getSupportActionBar() != null ) {
            getSupportActionBar().setTitle("Berita Sekolah");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose:
                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestForPermissions();
                } else {
                    openGallery();
                }
                break;
            case R.id.btn_simpan:
                pgBerita.setVisibility(View.VISIBLE);
                btnSimpan.setVisibility(View.INVISIBLE);
                saveData();
        }
    }

    private void saveData() {

        judul = editJudul.getText().toString();
        description = editDescription.getText().toString();

        if (imgUri != null || !judul.isEmpty() || !description.isEmpty()) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("gambar_berita");
            final StorageReference gambarBerita = storageReference.child(imgUri.getLastPathSegment());
            gambarBerita.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    gambarBerita.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Task<Uri> uriGambar = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriGambar.isComplete()) ;
                            Uri url = uriGambar.getResult();

                            DatabaseReference beritaRef = FirebaseDatabase.getInstance().getReference("Berita");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                            Date date = Calendar.getInstance().getTime();
                            String currentDate = dateFormat.format(date);
                            String id = beritaRef.push().getKey();
                            Berita berita = new Berita(judul, description, url.toString(), currentDate);

                            beritaRef.child(id).setValue(berita).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    finish();
                                    Toast.makeText(BeritaActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });
        } else if (imgUri == null || !judul.isEmpty() || !description.isEmpty()) {
            DatabaseReference beritaRef = FirebaseDatabase.getInstance().getReference("Berita");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = Calendar.getInstance().getTime();
            String currentDate = dateFormat.format(date);
            String id = beritaRef.push().getKey();
            Berita berita = new Berita(judul, description, "", currentDate);

            beritaRef.child(id).setValue(berita);
            Toast.makeText(BeritaActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
        } else if (judul.isEmpty() || description.isEmpty()) {
            Toast.makeText(BeritaActivity.this, "Isi data dengan lengkap", Toast.LENGTH_SHORT).show();
        }

    }


    private void checkAndRequestForPermissions() {
        if (ContextCompat.checkSelfPermission(BeritaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(BeritaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Pleast accept for required permission", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(BeritaActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, pReqCode);
            }
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            imgUri = data.getData();
            photoBerita.setImageURI(imgUri);
        }
    }
}
