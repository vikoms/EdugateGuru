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
import android.widget.Toast;

import com.example.edugateguru.Adapter.IzinPiketAdapter;
import com.example.edugateguru.Models.IzinPiket;
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
public class FragmentIzinPiket extends Fragment {

    ProgressBar progressBarNotif;
    List<IzinPiket> izinPiketList;
    RecyclerView rvIzinPiket;
    IzinPiketAdapter adapter;
    public FragmentIzinPiket() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBarNotif = view.findViewById(R.id.progressBar_izin_piket);
        rvIzinPiket = view.findViewById(R.id.rv_izin_piket);

        getDataIzinPiket();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_izin_piket, container, false);
    }

    private void getDataIzinPiket() {
        izinPiketList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Izin Piket");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String id = currentUser.getUid();
        ref.orderByChild("keyGuru_status").equalTo(id+"_0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBarNotif.setVisibility(View.INVISIBLE);
                izinPiketList.clear();
                for( DataSnapshot itemIzin:dataSnapshot.getChildren()) {
                    IzinPiket izinPiket = itemIzin.getValue(IzinPiket.class);
                    izinPiketList.add(izinPiket);
                }

                setupRecyclerView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setupRecyclerView() {
        adapter = new IzinPiketAdapter(izinPiketList);
        rvIzinPiket.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIzinPiket.setHasFixedSize(true);
        rvIzinPiket.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
