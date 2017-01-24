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

    public Objective buildObjective() {
        return new Objective(_mTargetRepsMin, _mTargetRepsMax, _mBasedOnPreviousPerformance,
                             _mIncrementTargetReps);
    }

    public ObjectiveBuilder min(int targetRepsMin) {
        this._mTargetRepsMin = targetRepsMin;
        return this;
    }

    public ObjectiveBuilder max(int targetRepsMax) {
        this._mTargetRepsMax = targetRepsMax;
        return this;
    }

    /**
     * Use this constructor when there is not a range of targets but rather only one target.
     * @param targetReps The repetition target to be applied.
     */
    public ObjectiveBuilder nbReps(int targetReps) {
        this._mTargetRepsMin = targetReps;
        this._mTargetRepsMax = targetReps;
        return this;
    }

    public ObjectiveBuilder progressive(boolean basedOnPreviousPerformance) {
        this._mBasedOnPreviousPerformance = basedOnPreviousPerformance;
        return this;
    }

    public ObjectiveBuilder increment(int incrementTargetRep) {
        this._mIncrementTargetReps = incrementTargetRep;
        return this;
    }
}
