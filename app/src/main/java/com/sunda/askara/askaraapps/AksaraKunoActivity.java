package com.sunda.askara.askaraapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
        onVokalisasiHandler();
        onAngkaHandler();
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

    private void onVokalisasiHandler() {
        findViewById(R.id.kuno_vokalisasi).setOnClickListener(view -> {
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_VOKALISASI);
            startActivity(intent);
        });
    }

    private void onPrasastiHandler () {
        findViewById(R.id.kuno_prasasti).setOnClickListener(view -> {
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_PRASASTI);
            startActivity(intent);
        });
    }

    private void onAngkaHandler() {
        findViewById(R.id.kuno_angka).setOnClickListener(view -> {
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_ANGKA);
            startActivity(intent);
        });
    }
}
