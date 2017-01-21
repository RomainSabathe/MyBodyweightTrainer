package company.mybodyweighttrainer;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String number_reps_done = data.getStringExtra(NumberRepsInputActivity.NUMBER_REPS_DONE_KEY);
        try {
            writeNumberReps(number_reps_done, getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            startActivityForResult(intent, 1);

            Long numberSetsRemaining = Long.parseLong(
                    mNumberSetsRemaining.getText().toString());
            numberSetsRemaining--;
            mNumberSetsRemaining.setText(numberSetsRemaining.toString());
        }
    }

    public void writeNumberReps(String numberReps, Context context) throws IOException {
        FileOutputStream fOut = openFileOutput("test",MODE_WORLD_READABLE);
        fOut.write(numberReps.getBytes());
        fOut.close();

        FileInputStream fIn = openFileInput("test");
        int c;
        String temp="";
        while( (c = fIn.read()) != -1){
            temp = temp + Character.toString((char)c);
        }

        Toast.makeText(getBaseContext(),"Saved: " + temp,Toast.LENGTH_SHORT).show();

        //FileOutputStream stream = new FileOutputStream(getRecordFile());
        //try {
        //    stream.write(new String(numberReps + "\n").getBytes());
        //} catch (IOException e) {
        //   Log.e("Exception", "File write failed: " + e.toString());
        //} finally {
        //    stream.close();
        //}
    }

    public File getRecordFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                   new String("MyBodyweightTrainer")), "test");
        //File file = new File(new String("SanDisk SD card/MyBodyweightTrainer/test.txt"));
        if (!file.mkdirs()) {
            Log.e("Exception", "Directory not created");
        }
        return file;
    }
}
