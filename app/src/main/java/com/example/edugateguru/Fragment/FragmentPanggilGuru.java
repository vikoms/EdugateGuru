package com.example.edugateguru.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.edugateguru.Adapter.PanggilGuruAdapter;
import com.example.edugateguru.Models.PanggilGuru;
import com.example.edugateguru.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPanggilGuru extends Fragment {

    ProgressBar progressBarNotif;
    List<PanggilGuru> panggilGuruList;
    RecyclerView rvPanggilGuru;
    PanggilGuruAdapter adapter;
    FirebaseUser currentUser;


    public FragmentPanggilGuru() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBarNotif = view.findViewById(R.id.progressBar_panggil_guru);
        rvPanggilGuru = view.findViewById(R.id.rv_panggil_guru);
        getDataPanggilGuru();
    }

    private void getDataPanggilGuru() {
        DatabaseReference refPanggilGuru = FirebaseDatabase.getInstance().getReference("PanggilGuru");
        panggilGuruList = new ArrayList<>();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uidGuru = currentUser.getUid();
        refPanggilGuru.orderByChild("uidGuru").equalTo(uidGuru).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBarNotif.setVisibility(View.INVISIBLE);
                for(DataSnapshot itemPanggilGuru : dataSnapshot.getChildren()) {
                    PanggilGuru guru = itemPanggilGuru.getValue(PanggilGuru.class);
                    panggilGuruList.add(guru);
                }

                setupRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_panggil_guru, container, false);
    }

    public void setupRecyclerView() {
        adapter = new PanggilGuruAdapter(panggilGuruList,getActivity());
        rvPanggilGuru.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPanggilGuru.setHasFixedSize(true);
        rvPanggilGuru.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
