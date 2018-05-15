package com.sunda.askara.askaraapps;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

// AksaraCategoryPagerAdapter set the ViewPager
class AksaraCategoryPagerAdapter extends FragmentPagerAdapter {
    // this is also the folder absPath/folderName where the files are exists
    // there are default values to avoid null pointer
    private String tabTitles[] = new String[]{"Kwl", "Btls", "Kbtn"};

    // absolute path to a folder of aksara category in assets.
    // there are default values to avoid null pointer
    private String absPath = "Aksara_Kuno/Prasasti/";

    public AksaraCategoryPagerAdapter(FragmentManager fm) {super(fm);}

    // tabTitles is folder name where the aksaras files exists. It will included in view pager
    public void setTabTitles(String[] tabTitles) {this.tabTitles = tabTitles;}

    // set absolute path to a folder of aksara category in assets.
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
