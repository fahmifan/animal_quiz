package com.example.mortezasaadat.animalquiz;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AksaraKunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksara_kuno);
        getSupportActionBar().setTitle("Aksara Kuno");
        onPrasastiHandler();
    }


    private void onPrasastiHandler () {
        findViewById(R.id.kun_prasasti).setOnClickListener(view -> {
            Intent intent = new Intent(this, AksaraKunoViewPager.class);
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO_PRASASTI);
            startActivity(intent);
        });
    }
}
