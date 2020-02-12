package com.example.edugateguru.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.edugateguru.Fragment.FragmentIzinPiket;
import com.example.edugateguru.Fragment.FragmentPanggilGuru;
import com.example.edugateguru.R;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    Context mContext;

    public SectionPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[] {
            R.string.tab_izin_piket,
            R.string.tab_panggil_guru
    };

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentIzinPiket();
                break;
            case 1:
                fragment = new FragmentPanggilGuru();
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
