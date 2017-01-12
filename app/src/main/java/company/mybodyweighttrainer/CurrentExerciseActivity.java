package company.mybodyweighttrainer;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import Exercises.Exercise;
import Trainings.Program;
import Trainings.Program2;

public class CurrentExerciseActivity extends AppCompatActivity {

    private TextView mTimeRemaining;
    private TextView mExerciseName;
    private TextView mNumberReps;
    private TextView mNumberSetsRemaining;
    private Button mImDone;

    private CountDownTimer mTimer;
    private boolean mIsResting;

    private Program mProgram;
    private String mCurrentExerciseName;
    private int mCurrentRestingTime;

    public final static String TARGET_REPS_KEY =
        "company.mybodyweighttrainer.TARGET_REPS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_exercise);

        mTimeRemaining = (TextView)findViewById(R.id.text_time_remaining);
        mExerciseName = (TextView)findViewById(R.id.text_exercise_name);
        mNumberReps = (TextView)findViewById(R.id.text_number_reps_to_do);
        mNumberSetsRemaining = (TextView)findViewById(R.id.text_number_sets_remaining);
        mImDone = (Button)findViewById(R.id.button_im_done);

        mProgram = new Program2();
        refreshOnScreenInformation();
    }

    protected void onResume() {
        super.onResume();
        //Intent intent = getIntent();
        //String number_reps_done = intent.getStringExtra(NumberRepsInputActivity.NUMBER_REPS_DONE_KEY);
        String number_reps_done = "3";
        Context context = getApplicationContext();
        writeNumberReps(number_reps_done, context);
    }

    private void refreshOnScreenInformation() {
        mCurrentExerciseName = mProgram.getNextExercise().getName();
        mCurrentRestingTime = mProgram.getNextRestingTime();

        mTimeRemaining.setText(Integer.toString(mCurrentRestingTime));
        mExerciseName.setText(mCurrentExerciseName);
    }

    public void imDone(View view) {
        if(!mIsResting) {  // The button is useless if the user's resting.
            mProgram.performOneSet();
            refreshOnScreenInformation(); // Refreshes the time and exercise name.
            // Starting the timer (reminder: mCurrentRestingTime is in seconds).
            mTimer = new CountDownTimer(mCurrentRestingTime*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Long remaining_time = millisUntilFinished / 1000;
                    mTimeRemaining.setText(Long.valueOf(remaining_time).toString());
                }

                @Override
                public void onFinish() {
                    mTimeRemaining.setText("GO GO GO!");
                    mIsResting = false;
                }
            };
            mTimer.start();
            mIsResting = true;

            Intent intent = new Intent(this, NumberRepsInputActivity.class);
            String numberTargetReps = mNumberReps.getText().toString();
            intent.putExtra(TARGET_REPS_KEY, numberTargetReps);
            startActivity(intent);

            Long numberSetsRemaining = Long.parseLong(
                    mNumberSetsRemaining.getText().toString());
            numberSetsRemaining--;
            mNumberSetsRemaining.setText(numberSetsRemaining.toString());
        }
    }

    public void writeNumberReps(String numberReps, Context context) {
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(
                            context.openFileOutput("test.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(numberReps);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
