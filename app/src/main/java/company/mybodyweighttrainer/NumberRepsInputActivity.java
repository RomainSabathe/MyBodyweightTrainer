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

public class NumberRepsInputActivity extends AppCompatActivity {

    private EditText mNumberRepsPerformed;
    public final static String NUMBER_REPS_DONE_KEY =
        "company.mybodyweighttrainer.NUMBER_REPS_DONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_reps_input);

        mNumberRepsPerformed = (EditText)findViewById(R.id.text_number_reps_done);

        Intent intent = getIntent();
        String number_reps_target = intent.getStringExtra(
                CurrentExerciseActivity.TARGET_REPS_KEY);
        mNumberRepsPerformed.setText(number_reps_target);

        // Setting the cursor to the end of the line for faster entry.
        mNumberRepsPerformed.setSelection(mNumberRepsPerformed.getText().length());

        // Showing up the keyboard so the user can enter the number of reps.
        final InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        mNumberRepsPerformed.setOnEditorActionListener(
                new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE |
                    actionId == KeyEvent.KEYCODE_BACK) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);                  
                    validateEntry();
                    return true;
                }
                return false;
            }
        });
    }

    public void validateEntry() {
        Intent returnIntent = new Intent();
        String numberRepsDone = mNumberRepsPerformed.getText().toString();
        returnIntent.putExtra(NUMBER_REPS_DONE_KEY, numberRepsDone);
        setResult(RESULT_OK, returnIntent);

        // Displaying a little motivation message.
        Context context = getApplicationContext();
        CharSequence text = "Congrats! Keep up man!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }
}
