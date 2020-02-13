package com.example.edugateguru.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.edugateguru.AboutActivity;
import com.example.edugateguru.BeritaActivity;
import com.example.edugateguru.LoginActivity;
import com.example.edugateguru.ProfileActivity;
import com.example.edugateguru.R;
import com.example.edugateguru.TugasActivity;
import com.example.edugateguru.AgendaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentHome extends Fragment implements View.OnClickListener {

    CardView cvAgenda, cvProfile, cvAbout, cvTugas,cvBeritSekolah;
    Button btnLogout;
    FirebaseAuth mAuth;
    FirebaseUser user ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater If = getActivity().getLayoutInflater();
        View view = If.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        cvAgenda = getView().findViewById(R.id.agenda);
        cvAgenda.setOnClickListener(this);

        cvProfile = getView().findViewById(R.id.profile);
        cvProfile.setOnClickListener(this);

        cvAbout = getView().findViewById(R.id.agenda);
        cvAbout.setOnClickListener(this);

        cvTugas = getView().findViewById(R.id.tugas);
        cvTugas.setOnClickListener(this);

        btnLogout = getView().findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);

        cvAbout = getView().findViewById(R.id.about);
        cvAbout.setOnClickListener(this);

        cvBeritSekolah = getView().findViewById(R.id.berita_sekolah);
        cvBeritSekolah.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tugas:
                startActivity(new Intent(getActivity().getApplication(), TugasActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(getActivity().getApplication(), ProfileActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(getActivity().getApplication(), AboutActivity.class));
                break;
            case R.id.agenda:
                startActivity(new Intent(getActivity().getApplication(), AgendaActivity.class));
                break;
            case R.id.btn_logout:
                mAuth.signOut();
                getActivity().finish();
                new Intent(getActivity().getApplication(), LoginActivity.class);
                break;
            case R.id.berita_sekolah:
                startActivity(new Intent(getActivity().getApplication(), BeritaActivity.class));
                break;
        }
    }
}
