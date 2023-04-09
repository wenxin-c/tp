package wellnus.atomichabit.feature;

/**
 * Class to represent a unique atomic habit that the user will practice
 * It contains primarily the description of the habit and the count of the habit
 */
public class AtomicHabit {
    private final String description;
    private int count;

    /**
     * Constructor of atomic habit class
     * Will initialise private description to the input parameter
     * Assigns count to 1 when a new habit is added
     *
     * @param description Description of this new atomic habit provided by the user
     */
    public AtomicHabit(String description) {
        this.description = description;
        this.count = 0;
    }

    /**
     * Constructor of atomic habit class.
     * Will initialise private description and count to the input parameter.
     *
     * @param description Description of atomic habit.
     * @param count       Number of habit to be initialized.
     */
    public AtomicHabit(String description, int count) {
        this.description = description;
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount(int increment) {
        count += increment;
    }

    public void decreaseCount(int decrement) {
        count -= decrement;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}

