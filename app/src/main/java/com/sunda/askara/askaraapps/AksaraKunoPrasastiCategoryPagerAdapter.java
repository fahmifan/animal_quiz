package com.sunda.askara.askaraapps;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class AksaraKunoPrasastiCategoryPagerAdapter extends FragmentPagerAdapter {
    // this is also the folder path/folderName where the files are
    private String tabTitles[] = new String[]{"Kwl", "Btls", "Kbtn"};
    private String path = "Aksara_Kuno/Prasasti/";
    public AksaraKunoPrasastiCategoryPagerAdapter(FragmentManager fm) {super(fm);}
    public AksaraKunoPrasastiCategoryPagerAdapter(FragmentManager fm, String[] tabTitles, String path) {
        super(fm);
        this.tabTitles = tabTitles;
        this.path = path;
    }


    @Override
    public Fragment getItem(int position) {
        AksaraFragment aksaraFragment = new AksaraFragment();
        aksaraFragment.setAksaraType(path + tabTitles[position]);
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
