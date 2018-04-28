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
        AksaraFragment aksaraFragment = new AksaraFragment();
        switch (position) {
            case 0: aksaraFragment.setAksaraType("Baku_Angka"); break;
            case 1: aksaraFragment.setAksaraType("Baku_Ngalagena"); break;
            case 2: aksaraFragment.setAksaraType("Baku_Ngalagena_Tambahan"); break;
            case 3: aksaraFragment.setAksaraType("Baku_Swara"); break;
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
