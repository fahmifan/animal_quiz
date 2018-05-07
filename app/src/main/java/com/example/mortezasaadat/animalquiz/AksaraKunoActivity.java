package com.example.mortezasaadat.animalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AksaraKunoActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksara_kuno);
        getSupportActionBar().setTitle("Aksara Kuno");
        intent = new Intent(this, AksaraKunoViewPager.class);

        onPrasastiHandler();
        onNaskahPerpustakaanHandler();
        onNaskahCiburuyHandler();
    }

    private void onNaskahCiburuyHandler() {
        findViewById(R.id.kuno_nsciburuy).setOnClickListener(view -> {
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_NSCIBURUY);
            startActivity(intent);
        });
    }

    private void onNaskahPerpustakaanHandler() {
        findViewById(R.id.kuno_nsperpustakaan).setOnClickListener(view -> {
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_NSPERPUSTAKAAN);
            startActivity(intent);
        });
    }

    private void onPrasastiHandler () {
        findViewById(R.id.kuno_prasasti).setOnClickListener(view -> {
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_PRASASTI);
            startActivity(intent);
        });
    }
}
