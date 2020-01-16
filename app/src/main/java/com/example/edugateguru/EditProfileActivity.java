package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.edugateguru.Models.Guru;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditProfileActivity extends AppCompatActivity {

    Button btn_confirm;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;

    public static final String EXTRA_NAMA = "extra_nama";
    public static final String EXTRA_KOTA = "extra_kota";
    public static final String EXTRA_TELP = "extra_telp";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_NIP = "extra_nip";
    public static final String EXTRA_PELAJARAN = "extra_pelajaran";
    public static final int pReqCode = 1;
    public static final int REQUESCODE = 1;

    private EditText edtNama, edtPhone, edtEmail, edtKota;
    private ImageView imgProfile;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn_confirm = findViewById(R.id.btnConfirm);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtKota = findViewById(R.id.edtKota);
        edtPhone = findViewById(R.id.edtTelphone);
        imgProfile = findViewById(R.id.photo_profile);

        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String kota = getIntent().getStringExtra(EXTRA_KOTA);
        String telp = getIntent().getStringExtra(EXTRA_TELP);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);


        edtNama.setText(nama);
        edtEmail.setText(email);
        edtPhone.setText(telp);
        edtKota.setText(kota);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valNama = edtNama.getText().toString();
                String valEmail = edtEmail.getText().toString();
                String valPhone = edtPhone.getText().toString();
                String valKota = edtKota.getText().toString();
                String valPelajaran = getIntent().getStringExtra(EXTRA_PELAJARAN);
                String valNip = getIntent().getStringExtra(EXTRA_NIP);
                updateData(valNama, valEmail, valPhone, valKota, valNip, valPelajaran, imgUri, mAuth.getCurrentUser());
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestForPermissions();
                } else {
                    openGallery();
                }
            }
        });


    }

    private void checkAndRequestForPermissions() {
        if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Pleast accept for required permission", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, pReqCode);
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

    private void updateData(final String valNama, final String valEmail, final String valPhone, final String valKota, final String valNip, final String valPelajaran, Uri imgUri, final FirebaseUser currentUser) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Guru").child(this.currentUser.getUid());
        if (this.imgUri != null) {
            StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
            final StorageReference imageFilePath = mStorage.child(this.imgUri.getLastPathSegment());
            imageFilePath.putFile(this.imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            UserProfileChangeRequest updateProfile = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(uri).build();

                            currentUser.updateProfile(updateProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Guru guru = new Guru(valNama, valKota, valNip, valEmail, valPhone, valPelajaran);
                                    databaseReference.setValue(guru);

                                    Toast.makeText(EditProfileActivity.this, "Update profile berhasil", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        } else {
            Guru guru = new Guru(valNama, valKota, valNip, valEmail, valPhone, valPelajaran);
            databaseReference.setValue(guru);

            Toast.makeText(EditProfileActivity.this, "Update profile berhasil", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
            finish();
        }

//

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            imgUri = data.getData();
            imgProfile.setImageURI(imgUri);
        }
    }
}
