package company.mybodyweighttrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import Tools.DatabaseHandler;
import Tools.ExerciseSetSQLEntry;
import Trainings.Program;
import Trainings.Program2;

public class CurrentExerciseActivity extends AppCompatActivity {

    // UI variables.
    private TextView mTimeRemaining;
    private TextView mExerciseName;
    private TextView mNumberReps;
    private TextView mNumberSetsRemaining;
    private Button mImDone;

    // Variables related to the timer.
    private CountDownTimer mTimer;
    private boolean mIsResting;

    // Variables related to the program and the exercises.
    private Program mProgram;
    private String mCurrentExerciseName;
    private int mCurrentRestingTime;

    // Variables related to the database.
    private DatabaseHandler mPerformanceDatabase;

    // Keys for transfering data through activities.
    public final static String TARGET_REPS_KEY =
        "company.mybodyweighttrainer.TARGET_REPS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_exercise);

        // Linking UI elements with code variables.
        mTimeRemaining = (TextView)findViewById(R.id.text_time_remaining);
        mExerciseName = (TextView)findViewById(R.id.text_exercise_name);
        mNumberReps = (TextView)findViewById(R.id.text_number_reps_to_do);
        mNumberSetsRemaining = (TextView)findViewById(R.id.text_number_sets_remaining);
        mImDone = (Button)findViewById(R.id.button_im_done);

        // Initialisation the database.
        mPerformanceDatabase = new DatabaseHandler(this);

        // Initialising the program and the information on screen.
        mProgram = new Program2();
        refreshOnScreenInformation();
    }

    /**
     * Catches the data that has been provided by the user during the "NumberRepsInput" activity,
     * i.e. the number of repetition just performed, and add it to the Performance database.
     * @param requestCode See parent Activity class.
     * @param resultCode See parent Activity class.
     * @param data See parent Activity class.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String number_reps_done = data.getStringExtra(NumberRepsInputActivity.NUMBER_REPS_DONE_KEY);
        writeNumberReps(number_reps_done);
    }

    /**
     * When the "I'm done' button is clicked, we need to update the information on screen, that is:
     * - the name of the next exercise
     * - the next rest time
     * This functions does that.
     */
    private void refreshOnScreenInformation() {
        mCurrentExerciseName = mProgram.getNextExercise().getName();
        mCurrentRestingTime = mProgram.getNextRestingTime();

        mTimeRemaining.setText(Integer.toString(mCurrentRestingTime));
        mExerciseName.setText(mCurrentExerciseName);
    }

    /**
     * Rules the behavior of the "I'm done" button.
     * If hitted:
     * - the resting timer is toggled.
     * - the activity "NumberRepsInput" is launched so the user can indicate the number of reps
     *   he just has done.
     * - the next exercise and its characteristics are loaded and displayed on screen.
     *
     * @param view Application view. Is mandatory since this method is called via a UI button.
     */
    public void imDone(View view) {
        if(!mIsResting) {  // The button is useless if the user's resting.
            // Taking act that the user has performed a set.
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
            mIsResting = true; // Guard to lock the button behavior during resting.

            // Stating the "NumberRepsInput" activity as an 'ActivityForResult'.
            Intent intent = new Intent(this, NumberRepsInputActivity.class);
            String numberTargetReps = mNumberReps.getText().toString();
            intent.putExtra(TARGET_REPS_KEY, numberTargetReps);
            startActivityForResult(intent, 1);

            // Updating the "Number of remaining sets" information.
            Long numberSetsRemaining = Long.parseLong(
                    mNumberSetsRemaining.getText().toString());
            numberSetsRemaining--;
            mNumberSetsRemaining.setText(numberSetsRemaining.toString());
        }
    }

    /**
     * Add the current performance to the Performance table in the database.
     * @param numberReps The number of repetition, as a String, the user has done during the last
     *                   set. Is obtained via the "NumberRepsInput" activity and the "onResult"
     *                   method.
     */
    public void writeNumberReps(String numberReps) {
        ExerciseSetSQLEntry newEntry = new ExerciseSetSQLEntry(
                new Date(),  // date and time when the exercise was performed.
                mProgram.getmName(),  // name of the program.
                mCurrentExerciseName,  // name of the exercise that has just been done.
                1,  // number of the set within the exercise.
                Integer.parseInt(numberReps),  // number of repetition that has just been done.
                mCurrentRestingTime);  // the resting time the user had, before performing the set.
        mPerformanceDatabase.addNewRecord(newEntry);
    }
}
