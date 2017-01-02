package company.mybodyweighttrainer;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class CurrentExerciseActivity extends AppCompatActivity {

    private TextView mTimeRemaining;
    private TextView mExerciseName;
    private TextView mNumberReps;
    private TextView mNumberSetsRemaining;
    private Button mImDone;

    private CountDownTimer mTimer;
    private boolean mIsResting;

    public final static String TARGET_REPS_KEY = "company.mybodyweighttrainer.TARGET_REPS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_exercise);

        mTimeRemaining = (TextView)findViewById(R.id.text_time_remaining);
        mExerciseName = (TextView)findViewById(R.id.text_exercise_name);
        mNumberReps = (TextView)findViewById(R.id.text_number_reps_to_do);
        mNumberSetsRemaining = (TextView)findViewById(R.id.text_number_sets_remaining);
        mImDone = (Button)findViewById(R.id.button_im_done);
    }

    public void imDone(View view) {
        int rest_time = 10000;
        if(!mIsResting) {
            // Starting the timer.
            mTimer = new CountDownTimer(rest_time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Long remaining_time = millisUntilFinished / 1000;
                    mTimeRemaining.setText(Long.valueOf(remaining_time).toString());
                }

                @Override
                public void onFinish() {
                    mTimeRemaining.setText("-");
                    mIsResting = false;
                }
            };
            mTimer.start();
            mIsResting = true;

            Intent intent = new Intent(this, NumberRepsInputActivity.class);
            String numberTargetReps = mNumberReps.getText().toString();
            intent.putExtra(TARGET_REPS_KEY, numberTargetReps);
            startActivity(intent);
        }
    }
}
