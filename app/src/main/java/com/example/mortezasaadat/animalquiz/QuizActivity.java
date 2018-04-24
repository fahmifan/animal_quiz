package com.example.mortezasaadat.animalquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Set;

public class QuizActivity extends AppCompatActivity {


    public static final String GUESSES = "settings_numberOfGuesses";
    public static final String ANIMALS_TYPE = "settings_animalsType";
    public static final String QUIZ_BACKGROUND_COLOR = "settings_quiz_background_color";
    public static final String QUIZ_FONT = "settings_quiz_font";


    private boolean isSettingsChanged = false;


    static Typeface chunkfive;
    static Typeface fontlerybrown;
    static Typeface wonderbarDemo;


    QuizActivityFragment modifyQuizFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        chunkfive = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        fontlerybrown = Typeface.createFromAsset(getAssets(), "fonts/FontleroyBrown.ttf");
        wonderbarDemo = Typeface.createFromAsset(getAssets(), "fonts/Wonderbar Demo.otf");


        PreferenceManager.setDefaultValues(QuizActivity.this, R.xml.quiz_preferences, false);


        PreferenceManager.getDefaultSharedPreferences(QuizActivity.this).
                registerOnSharedPreferenceChangeListener(settingsChangeListener);


        modifyQuizFragment = (QuizActivityFragment) getSupportFragmentManager().findFragmentById(R.id.animalQuizFragment);

        modifyQuizFragment.modifyAksaraGuessRows(PreferenceManager.getDefaultSharedPreferences(QuizActivity.this));
        modifyQuizFragment.modifyTypeOfAksaraInQuiz(PreferenceManager.getDefaultSharedPreferences(QuizActivity.this));
        modifyQuizFragment.modifyQuizFont(PreferenceManager.getDefaultSharedPreferences(QuizActivity.this));
        modifyQuizFragment.modifyBackgroundColor(PreferenceManager.getDefaultSharedPreferences(QuizActivity.this));
        modifyQuizFragment.resetAnimalQuiz();
        isSettingsChanged = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Intent preferencesIntent = new Intent(QuizActivity.this, SettingsActivity.class);
        startActivity(preferencesIntent);
        return super.onOptionsItemSelected(item);

    }

    private SharedPreferences.OnSharedPreferenceChangeListener settingsChangeListener
            = new SharedPreferences.OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


            isSettingsChanged = true;

            if (key.equals(GUESSES)) {

                modifyQuizFragment.modifyAksaraGuessRows(sharedPreferences);
                modifyQuizFragment.resetAnimalQuiz();

            } else if (key.equals(ANIMALS_TYPE)) {

                Set<String> animalTypes = sharedPreferences.getStringSet(ANIMALS_TYPE, null);

                if (animalTypes != null && animalTypes.size() > 0) {

                    modifyQuizFragment.modifyTypeOfAksaraInQuiz(sharedPreferences);
                    modifyQuizFragment.resetAnimalQuiz();

                } else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    animalTypes.add(getString(R.string.default_animal_type));
                    editor.putStringSet(ANIMALS_TYPE, animalTypes);
                    editor.apply();

                    Toast.makeText(QuizActivity.this,
                            R.string.toast_message, Toast.LENGTH_SHORT).show();

                }

            } else if (key.equals(QUIZ_FONT)) {

                modifyQuizFragment.modifyQuizFont(sharedPreferences);
                modifyQuizFragment.resetAnimalQuiz();
            } else if (key.equals(QUIZ_BACKGROUND_COLOR)) {

                modifyQuizFragment.modifyBackgroundColor(sharedPreferences);
                modifyQuizFragment.resetAnimalQuiz();

            }

            Toast.makeText(QuizActivity.this, R.string.change_message, Toast.LENGTH_SHORT).show();




        }
    };

}
