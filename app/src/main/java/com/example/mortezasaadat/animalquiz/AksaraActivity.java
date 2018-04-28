package com.example.mortezasaadat.animalquiz;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AksaraActivity extends AppCompatActivity {

    ViewPager viewPager;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksara);

        extras = getIntent().getExtras();
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        if(extras != null) setAdapterToView(viewPager);
        else Toast.makeText(this, "extras null", Toast.LENGTH_SHORT).show();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setAdapterToView(ViewPager viewPager) {
        if(extras.get(Keys.AKSARA_TYPE).equals(Keys.AKSARA_BAKU)) {

            AksaraBakuCategoryPagerAdapter pagerAdapter = new AksaraBakuCategoryPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);

        } else if(extras.get(Keys.AKSARA_TYPE).equals(Keys.AKSARA_KUNO)) {

            AksaraKunoCategoryPagerAdapter pagerAdapter = new AksaraKunoCategoryPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);

        } else {
            Toast.makeText(this, "Nothing Match", Toast.LENGTH_SHORT).show();
        }
    }
}
