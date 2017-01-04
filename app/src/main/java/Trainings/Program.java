package Trainings;

import java.util.ArrayList;

import Exercises.ExerciseSet;

/**
 * Created by Romain on 04/01/2017.
 */

public abstract class Program {

    private ArrayList<ExerciseSet> mExerciseSets;
    private int mProgression;

    protected void initProgram(ArrayList<ExerciseSet> exerciseSets) {
        mExerciseSets = exerciseSets;
        mProgression = 0;
    }
}
