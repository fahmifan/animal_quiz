package com.sunda.askara.askaraapps;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mortezasaadat.askaraapps.R;

public class AksaraKunoViewPager extends AppCompatActivity {
    ViewPager viewPager;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksara_kuno_view_pager);
        extras = getIntent().getExtras();
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        if(extras != null) {
            String aksaraType = extras.getString(Keys.AKSARA_TYPE);
            setAdapterToView(viewPager, aksaraType);
        }
        else Toast.makeText(this, "extras null", Toast.LENGTH_SHORT).show();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setAdapterToView(ViewPager viewPager, String aksaraType) {
        AksaraCategoryPagerAdapter pa = new AksaraCategoryPagerAdapter(getSupportFragmentManager());

        switch (aksaraType) {
            case Keys.AKSARA_KUNO_PRASASTI: {
                String[] aksaras = {"Kwl", "Btls", "Kbtn"};
                String folderPath = "Aksara_Kuno/Prasasti/";
                pa.setAbsPath(folderPath);
                pa.setTabTitles(aksaras);
                getSupportActionBar().setTitle("Aksara Kuno Prasasti");
                break;
            }
            case Keys.AKSARA_KUNO_NSCIBURUY: {
                String[] aksaras = {"I", "II", "III", "IV"};
                String folderPath = "Aksara_Kuno/Naskah/Ciburuy/";
                pa.setAbsPath(folderPath);
                pa.setTabTitles(aksaras);
                getSupportActionBar().setTitle("Naskah Ciburuy");
                break;
            }
            case Keys.AKSARA_KUNO_NSPERPUSTAKAAN: {
                String[] aksaras = {"CP", "FCP", "CRP", "PRR", "SD", "BM"};
                String folderPath = "Aksara_Kuno/Naskah/Perpustakaan/";
                pa.setAbsPath(folderPath);
                pa.setTabTitles(aksaras);
                getSupportActionBar().setTitle("Naskah Perpustakaan");
                break;
            }
            default:
                Toast.makeText(this, "Nothing Match", Toast.LENGTH_SHORT).show();
                break;
        }

        viewPager.setAdapter(pa);
    }
}

