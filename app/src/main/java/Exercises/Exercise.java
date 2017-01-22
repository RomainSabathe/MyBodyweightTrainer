package Exercises;

/**
 * Created by Romain on 04/01/2017.
 *
 * An exercise is a general entity. It has a name and correspond to a certain movement that should
 * be performed.
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
