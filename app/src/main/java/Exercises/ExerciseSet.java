package Exercises;

import java.util.ArrayList;

import Exercises.Exercise;

/**
 * Created by Romain on 04/01/2017.
 */

public class ExerciseSet {

    private Exercise mExercise;
    private int mNumberSets;
    private int mProgression;
    private boolean mIsFinished;
    private ArrayList<Integer> mRestingTimes;

    public ExerciseSet(Exercise exercise, ArrayList<Integer> restingTimes) {
        mExercise = exercise;
        mRestingTimes = restingTimes;
        mNumberSets = restingTimes.size();
        mProgression = 0;
        mIsFinished = false;
    }

    public int getNumberSets() {
        return mNumberSets;
    }

    public Exercise getExercise() {
        return mExercise;
    }

    public boolean isFinished() {
        return mIsFinished;
    }

    public int getNextRestingTime() {
        return mRestingTimes.get(mProgression);
    }

    public void performOneSet() {
        mProgression += 1;
        if (mProgression == mNumberSets) {
            mIsFinished = true;
        }
    }
}
