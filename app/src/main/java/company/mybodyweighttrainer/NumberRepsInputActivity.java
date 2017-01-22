package company.mybodyweighttrainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * In this activity, the User is asked to indicate the number of repetition he has just performed
 * over the last set.
 * The result is then sent back (as a String) to the calling activity.
 */

public class NumberRepsInputActivity extends AppCompatActivity {
    private EditText mNumberRepsPerformed;
    private String mNumberTargetReps; // The ideal number of repetitions that should have been done.
                                      // Is used to customize the Toast message.
    public final static String NUMBER_REPS_DONE_KEY =
        "company.mybodyweighttrainer.NUMBER_REPS_DONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_reps_input);

        // Linking UI elements with code variables.
        mNumberRepsPerformed = (EditText)findViewById(R.id.text_number_reps_done);

        // Catching the target number of repetition. Ideally, if the user has fully and
        // successfully completed the exercise, he/she should have done that many number of reps.
        // This enables to gain speed since, if it's the case, all the user has to do is to
        // validate the number without even having to type anything.
        Intent intent = getIntent();
        // TODO: fix the inconsistency in the variable naming (NumberTargetREPS, NumberREPSPerfo..)
        mNumberTargetReps = intent.getStringExtra(CurrentExerciseActivity.TARGET_REPS_KEY);
        mNumberRepsPerformed.setText(mNumberTargetReps);

        // Setting the cursor to the end of the line for faster entry.
        mNumberRepsPerformed.setSelection(mNumberRepsPerformed.getText().length());

        // Showing up the keyboard so the user can enter the number of reps.
        final InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        // Tracking the keyboard activity.
        // If the "Enter" button is pushed or the "Previous" button, the User is sent back to the
        // CurrentExercise activity and the result of his/her entry is sent back to this activity.
        mNumberRepsPerformed.setOnEditorActionListener(
                new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE |
                    actionId == KeyEvent.KEYCODE_BACK) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0); // Hiding the keyboard.
                    validateEntry(); // Getting the User's entry and switching back to previous act.
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Catches the number of repetitions entered by the user and sends it back to the calling
     * activity (CurrentExercise).
     */
    public void validateEntry() {
        // Creating the Intent and linking to it the number of repetition.
        Intent returnIntent = new Intent();
        String numberRepsDone = mNumberRepsPerformed.getText().toString();
        returnIntent.putExtra(NUMBER_REPS_DONE_KEY, numberRepsDone);
        setResult(RESULT_OK, returnIntent);

        // Displaying a little motivation message. Its content will be based on the performance
        // of the user compared to the target.
        Context context = getApplicationContext();
        CharSequence text;
        if(Integer.parseInt(numberRepsDone) < Integer.parseInt(mNumberTargetReps)) {
            text = "It's alright! Don't loose focus! Keep going!";
        } else if(Integer.parseInt(numberRepsDone) == Integer.parseInt(mNumberTargetReps)) {
            text = "That's impressive! You're on right track, keep pushing!";
        } else{
            text = "My GOD you're on steroid! I love it! Destroy EVERRYYYYTHIIINNNGG!!!";
        }
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }
}
