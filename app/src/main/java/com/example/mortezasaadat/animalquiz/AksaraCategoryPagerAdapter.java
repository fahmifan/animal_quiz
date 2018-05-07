package com.example.mortezasaadat.animalquiz;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class AksaraCategoryPagerAdapter extends FragmentPagerAdapter {
    // this is also the folder absPath/folderName where the files are
    private String tabTitles[] = new String[]{"Kwl", "Btls", "Kbtn"};
    private String absPath = "Aksara_Kuno/Prasasti/";

    public AksaraCategoryPagerAdapter(FragmentManager fm) {super(fm);}
    public void setTabTitles(String[] tabTitles) {this.tabTitles = tabTitles;}
    public void setAbsPath(String absPath) {this.absPath = absPath;}

    @Override
    public Fragment getItem(int position) {
        AksaraFragment aksaraFragment = new AksaraFragment();
        aksaraFragment.setAksaraType(absPath + tabTitles[position]);
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
