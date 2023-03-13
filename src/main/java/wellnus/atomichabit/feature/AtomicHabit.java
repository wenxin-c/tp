package wellnus.atomichabit.feature;

public class AtomicHabit {
    private String description;
    private int count;

    /**
     * Constructor of atomic habit class
     * Will initialise private description to the input parameter
     * Assigns count to 1 when a new habit is added
     *
     * @param description
     */
    public AtomicHabit(String description) {
        this.description = description;
        this.count = 1;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int increment){
        count += increment;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}

