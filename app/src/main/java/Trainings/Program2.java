package Trainings;

import java.util.ArrayList;
import java.util.Arrays;

import Exercises.Dips;
import Exercises.ExerciseSet;
import Exercises.PullUp;

/**
 * Created by Romain on 04/01/2017.
 */

public class Program2 extends Program {

    public Program2() {
        mName = "Program 2";

        ExerciseSet exerciseSet1 = new ExerciseSet(
                new Dips(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(8, 7, 6, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(5, 10, 15, 20)
                )
        );

        ExerciseSet exerciseSet2 = new ExerciseSet(
                new PullUp(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(10, 9, 13, 11)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(5, 10, 15, 20)
                )
        );

        ArrayList<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>(
                Arrays.asList(exerciseSet1, exerciseSet2)
        );
        this.initProgram(exerciseSets);
    }
}
