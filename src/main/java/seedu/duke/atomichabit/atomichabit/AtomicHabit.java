package seedu.duke.atomichabit.atomichabit;

public class AtomicHabit {
    protected String description;

    protected int count;

    public AtomicHabit(String description) {
        this.description = description;
        count = 0;
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
