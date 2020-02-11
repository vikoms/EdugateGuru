package com.example.edugateguru;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.edugateguru.Adapter.AboutAdapter;
import com.example.edugateguru.Models.About;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    ViewPager viewPager;
    AboutAdapter adapter;
    List<About> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private ImageView viko,salman,fajar,dea,firman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initComponent();
        initPicture();
        models = new ArrayList<>();
        models.add(new About(R.drawable.brochure, "Brochure", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));
        models.add(new About(R.drawable.sticker, "Sticker", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));
        models.add(new About(R.drawable.poster, "Poster", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));
        models.add(new About(R.drawable.namecard, "Namecard", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));

        adapter = new AboutAdapter(models, this);

//        viewPager = findViewById(R.id.viewPager);
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
//                    viewPager.setBackgroundColor(
//
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//
//                else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    private void initPicture() {

        Glide.with(this).load(R.drawable.profile).into(viko);
        Glide.with(this).load(R.drawable.salman_profile).into(salman);
        Glide.with(this).load(R.drawable.fajar_profile).into(fajar);
        Glide.with(this).load(R.drawable.dea_profile).into(dea);
        Glide.with(this).load(R.drawable.firman_profile).into(firman);

    }

    private void initComponent() {

        viko = findViewById(R.id.viko_profile);
        salman = findViewById(R.id.salman_profile);
        fajar = findViewById(R.id.fajar_profile);
        dea = findViewById(R.id.dea_profile);
        firman = findViewById(R.id.firman_profile);

    }
}
