package company.mybodyweighttrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import Tools.DatabaseHandler;
import Tools.ExerciseSetSQLEntry;
import Trainings.Program;
import Trainings.Program2;

public class CurrentExerciseActivity extends AppCompatActivity {

    // UI variables.
    private TextView mTimeRemaining;
    private TextView mExerciseName;
    private TextView mNumberTargetReps;
    private TextView mNumberSetsRemaining;
    private Button mImDone;

    // Variables related to the timer.
    private CountDownTimer mTimer;
    private boolean mIsResting;

    // Variables related to the program and the exercises.
    private Program mProgram;
    //private ExerciseSet mCurrentExerciseSet;
    //private Exercise mCurrentExercise;
    //private int mCurrentRestingTime;

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
        mNumberTargetReps = (TextView)findViewById(R.id.text_number_reps_to_do);
        mNumberSetsRemaining = (TextView)findViewById(R.id.text_number_sets_remaining);
        mImDone = (Button)findViewById(R.id.button_im_done);

        // Initialisation the database.
        mPerformanceDatabase = new DatabaseHandler(this);

        // Initialising the program and the information on screen.
        mProgram = new Program2();
        refreshOnScreenInformation();
    }

    /**
     * - Catches the data that has been provided by the user during the "NumberRepsInput" activity,
     *   i.e. the number of repetition just performed, and add it to the Performance database.
     * - The next exercise and its characteristics are loaded and displayed on screen.
     * TODO: the logic is not good for me. The set should end when the User hits the "I'm done"
     * TODO: button, right?
     *
     * @param requestCode See parent Activity class.
     * @param resultCode See parent Activity class.
     * @param data See parent Activity class.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String number_reps_done = data.getStringExtra(NumberRepsInputActivity.NUMBER_REPS_DONE_KEY);
        writeNumberReps(number_reps_done);

        // Taking act that the user has performed a set.
        mProgram.performOneSet();  // Refreshes nProgram.nCurrentExerciseSet etc.
        refreshOnScreenInformation(); // Refreshes the time and exercise name.

        // Updating the "Number of remaining sets" information.
        // TODO: should be done within refreshOnScreenInformation() as well.
        Long numberSetsRemaining = Long.parseLong(
                mNumberSetsRemaining.getText().toString());
        numberSetsRemaining--;
        mNumberSetsRemaining.setText(numberSetsRemaining.toString());
    }

    /**
     * When the "I'm done' button is clicked, we need to update the information on screen, that is:
     * - the name of the next exercise
     * - the next rest time
     * This functions does that.
     */
    private void refreshOnScreenInformation() {
        mExerciseName.setText(mProgram.getCurrentExercise().getName());
        mTimeRemaining.setText(Integer.toString(
                mProgram.getCurrentExerciseSet().getCurrentRestingTime()));
    }

    /**
     * Rules the behavior of the "I'm done" button.
     * If hitted:
     * - the resting timer is toggled.
     * - the activity "NumberRepsInput" is launched so the user can indicate the number of reps
     *   he just has done.
     *
     * <p>Note that the current Set properly ends within the OnActivityResult() method, i.e.
     * when the User has entered its number of reps.
     *
     * @param view Application view. Is mandatory since this method is called via a UI button.
     */
    public void imDone(View view) {
        if(!mIsResting) {  // The button is useless if the user's resting.
            // Starting the timer (reminder: mCurrentRestingTime is in seconds).
            int currentRestingTime = mProgram.getCurrentExerciseSet().getCurrentRestingTime();
            mTimer = new CountDownTimer(currentRestingTime*1000, 1000) {
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
            String numberTargetReps = mNumberTargetReps.getText().toString();
            intent.putExtra(TARGET_REPS_KEY, numberTargetReps);
            startActivityForResult(intent, 1);
            // Will jump to onActivityResult(...) when the User validate its input.
        }
    }

    /**
     * Add the current performance to the Performance table in the database.
     * @param numberReps The number of repetition, as a String, the user has done during the last
     *                   set. Is obtained via the "NumberRepsInput" activity and the
     *                   "onActivityResult" method.
     */
    public void writeNumberReps(String numberReps) {
        ExerciseSetSQLEntry newEntry = new ExerciseSetSQLEntry(
                new Date(),  // date and time when the exercise was performed.
                mProgram.getmName(),  // name of the program.
                mProgram.getCurrentExerciseSet().getName(),  // name of just done exercise.
                mProgram.getCurrentExerciseSet().getProgression(),  // index of this Set.
                Integer.parseInt(numberReps),  // number of repetition that has just been done.
                mProgram.getPreviousRestingTime());  // the resting time the user had at the end
                                                     // of the last set.
        mPerformanceDatabase.addNewRecord(newEntry);
    }
}
