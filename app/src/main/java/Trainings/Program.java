package Trainings;

import java.util.ArrayList;
import java.util.Map;

import Exercises.Exercise;
import Exercises.ExerciseSet;

/**
 * Created by Romain on 04/01/2017.
 *
 * A program is thought as a fixed collection of ExerciseSets (ex: 2 sets of dips, 3 sets of
 * pull-ups. In this case, it makes 2 ExerciseSets by 5 sets of exercise in total.).
 * TODO: find a better naming convention, this might be confusing.
 * A program is designed to be started an finished. A progression variable stores the current
 * Set which is being performed. A program ends when the progression variables reaches the end of
 * the number of Sets.
 *
 * The overall structure can be thought as:
 * Program > ExerciseSet > Exercise
 */

public abstract class Program {
    private ArrayList<ExerciseSet> mExerciseSets;
    private ExerciseSet mCurrentExerciseSet;
    private int mProgression; // By design, 0 <= mProgression < mExerciseSets.size()
    protected String mName; // Name of the program.

    /**
     * An auxilliary function to the constructor. Defines the Sets composing the Program, sets
     * the first exercise and the progression.
     * @param exerciseSets The list of Sets forming the Program.
     */
    protected void initProgram(ArrayList<ExerciseSet> exerciseSets) {
        mExerciseSets = exerciseSets;
        mProgression = 0;
        mCurrentExerciseSet = mExerciseSets.get(0);
    }

    /**
     * This method should be used when the user has completely finished an ExerciseSet (that is:
     * the user has done all the sets in the ExerciseSet). The next ExerciseSet is called and become
     * the "CurrentExerciseSet".
     */
    public void performOneSet() {
        mCurrentExerciseSet.performOneSet();
        if (mCurrentExerciseSet.isFinished()) {
            mCurrentExerciseSet = mExerciseSets.get(++mProgression);
        }
    }

    /**
     * @return True if the Program is over, False otherwise.
     */
    public boolean isFinished() {
        return mProgression == mExerciseSets.size();
    }

    public Exercise getNextExercise(){ return mCurrentExerciseSet.getExercise(); }

    public int getNextRestingTime(){ return mCurrentExerciseSet.getNextRestingTime(); }

    public String getmName(){ return mName; }
}