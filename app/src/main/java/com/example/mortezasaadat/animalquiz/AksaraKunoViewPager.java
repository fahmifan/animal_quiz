package com.example.mortezasaadat.animalquiz;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AksaraKunoViewPager extends AppCompatActivity {
    ViewPager viewPager;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksara_kuno_view_pager);
        extras = getIntent().getExtras();
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        if(extras != null) setAdapterToView(viewPager);
        else Toast.makeText(this, "extras null", Toast.LENGTH_SHORT).show();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setAdapterToView(ViewPager viewPager) {
        if(extras.get(Keys.AKSARA_TYPE).equals(Keys.AKSARA_KUNO_PRASASTI)) {

            AksaraKunoPrasastiCategoryPagerAdapter pagerAdapter = new AksaraKunoPrasastiCategoryPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);
            getSupportActionBar().setTitle("Aksara Kuno Prasasti");

        } else {
            Toast.makeText(this, "Nothing Match", Toast.LENGTH_SHORT).show();
        }
    }
}

