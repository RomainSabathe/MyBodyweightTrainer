package Exercises;

/**
 * Created by Romain on 04/01/2017.
 */

public abstract class Exercise {

    private final String mName;

    public Exercise(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
