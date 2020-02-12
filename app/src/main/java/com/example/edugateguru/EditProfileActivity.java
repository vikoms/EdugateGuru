package com.example.edugateguru;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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

import com.example.edugateguru.Models.Guru;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_confirm, btnChangePassword;
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

    private EditText edtNama, edtPhone, edtKota;
    private ImageView imgProfile;
    private Uri imgUri;
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn_confirm = findViewById(R.id.btnConfirm);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        edtNama = findViewById(R.id.edtNama);
        edtKota = findViewById(R.id.edtKota);
        edtPhone = findViewById(R.id.edtTelphone);
        imgProfile = findViewById(R.id.photo_profile);
        btnChangePassword = findViewById(R.id.btn_change_password);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String kota = getIntent().getStringExtra(EXTRA_KOTA);
        String telp = getIntent().getStringExtra(EXTRA_TELP);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);


        myDialog = new Dialog(EditProfileActivity.this);
        myDialog.setContentView(R.layout.dialog_change_password);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        edtNama.setText(nama);
        edtPhone.setText(telp);
        edtKota.setText(kota);

        btn_confirm.setOnClickListener(this);

        imgProfile.setOnClickListener(this);

        btnChangePassword.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Edit Profile");
        }


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
                                    Guru guru = new Guru(valNama, valKota, valNip, valEmail, valPhone, valPelajaran, currentUser.getUid());
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
            Guru guru = new Guru(valNama, valKota, valNip, valEmail, valPhone, valPelajaran, currentUser.getUid());
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnConfirm) {
            String valNama = edtNama.getText().toString();
            String valPhone = edtPhone.getText().toString();
            String valKota = edtKota.getText().toString();
            String valPelajaran = getIntent().getStringExtra(EXTRA_PELAJARAN);
            String valNip = getIntent().getStringExtra(EXTRA_NIP);
            updateData(valNama, currentUser.getEmail(), valPhone, valKota, valNip, valPelajaran, imgUri, mAuth.getCurrentUser());
        } else if (view.getId() == R.id.photo_profile) {
            if (Build.VERSION.SDK_INT >= 22) {
                checkAndRequestForPermissions();
            } else {
                openGallery();
            }
        } else if (view.getId() == R.id.btn_change_password) {
            showDialog();
        }
    }

    private void showDialog() {

        final EditText edtNewPassword = myDialog.findViewById(R.id.edit_new_password);
        final EditText edtOldPassword = myDialog.findViewById(R.id.edit_old_password);
        final Button btnConfirmChangePassword = myDialog.findViewById(R.id.btn_confirm_change_password);
        final ProgressBar pgChangePassword = myDialog.findViewById(R.id.progressBar_change_password);
        myDialog.show();

        btnConfirmChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgChangePassword.setVisibility(View.VISIBLE);
                btnConfirmChangePassword.setVisibility(View.INVISIBLE);
                final String newPassword = edtNewPassword.getText().toString();
                String oldPassword = edtOldPassword.getText().toString();

                if (newPassword.isEmpty() || oldPassword.isEmpty()) {
                    pgChangePassword.setVisibility(View.INVISIBLE);
                    btnConfirmChangePassword.setVisibility(View.VISIBLE);
                    Toast.makeText(EditProfileActivity.this, "Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
                } else {
                    AuthCredential credential = EmailAuthProvider
                            .getCredential(currentUser.getEmail(), oldPassword);

                    currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                currentUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            pgChangePassword.setVisibility(View.INVISIBLE);
                                            btnConfirmChangePassword.setVisibility(View.VISIBLE);

                                            Toast.makeText(EditProfileActivity.this, "Update Password Berhasil", Toast.LENGTH_SHORT).show();
                                            myDialog.dismiss();
                                        }

                                    }
                                });
                            } else {
                                Toast.makeText(EditProfileActivity.this, task.getException() + "", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


    }
}
