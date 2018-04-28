package com.example.mortezasaadat.animalquiz;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class AksaraBakuCategoryPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"Angka", "Ngalagena", "Ngalagena Tambahan", "Swara"};


    public AksaraBakuCategoryPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
        NewAksaraFragment aksaraFragment = new NewAksaraFragment();
        switch (position) {
            case 0: aksaraFragment.setAksaraType("Baku_Angka"); return aksaraFragment;
            case 1: aksaraFragment.setAksaraType("Baku_Ngalagena"); return aksaraFragment;
            case 2: aksaraFragment.setAksaraType("Baku_Ngalagena_Tambahan"); return aksaraFragment;
            case 3: aksaraFragment.setAksaraType("Baku_Swara"); return aksaraFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {return tabTitles.length;}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
