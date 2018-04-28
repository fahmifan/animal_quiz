package com.example.mortezasaadat.animalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onAksaraBakuHandler();
        onQuizClick();
    }

    private void onAksaraBakuHandler() {
        findViewById(R.id.btn_aksara_baku).setOnClickListener(view -> {
            Intent intent = new Intent(this, NewAksaraActivity.class);
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
