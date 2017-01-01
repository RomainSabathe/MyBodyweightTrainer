package company.mybodyweighttrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CurrentExerciseActivity extends AppCompatActivity {

    private TextView mTimeRemaining;
    private TextView mExerciseName;
    private TextView mNumberReps;
    private TextView mNumberSetsRemaining;
    private Button mImDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_exercise);

        mTimeRemaining = (TextView)findViewById(R.id.text_time_remaining);
        mExerciseName = (TextView)findViewById(R.id.text_exercise_name);
        mNumberReps = (TextView)findViewById(R.id.text_number_reps);
        mNumberSetsRemaining = (TextView)findViewById(R.id.text_number_sets_remaining);
        mImDone = (Button)findViewById(R.id.button_im_done);

        new CountDownTimer(90000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeRemaining.setText(Long.valueOf(millisUntilFinished / 1000).toString());
            }

            @Override
            public void onFinish() {
                mTimeRemaining.setText(Long.valueOf(90000 / 1000).toString());
            }
        }.start();
    }
}
