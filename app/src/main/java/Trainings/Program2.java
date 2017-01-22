package Trainings;

import java.util.ArrayList;
import java.util.Arrays;

import Exercises.A2;
import Exercises.A3;
import Exercises.B1;
import Exercises.C1;
import Exercises.E_L;
import Exercises.E_R;
import Exercises.F;
import Exercises.G;
import Exercises.H;
import Exercises.K2;
import Exercises.ExerciseSet;

/**
 * Created by Romain on 04/01/2017.
 */

public class Program2 extends Program {

    public Program2() {
        mName = "Second level";

        ExerciseSet exerciseSet1 = new ExerciseSet(
                new B1(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(5, 5, 5, 5, 5, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, 25)
                )
        );

        ExerciseSet exerciseSet2 = new ExerciseSet(
                new A3(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(5, 5, 5, 5, 5, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, 25)
                )
        );

        ExerciseSet exerciseSet3 = new ExerciseSet(
                new A2(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(5, 5, 5, 5, 5, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, 60*3)
                )
        );

        ExerciseSet exerciseSet4 = new ExerciseSet(
                new C1(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(5, 5, 5, 5, 5, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, 60*3)
                )
        );

         ExerciseSet exerciseSet5 = new ExerciseSet(
                 new E_L(), // exercise.
                 new ArrayList<Integer>( // number of target repetitions.
                         Arrays.asList(5, 5, 5, 5, 5, 5)
                 ),
                 new ArrayList<Integer>( // resting time after each set.
                         Arrays.asList(25, 25, 25, 25, 25, 60*2)
                 )
        );

        ExerciseSet exerciseSet6 = new ExerciseSet(
                new E_R(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(5, 5, 5, 5, 5, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, 60*3)
                )
        );

        ExerciseSet exerciseSet7 = new ExerciseSet(
                new F(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(5, 5, 5, 5)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 60*3)
                )
        );

        ExerciseSet exerciseSet8 = new ExerciseSet(
                new G(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(10, 10, 10, 10, 10, 10)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, (int)(60*1.5))
                )
        );

        ExerciseSet exerciseSet9 = new ExerciseSet(
                new H(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(1, 1, 1, 1, 1, 1)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(25, 25, 25, 25, 25, 60*1)
                )
        );

        ExerciseSet exerciseSet10 = new ExerciseSet(
                new K2(), // exercise.
                new ArrayList<Integer>( // number of target repetitions.
                        Arrays.asList(13, 13, 13)
                ),
                new ArrayList<Integer>( // resting time after each set.
                        Arrays.asList(60, 60, 60)
                )
        );

        ArrayList<ExerciseSet> exerciseSets = new ArrayList<ExerciseSet>(
                Arrays.asList(exerciseSet1,
                              exerciseSet2,
                              exerciseSet3,
                              exerciseSet4,
                              exerciseSet5,
                              exerciseSet6,
                              exerciseSet7,
                              exerciseSet8,
                              exerciseSet9,
                              exerciseSet10
                )
        );
        this.initProgram(exerciseSets);
    }
}
