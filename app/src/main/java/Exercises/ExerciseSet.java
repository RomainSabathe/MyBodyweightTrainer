package Exercises;

import java.util.ArrayList;

import Exercises.Exercise;

/**
 * Created by Romain on 04/01/2017.
 *
 * An ExerciseSet is a specific number of sets of a same Exercise. Each 'set' is made of:
 * - a specific number of (target) repetition of this exercise.
 * - a resting time to be taken once all the repetitions are done.
 *
 * <p>Similarly to a Program, an ExerciseSet is designed to be started and completed. It ends when
 * all the sets have been performed.
 */

public class ExerciseSet {
    private Exercise mExercise;
    private ArrayList<Integer> mNumberTargetReps; // the list of target repetition, one value per set.
    private ArrayList<Integer> mRestingTimes; // the list of resting time after each set.
    private int mNumberSets;
    private int mProgression; // Tracks the progression of this set.
    private boolean mIsFinished;
    private String mName; // Name of the exercise set, it actually the name of the mExercise.

    /**
     * Default constructor.
     * @param exercise The exercise that will be performed all along this ExerciseSet.
     * @param numberTargetReps An array which length match the number of sets composing this
     *                     ExerciseSet. Gives the number of repetition that should ideally be
     *                     performed during each.
     * @param restingTimes An array which length match the number of sets composing this
     *                     ExerciseSet. The values should be given in seconds and represent the
     *                     resting time after performing each set.
     */
    public ExerciseSet(Exercise exercise, ArrayList<Integer> numberTargetReps,
                       ArrayList<Integer> restingTimes) {
        mExercise = exercise;
        mNumberTargetReps = numberTargetReps;
        mRestingTimes = restingTimes;
        mNumberSets = restingTimes.size();
        mProgression = 0;
        mIsFinished = false;
        mName = mExercise.getName();

        // Standard verifications: if the size of the components of the set do not match, we
        // can't continue.
        if(mNumberTargetReps.size() != mRestingTimes.size()) {
            throw new IllegalStateException();
        }
    }

    /**
     * This method should be used when the user has finished a set (that is:
     * the user has done all the repetitions of the set, or at least as much as he/she could).
     * The "mProgression" index increases.
     */
    public void performOneSet() {
        mProgression += 1;
        if (mProgression == mNumberSets) {
            mIsFinished = true;
        }
    }
    public int getNumberSets() {
        return mNumberSets;
    }

    public boolean isFinished() {
        return mIsFinished;
    }

    public int getCurrentRestingTime() {
        return mRestingTimes.get(mProgression);
    }
    public int getCurrentNumberTargetReps() { return mNumberTargetReps.get(mProgression); }
    public int getProgression() { return mProgression; }
    public ArrayList<Integer> getRestingTimes() { return mRestingTimes; }
    public ArrayList<Integer> getmNumberTargetReps() { return mNumberTargetReps; }
    public String getName() { return mName; }
    public Exercise getExercise() { return mExercise;
    }

}
