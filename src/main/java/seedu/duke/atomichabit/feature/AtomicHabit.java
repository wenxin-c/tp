package seedu.duke.atomichabit.feature;

public class AtomicHabit {
    private String description;
    private int count;

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

    @Override
    public String toString() {
        return getDescription();
    }
}

