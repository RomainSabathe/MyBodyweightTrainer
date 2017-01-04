package Trainings;

import java.util.ArrayList;
import java.util.Map;

import Exercises.Exercise;
import Exercises.ExerciseSet;

/**
 * Created by Romain on 04/01/2017.
 */

public abstract class Program {

    private ArrayList<ExerciseSet> mExerciseSets;
    private ExerciseSet mCurrentExerciseSet;
    private int mProgression;

    protected void initProgram(ArrayList<ExerciseSet> exerciseSets) {
        mExerciseSets = exerciseSets;
        mProgression = 0;
        mCurrentExerciseSet = mExerciseSets.get(0);
    }

    public Exercise getNextExercise() {
        return mCurrentExerciseSet.getExercise();
    }

    public int getNextRestingTime() {
        return mCurrentExerciseSet.getNextRestingTime();
    }

    public void performOneSet() {
        mCurrentExerciseSet.performOneSet();
        if (mCurrentExerciseSet.isFinished()) {
            mCurrentExerciseSet = mExerciseSets.get(++mProgression);
        }
    }

    public boolean isFinished() {
        return mProgression == mExerciseSets.size();
    }
}
