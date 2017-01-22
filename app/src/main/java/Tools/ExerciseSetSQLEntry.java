package Tools;

import java.util.Date;

/**
 * Created by root on 22/01/17.
 * Is used to add performance in an SQL table.
 */

public class ExerciseSetSQLEntry {

    private int mId;
    private Date mDate;
    private String mProgramName;
    private String mExerciseName;
    private int mExerciseSetNumber;
    private int mRepetitionNumber;
    private int mTimeRestedBefore;

    public ExerciseSetSQLEntry() {}

    public ExerciseSetSQLEntry(int mId, Date mDate, String mProgramName, String mExerciseName,
                               int mExerciseSetNumber, int mRepetitionNumber,
                               int mTimeRestedBefore) {
        // Default constructor.
        this.mId = mId;
        this.mDate = mDate;
        this.mProgramName = mProgramName;
        this.mExerciseName = mExerciseName;
        this.mExerciseSetNumber = mExerciseSetNumber;
        this.mRepetitionNumber = mRepetitionNumber;
        this.mTimeRestedBefore = mTimeRestedBefore;
    }

    public ExerciseSetSQLEntry(Date mDate, String mProgramName, String mExerciseName,
                               int mExerciseSetNumber, int mRepetitionNumber,
                               int mTimeRestedBefore) {
        // Same as default constructor, without the Id entry.
        this.mDate = mDate;
        this.mProgramName = mProgramName;
        this.mExerciseName = mExerciseName;
        this.mExerciseSetNumber = mExerciseSetNumber;
        this.mRepetitionNumber = mRepetitionNumber;
        this.mTimeRestedBefore = mTimeRestedBefore;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmProgramName() {
        return mProgramName;
    }

    public void setmProgramName(String mProgramName) {
        this.mProgramName = mProgramName;
    }

    public String getmExerciseName() {
        return mExerciseName;
    }

    public void setmExerciseName(String mExerciseName) {
        this.mExerciseName = mExerciseName;
    }

    public int getmExerciseSetNumber() {
        return mExerciseSetNumber;
    }

    public void setmExerciseSetNumber(int mExerciseSetNumber) {
        this.mExerciseSetNumber = mExerciseSetNumber;
    }

    public int getmRepetitionNumber() {
        return mRepetitionNumber;
    }

    public void setmRepetitionNumber(int mRepetitionNumber) {
        this.mRepetitionNumber = mRepetitionNumber;
    }

    public int getmTimeRestedBefore() {
        return mTimeRestedBefore;
    }

    public void setmTimeRestedBefore(int mTimeRestedBefore) {
        this.mTimeRestedBefore = mTimeRestedBefore;
    }
}