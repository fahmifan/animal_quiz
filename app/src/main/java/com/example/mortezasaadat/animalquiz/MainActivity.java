package com.example.mortezasaadat.animalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBelajarAksaraHandler();
        onQuizClick();
    }

    private void onBelajarAksaraHandler() {
        findViewById(R.id.btn_belajar_aksara).setOnClickListener(view -> {
            Intent intent = new Intent(this, AksaraActivity.class);
            startActivity(intent);
        });
    }

    private void onQuizClick() {
        findViewById(R.id.btn_quiz).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });
    }

}
