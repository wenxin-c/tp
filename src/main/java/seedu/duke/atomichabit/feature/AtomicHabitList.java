package seedu.duke.atomichabit.feature;

import java.util.ArrayList;

public class AtomicHabitList {
    private ArrayList<AtomicHabit> allAtomicHabits;

    public void addAtomicHabit(AtomicHabit atomicHabit) {
        allAtomicHabits.add(atomicHabit);
    }

    public AtomicHabitList() {
        allAtomicHabits = new ArrayList<>();
    }

    public ArrayList<AtomicHabit> getAllHabits() {
        return allAtomicHabits;
    }
}

