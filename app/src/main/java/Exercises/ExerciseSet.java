package Exercises;

import java.util.ArrayList;

import Exercises.Exercise;

/**
 * Created by Romain on 04/01/2017.
 */

public class ExerciseSet {

    private Exercise mExercise;
    private int mNumberSets;
    private ArrayList<Integer> mRestingTimes;

    public ExerciseSet(Exercise exercise, ArrayList<Integer> restingTimes) {
        mExercise = exercise;
        mRestingTimes = restingTimes;
        mNumberSets = restingTimes.size();
    }

    public int getmNumberSets() {
        return mNumberSets;
    }
}
