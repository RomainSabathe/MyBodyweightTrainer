package Trainings;

import java.util.ArrayList;
import java.util.Map;

import Exercises.Exercise;
import Exercises.ExerciseSet;

/**
 * Created by Romain on 04/01/2017.
 *
 * If bugs or issues: start reading the doc of the initProgram(...) method.
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
     * WARNING: *all* inherited classes of Program should call this method last in their
     * constructor!
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

    public Exercise getCurrentExercise(){ return mCurrentExerciseSet.getExercise(); }

    public int getCurrentRestingTime(){ return mCurrentExerciseSet.getCurrentRestingTime(); }

    public ExerciseSet getCurrentExerciseSet(){ return mCurrentExerciseSet; }

    public String getmName(){ return mName; }

    /**
     * When logging some data in the database, we need to now for how long the User has rest.
     * This information is not directly accessible from the activity so we make it more easy
     * using this function.
     * @return The resting time of the previous exercise set. If this is the very first exercise
     *         set performed during this program, returns Infinity;
     */
    public int getPreviousRestingTime() {
        // Handling the tricky cases.
        // First, when there is no proper previous resting time.
        if(mProgression == 0 && mCurrentExerciseSet.getProgression() == 0) {
            return (int) Double.POSITIVE_INFINITY;
        }
        // Second, when the User has just started a new ExerciseSet.
        if(mCurrentExerciseSet.getProgression() == 0) {
            // The previous resting time was actually the last resting time of the previous
            // ExerciseSet. Getting all the resting times from this Set and getting the last one
            // of them.
            ArrayList<Integer> previousRestingTimes =
                    mExerciseSets.get(mProgression - 1).getRestingTimes();
            return previousRestingTimes.get(previousRestingTimes.size() - 1);
        }

        // In all other cases, it's fairly simple.
        int progressionInCurrentExerciseSet = mCurrentExerciseSet.getProgression();
        return mCurrentExerciseSet.getRestingTimes().get(progressionInCurrentExerciseSet - 1);
    }
}