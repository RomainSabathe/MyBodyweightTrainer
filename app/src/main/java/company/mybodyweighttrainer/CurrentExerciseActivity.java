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
import android.widget.Toast;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Tools.RepetitionBean;
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
        final RepetitionBean bean_temp = new RepetitionBean(new Date(), new String("My Program"),
                              new String("Dips"),
                             1, Integer.parseInt(numberReps), 30);
        final List<RepetitionBean> allRepetitions = Arrays.asList(bean_temp);

        ICsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter("test.txt")),
                    CsvPreference.STANDARD_PREFERENCE);

            final String[] header = new String[] {"date", "programName", "exerciseName",
                "exerciseSetNumber", "repetitionNumber", "timeRestedBefore"};
            final CellProcessor[] processors = getProcessors();

            // Write the header.
            beanWriter.writeHeader(header);
            // Write the beans.
            for (final RepetitionBean bean : allRepetitions) {
                beanWriter.write(bean, header, processors);
            }
        }
        finally {
            if(beanWriter != null) {
                beanWriter.close();
            }
        }

        FileInputStream fIn = openFileInput("test.txt");
        int c;
        String temp="";
        while( (c = fIn.read()) != -1){
            temp = temp + Character.toString((char)c);
        }

        Toast.makeText(getBaseContext(),"Saved: " + temp,Toast.LENGTH_SHORT).show();
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
