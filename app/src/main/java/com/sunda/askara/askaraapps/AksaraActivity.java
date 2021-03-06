package com.sunda.askara.askaraapps;

import android.content.Intent;
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

    // based on AksaraTypes it may set the adpter to view
    private void setAdapterToView(ViewPager viewPager) {
        if(extras.get(Keys.AKSARA_TYPE).equals(Keys.AKSARA_BAKU)) {

            AksaraCategoryPagerAdapter pa = new AksaraCategoryPagerAdapter(getSupportFragmentManager());
            pa.setAbsPath("Aksara_Baku/");
            String[] aksaras = {"Angka", "Ngalagena", "Ngalagena Tambahan", "Swara"};
            pa.setTabTitles(aksaras);

            viewPager.setAdapter(pa);
            getSupportActionBar().setTitle("Aksara Baku");

        } else if(extras.get(Keys.AKSARA_TYPE).equals(Keys.AKSARA_KUNO)) {

            Intent intent = new Intent(this, AksaraKunoActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Nothing Match", Toast.LENGTH_SHORT).show();
        }
    }
}
