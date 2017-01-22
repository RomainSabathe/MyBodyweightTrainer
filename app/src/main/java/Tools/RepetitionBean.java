package Tools;

import java.util.Date;

/**
 * Created by root on 22/01/17.
 */

public class RepetitionBean implements java.io.Serializable {
    // Is used with Super CSV to write the current information
    // (date, time, number of reps etc.) on file.

    private Date date;
    private String programName;
    private String exerciseName;
    private int exerciseSetNumber;
    private int repetitionNumber;
    private int timeRestedBefore;

    public RepetitionBean(Date date, String programName, String exerciseName,
                           int exerciseSetNumber, int repetitionNumber, int timeRestedBefore) {
        this.date = date;
        this.programName = programName;
        this.exerciseName = exerciseName;
        this.exerciseSetNumber = exerciseSetNumber;
        this.repetitionNumber = repetitionNumber;
        this.timeRestedBefore = timeRestedBefore;
    }

    public Date getDate() {
        return date;
    }

    public String getProgramName() {
        return programName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getExerciseSetNumber() {
        return exerciseSetNumber;
    }

    public int getRepetitionNumber() {
        return repetitionNumber;
    }

    public int getTimeRestedBefore() {
        return timeRestedBefore;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseSetNumber(int exerciseSetNumber) {
        this.exerciseSetNumber = exerciseSetNumber;
    }

    public void setRepetitionNumber(int repetitionNumber) {
        this.repetitionNumber = repetitionNumber;
    }

    public void setTimeRestedBefore(int timeRestedBefore) {
        this.timeRestedBefore = timeRestedBefore;
    }
}