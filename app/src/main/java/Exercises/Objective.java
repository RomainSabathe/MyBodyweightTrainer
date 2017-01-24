package Exercises;

/**
 * Created by root on 24/01/17.
 * This class is used when defining a target number of repetitions for a set of an exercise.
 * It enables to bring more flexibility and generality than the basic 'perform this exercise
 * x times' at each training.
 * With an Objective, we can define a range of target parameters and make this target adjustable
 * based on the past performance of the user. For instance, if last time the User has performed
 * the exercise x times, it will be targeted to do it x+1 times.
 *
 * Note that the usage of this class is simplified by the Builder Design Pattern.
 * More info can be found at:
 * http://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295
 * and using the doc of the `ObjectiveBuilder` class.
 */

public class Objective {
    /**
     * If the User has never done this exercise, this number will be taken as the first target.
     */
    private int mTargetRepsMin;
    /**
     * If the User has already done mTargetRepsMax or more in a previous session, max out the target
     * to mTargetRepsMax.
     */
    private int mTargetRepsMax;
    /**
     * Is true if the objective should be based on the previous performance of the User. If not,
     * the target is taken as mTargetRepsMax.
     */
    private boolean mBasedOnPreviousPerformance;
    /**
     * In the case where the target should depend on previous performance of the User, this variable
     * defines the increment for the next target.
     * Exemple: the previous target was 5 and the User has done 5 reps. If mIncrementTargetReps == 2
     * then the next time the User will attempt the exercise, the target number of rep will be 7.
     */
    private int mIncrementTargetReps;

    public Objective(int mTargetRepsMin, int mTargetRepsMax, boolean mBasedOnPreviousPerformance,
                     int mIncrementTargetReps) {
        this.mTargetRepsMin = mTargetRepsMin;
        this.mTargetRepsMax = mTargetRepsMax;
        this.mBasedOnPreviousPerformance = mBasedOnPreviousPerformance;
        this.mIncrementTargetReps = mIncrementTargetReps;
    }

    public int getmTargetRepsMin() {
        return mTargetRepsMin;
    }

    public int getmTargetRepsMax() {
        return mTargetRepsMax;
    }

    public boolean ismBasedOnPreviousPerformance() {
        return mBasedOnPreviousPerformance;
    }

    public int getmIncrementTargetReps() {
        return mIncrementTargetReps;
    }
}
