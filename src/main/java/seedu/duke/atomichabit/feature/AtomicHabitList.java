package seedu.duke.atomichabit.feature;

import java.util.ArrayList;

public class AtomicHabitList {
    private ArrayList<AtomicHabit> allAtomicHabits;

    public AtomicHabitList() {
        allAtomicHabits = new ArrayList<>();
    }
    public void addAtomicHabit(AtomicHabit atomicHabit) {
        allAtomicHabits.add(atomicHabit);
    }
    public ArrayList<AtomicHabit> getAllHabits() {
        return allAtomicHabits;
    }
}

