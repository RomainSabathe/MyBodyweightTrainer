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
        ExerciseSet exerciseSet1 = new ExerciseSet(
                new Dips(),
                new ArrayList<Integer>(
                        Arrays.asList(5, 10, 15, 60)
                )
        );

        ExerciseSet exerciseSet2 = new ExerciseSet(
                new PullUp(),
                new ArrayList<Integer>(
                        Arrays.asList(5, 10, 15, 60)
                )
        );

        ArrayList<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>(
                Arrays.asList(exerciseSet1, exerciseSet2)
        );
        this.initProgram(exerciseSets);
    }
}
