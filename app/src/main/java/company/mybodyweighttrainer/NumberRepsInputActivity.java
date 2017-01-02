package company.mybodyweighttrainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class NumberRepsInputActivity extends AppCompatActivity {

    private EditText mNumberRepsPerformed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_reps_input);

        mNumberRepsPerformed = (EditText)findViewById(R.id.text_number_reps_done);

        Intent intent = getIntent();
        String number_reps_target = intent.getStringExtra(CurrentExerciseActivity.TARGET_REPS_KEY);
        mNumberRepsPerformed.setText(number_reps_target);

        // Setting the cursor to the end of the line for faster entry.
        mNumberRepsPerformed.setSelection(mNumberRepsPerformed.getText().length());

        // Showing up the keyboard so the user can enter the number of reps.
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
