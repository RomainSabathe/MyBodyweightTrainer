package Exercises;

/**
 * Created by root on 24/01/17.
 * Builder of the Objective class.
 * The parameters `mBasedOnPreviousPerformance` and `mIncrementTargetReps` have default values.
 *
 * More info on the Builder Design Pattern can be found at:
 * http://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295
 */

public class ObjectiveBuilder {
    private int _mTargetRepsMin;
    private int _mTargetRepsMax;
    private boolean _mBasedOnPreviousPerformance = true;
    private int _mIncrementTargetReps = 1;

    public ObjectiveBuilder() { }

    public Objective buildObjective(int targetRepsMin, int targetRepsMax) {
        return new Objective(targetRepsMin, targetRepsMax, _mBasedOnPreviousPerformance,
                             _mIncrementTargetReps);
    }

    /**
     * Use this constructor when there is not a range of targets but rather only one target.
     * @param targetReps The repetition target to be applied.
     */
    public Objective buildObjective(int targetReps) {
        return new Objective(targetReps, targetReps, _mBasedOnPreviousPerformance,
                _mIncrementTargetReps);
    }

    /**
     * Use this constructor when there is not a range of targets but rather only one target,
     * the `BasedOnPreviousPerformance` can be set as well.
     * @param targetReps The repetition target to be applied.
     */
    public Objective buildObjective(int targetReps, boolean basedOnPreviousPerformance) {
        return new Objective(targetReps, targetReps, basedOnPreviousPerformance,
                _mIncrementTargetReps);
    }
}
