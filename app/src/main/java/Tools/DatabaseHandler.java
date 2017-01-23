package Tools;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;

import Trainings.Program;

/**
 * Created by root on 22/01/17.
 */

public class DatabaseHandler  extends SQLiteOpenHelper {
    // Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "performance";

    // Table-related information.
    private static final String TABLE_PERFORMANCE_RECORD = "performanceRecord";

    // Keys in the table.
    private static final  String KEY_ID = "id";
    private static final  String KEY_DATE = "date";
    private static final  String KEY_PROGRAM_NAME = "program_name";
    private static final  String KEY_EXERCISE_NAME = "exercise_name";
    private static final  String KEY_EXERCISESET_NUMBER = "exerciseset_number";
    private static final  String KEY_REPETITION_NUMBER = "repetition_number";
    private static final  String KEY_TIME_RESTED_BEFORE = "time_rested_before";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERFORMANCE_RECORD_TABLE = "CREATE TABLE " + TABLE_PERFORMANCE_RECORD + "(" +
                KEY_ID + " INTEGER PRIMARY KEY,"  +
                KEY_DATE + " DATE," +
                KEY_PROGRAM_NAME + " TEXT," +
                KEY_EXERCISE_NAME + " TEXT," +
                KEY_EXERCISESET_NUMBER + " TEXT," +
                KEY_REPETITION_NUMBER + " INTEGER," +
                KEY_TIME_RESTED_BEFORE + " INTEGER" + ")";

        db.execSQL(CREATE_PERFORMANCE_RECORD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERFORMANCE_RECORD);

        // Create tables again.
        onCreate(db);
    }

    public void addNewRecord(ExerciseSetSQLEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DATE, String.valueOf(entry.getmDate()));
        values.put(KEY_PROGRAM_NAME, entry.getmProgramName());
        values.put(KEY_EXERCISE_NAME, entry.getmExerciseName());
        values.put(KEY_EXERCISESET_NUMBER, entry.getmExerciseSetNumber());
        values.put(KEY_REPETITION_NUMBER, entry.getmRepetitionNumber());
        values.put(KEY_TIME_RESTED_BEFORE, entry.getmTimeRestedBefore());

        db.insert(TABLE_PERFORMANCE_RECORD, null, values);
        db.close();
    }

    public int getLastRecordedNumberRep(Program programBeingPerformed) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Getting the variables for performing the filtering.
        String programName = programBeingPerformed.getmName();
        String exerciseName = programBeingPerformed.getCurrentExercise().getName();
        int exerciseSetNumber = programBeingPerformed.getCurrentExerciseSet().getProgression();

        // Building the query.
        String query = "Select " + KEY_REPETITION_NUMBER + " FROM " + TABLE_PERFORMANCE_RECORD +
            " WHERE " +
                KEY_PROGRAM_NAME + "='" + programName + "' AND " +
                KEY_EXERCISE_NAME + "='" + exerciseName + "' AND " +
                KEY_EXERCISESET_NUMBER + "=" + exerciseSetNumber +
            " ORDER BY ID DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        int result = -1;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            result = cursor.getInt(0);
        }
        // Otherwise the user has never done this exercise before and the `result` var. is valid.

        return result;
    }
}
