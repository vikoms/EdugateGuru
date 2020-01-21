package com.example.edugateguru;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        models = new ArrayList<>();
        models.add(new About(R.drawable.brochure, "Brochure", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));
        models.add(new About(R.drawable.sticker, "Sticker", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));
        models.add(new About(R.drawable.poster, "Poster", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));
        models.add(new About(R.drawable.namecard, "Namecard", "Diterjemahkan dari bahasa Inggris-Pusat Layanan Darurat Pusat Kota adalah sebuah organisasi nirlaba di Seattle, Washington"));

        adapter = new AboutAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
