package com.example.mortezasaadat.animalquiz;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class AksaraKunoCategoryPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"Prasasti"};

    public AksaraKunoCategoryPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
        AksaraFragment aksaraFragment = new AksaraFragment();
        switch (position) {
            case 0: aksaraFragment.setAksaraType("Aksara_Kuno/Prasasti/1_Kwl"); break;
            default: return null;
        }
        return aksaraFragment;
    }

    @Override
    public int getCount() {return tabTitles.length;}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
