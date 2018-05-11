package com.sunda.askara.askaraapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onAksaraBakuHandler();
        onAksaraKunoHandler();
        onQuizHandler();
    }

    private void onAksaraBakuHandler() {
        findViewById(R.id.btn_aksara_baku).setOnClickListener(view -> {
            Intent intent = new Intent(this, AksaraActivity.class);
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_BAKU);
            startActivity(intent);
        });
    }

    private void onAksaraKunoHandler() {
        findViewById(R.id.btn_aksara_kuno).setOnClickListener(view -> {
            Intent intent = new Intent(this, AksaraActivity.class);
            intent.putExtra(Keys.AKSARA_TYPE, Keys.AKSARA_KUNO);
            startActivity(intent);
        });
    }

    private void onQuizHandler() {
        findViewById(R.id.btn_quiz).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });
    }

}
