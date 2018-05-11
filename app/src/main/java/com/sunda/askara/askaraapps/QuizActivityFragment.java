package com.sunda.askara.askaraapps;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuizActivityFragment extends Fragment {

    private static final int NUMBER_OF_AKSARA_INCLUDED_IN_QUIZ = 10;

    private List<String> allAksaraList;
    private List<String> aksaraNamesQuizList;
    private Set<String> aksaraTypesInQuiz;
    private String correctAksaraAnswer;
    private int numberOfAllGuesses;
    private int numberOfRightAnswers;
    private int numberOfAksaraGuessRows;
    private SecureRandom secureRandomNumber;
    private Handler handler;
    private Animation wrongAnswerAnimation;

    private LinearLayout aksaraQuizLinearLayout;
    private TextView txtQuestionNumber;
    private ImageView imgAksara;
    private LinearLayout[] rowsOfGuessButtonsAksaraQuiz;
    private TextView txtAnswer;

    public QuizActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        allAksaraList = new ArrayList<>();
        aksaraNamesQuizList = new ArrayList<>();
        secureRandomNumber = new SecureRandom();
        handler = new Handler();

        wrongAnswerAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.wrong_answer_animation);
        wrongAnswerAnimation.setRepeatCount(1);


        aksaraQuizLinearLayout = (LinearLayout) view.findViewById(R.id.animalQuizLinearLayout);
        txtQuestionNumber = (TextView) view.findViewById(R.id.txtQuestionNumber);
        imgAksara = (ImageView) view.findViewById(R.id.imgAnimal);
        rowsOfGuessButtonsAksaraQuiz = new LinearLayout[3];
        rowsOfGuessButtonsAksaraQuiz[0] = (LinearLayout) view.findViewById(R.id.firstRowLinearLayout);
        rowsOfGuessButtonsAksaraQuiz[1] = (LinearLayout) view.findViewById(R.id.secondRowLinearLayout);
        rowsOfGuessButtonsAksaraQuiz[2] = (LinearLayout) view.findViewById(R.id.thirdRowLinearLayout);
        txtAnswer = (TextView) view.findViewById(R.id.txtAnswer);

        for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

            for (int column = 0; column < row.getChildCount(); column++) {

                Button btnGuess = (Button) row.getChildAt(column);
                btnGuess.setOnClickListener(btnGuessListener);
                btnGuess.setTextSize(24);
            }
        }

        txtQuestionNumber.setText(getString(R.string.question_text, 1, NUMBER_OF_AKSARA_INCLUDED_IN_QUIZ));
        return view;
    }


    private View.OnClickListener btnGuessListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Button btnGuess = ((Button) view);
            String guessValue = btnGuess.getText().toString();
            String answerValue = getTheExactAksaraName(correctAksaraAnswer);
            ++numberOfAllGuesses;

            if (guessValue.equals(answerValue)) {

                ++numberOfRightAnswers;

                txtAnswer.setText(answerValue + "!" + " RIGHT");

                disableQuizGuessButtons();

                if (numberOfRightAnswers == NUMBER_OF_AKSARA_INCLUDED_IN_QUIZ) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(getString(R.string.results_string_value, numberOfAllGuesses,
                            (1000 / (double) numberOfAllGuesses)));


                    builder.setPositiveButton(R.string.reset_animal_quiz, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            resetAksaraQuiz();

                        }
                    }).create().show();
                    builder.setCancelable(false);

                } else {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animateAksaraQuiz(true);
                        }
                    }, 1000);

                }

            } else {

                imgAksara.startAnimation(wrongAnswerAnimation);

                txtAnswer.setText(R.string.wrong_answer_message);
                btnGuess.setEnabled(false);

            }
        }
    };

    private String getTheExactAksaraName(String aksaraName) {
        return aksaraName.substring(aksaraName.indexOf('-') + 1).replace('_', ' ');
    }


    private void disableQuizGuessButtons() {

        for (int row = 0; row < numberOfAksaraGuessRows; row++) {

            LinearLayout guessRowLinearLayout = rowsOfGuessButtonsAksaraQuiz[row];

            for (int buttonIndex = 0; buttonIndex < guessRowLinearLayout.getChildCount(); buttonIndex++) {

                guessRowLinearLayout.getChildAt(buttonIndex).setEnabled(false);

            }

        }

    }


    public void resetAksaraQuiz() {

        AssetManager assets = getActivity().getAssets();
        allAksaraList.clear();

        try {

            for (String aksaraType : aksaraTypesInQuiz) {

                String[] aksaraImagePathsInQuiz = assets.list(aksaraType);

                for (String aksaraImagePathInQuiz : aksaraImagePathsInQuiz) {

                    allAksaraList.add(aksaraImagePathInQuiz.replace(".png", ""));

                }

            }

        } catch (IOException e) {
            Log.e("Askara", "Error", e);
        }

        numberOfRightAnswers = 0;
        numberOfAllGuesses = 0;
        aksaraNamesQuizList.clear();

        int counter = 1;
        int numberOfAvailableAnimals = allAksaraList.size();

        while (counter <= NUMBER_OF_AKSARA_INCLUDED_IN_QUIZ) {

            int randomIndex = secureRandomNumber.nextInt(numberOfAvailableAnimals);

            String animalImageName = allAksaraList.get(randomIndex);

            if (!aksaraNamesQuizList.contains(animalImageName)) {

                aksaraNamesQuizList.add(animalImageName);
                ++counter;

            }

        }

        showNextAksara();

    }


    private void animateAksaraQuiz(boolean animateOutAksaraImage) {

        if (numberOfRightAnswers == 0) {
            return;
        }

        int xTopLeft = 0;
        int yTopLeft = 0;


        int xBottomRight = aksaraQuizLinearLayout.getLeft() + aksaraQuizLinearLayout.getRight();
        int yBottomRight = aksaraQuizLinearLayout.getTop() + aksaraQuizLinearLayout.getBottom();

        // Here is max value for radius
        int radius = Math.max(aksaraQuizLinearLayout.getWidth(), aksaraQuizLinearLayout.getHeight());

        Animator animator;

        if (animateOutAksaraImage) {

            animator = ViewAnimationUtils.createCircularReveal(aksaraQuizLinearLayout,
                    xBottomRight, yBottomRight, radius, 0);


            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    showNextAksara();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });


        } else {

            animator = ViewAnimationUtils.createCircularReveal(aksaraQuizLinearLayout,
                    xTopLeft, yTopLeft, 0, radius);

        }

        animator.setDuration(700);
        animator.start();

    }


    private void showNextAksara() {

        String nextAksaraImageName = aksaraNamesQuizList.remove(0);
        correctAksaraAnswer = nextAksaraImageName;
        txtAnswer.setText("");

        txtQuestionNumber.setText(getString(R.string.question_text,
                (numberOfRightAnswers + 1), NUMBER_OF_AKSARA_INCLUDED_IN_QUIZ));

        String aksaraType = nextAksaraImageName.substring(0, nextAksaraImageName.indexOf("-"));

        AssetManager assets = getActivity().getAssets();

        try (InputStream stream = assets.open(aksaraType + "/" + nextAksaraImageName + ".png")) {

            Drawable animalImage = Drawable.createFromStream(stream, nextAksaraImageName);

            imgAksara.setImageDrawable(animalImage);

            animateAksaraQuiz(false);

        } catch (IOException e) {
            Log.e("Askara", "There is an Error Getting" + nextAksaraImageName, e);
        }

        Collections.shuffle(allAksaraList);

        int correctAksaraNameIndex = allAksaraList.indexOf(correctAksaraAnswer);
        String correctAksaraName = allAksaraList.remove(correctAksaraNameIndex);
        allAksaraList.add(correctAksaraName);


        for (int row = 0; row < numberOfAksaraGuessRows; row++) {

            for (int column = 0; column < rowsOfGuessButtonsAksaraQuiz[row].getChildCount(); column++) {

                Button btnGuess = (Button) rowsOfGuessButtonsAksaraQuiz[row].getChildAt(column);
                btnGuess.setEnabled(true);

                String aksaraImageName = allAksaraList.get((row * 2) + column);
                btnGuess.setText(getTheExactAksaraName(aksaraImageName));

            }

        }

        int row = secureRandomNumber.nextInt(numberOfAksaraGuessRows);
        int column = secureRandomNumber.nextInt(2);
        LinearLayout randomRow = rowsOfGuessButtonsAksaraQuiz[row];
        String correctAksaraImageName = getTheExactAksaraName(correctAksaraAnswer);
        ((Button) randomRow.getChildAt(column)).setText(correctAksaraImageName);

    }


    public void modifyAksaraGuessRows(SharedPreferences sharedPreferences) {

        final String NUMBER_OF_GUESS_OPTIONS = sharedPreferences.getString(QuizActivity.GUESSES, null);

        numberOfAksaraGuessRows = Integer.parseInt(NUMBER_OF_GUESS_OPTIONS) / 2;

        for (LinearLayout horizontalLinearLayout : rowsOfGuessButtonsAksaraQuiz) {

            horizontalLinearLayout.setVisibility(View.GONE);

        }

        for (int row = 0; row < numberOfAksaraGuessRows; row++) {

            rowsOfGuessButtonsAksaraQuiz[row].setVisibility(View.VISIBLE);

        }

    }


    public void modifyTypeOfAksaraInQuiz(SharedPreferences sharedPreferences) {

        aksaraTypesInQuiz = sharedPreferences.getStringSet(QuizActivity.AKSARA_TYPE, null);

    }


    public void modifyQuizFont(SharedPreferences sharedPreferences) {

        String fontStringValue = sharedPreferences.getString(QuizActivity.QUIZ_FONT, null);

        switch (fontStringValue) {

            case "Chunkfive.otf":
                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setTypeface(QuizActivity.chunkfive);

                    }

                }

                break;
            case "FontleroyBrown.ttf":

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setTypeface(QuizActivity.fontlerybrown);

                    }

                }

                break;
            case "Wonderbar Demo.otf":

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setTypeface(QuizActivity.wonderbarDemo);

                    }

                }

                break;
        }

    }


    public void modifyBackgroundColor(SharedPreferences sharedPreferences) {

        String backgroundColor = sharedPreferences.getString(QuizActivity.QUIZ_BACKGROUND_COLOR, null);

        switch (backgroundColor) {

            case "White":

                aksaraQuizLinearLayout.setBackgroundColor(Color.WHITE);

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setBackgroundColor(Color.BLUE);
                        button.setTextColor(Color.WHITE);

                    }

                }

                txtAnswer.setTextColor(Color.BLUE);
                txtQuestionNumber.setTextColor(Color.BLACK);

                break;

            case "Black":

                aksaraQuizLinearLayout.setBackgroundColor(Color.BLACK);

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setBackgroundColor(Color.YELLOW);
                        button.setTextColor(Color.BLACK);

                    }

                }

                txtAnswer.setTextColor(Color.WHITE);
                txtQuestionNumber.setTextColor(Color.WHITE);

                break;

            case "Green":

                aksaraQuizLinearLayout.setBackgroundColor(Color.GREEN);

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setBackgroundColor(Color.BLUE);
                        button.setTextColor(Color.WHITE);

                    }

                }

                txtAnswer.setTextColor(Color.WHITE);
                txtQuestionNumber.setTextColor(Color.YELLOW);


                break;

            case "Blue":

                aksaraQuizLinearLayout.setBackgroundColor(Color.BLUE);

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setBackgroundColor(Color.RED);
                        button.setTextColor(Color.WHITE);

                    }

                }

                txtAnswer.setTextColor(Color.WHITE);
                txtQuestionNumber.setTextColor(Color.WHITE);

                break;

            case "Red":

                aksaraQuizLinearLayout.setBackgroundColor(Color.RED);

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setBackgroundColor(Color.BLUE);
                        button.setTextColor(Color.WHITE);

                    }

                }

                txtAnswer.setTextColor(Color.WHITE);
                txtQuestionNumber.setTextColor(Color.WHITE);


                break;

            case "Yellow":

                aksaraQuizLinearLayout.setBackgroundColor(Color.YELLOW);

                for (LinearLayout row : rowsOfGuessButtonsAksaraQuiz) {

                    for (int column = 0; column < row.getChildCount(); column++) {

                        Button button = (Button) row.getChildAt(column);
                        button.setBackgroundColor(Color.BLACK);
                        button.setTextColor(Color.WHITE);

                    }

                }

                txtAnswer.setTextColor(Color.BLACK);
                txtQuestionNumber.setTextColor(Color.BLACK);

                break;

        }

    }
}
