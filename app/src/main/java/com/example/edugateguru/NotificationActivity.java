package com.example.edugateguru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.edugateguru.Adapter.IzinPiketAdapter;
import com.example.edugateguru.Adapter.SectionPagerAdapter;
import com.example.edugateguru.Models.IzinPiket;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    List<IzinPiket> izinPiketList;
    RecyclerView rvIzinPiket;
    DatabaseReference ref;
    IzinPiketAdapter adapter;
    ProgressBar progressBarNotif;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initComponent();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Notifikasi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void initComponent() {
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(),this);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }
}
