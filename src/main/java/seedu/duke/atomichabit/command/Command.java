package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.atomichabit.AtomicHabitList;

public abstract class Command {
    public abstract CommandResult execute(AtomicHabitList atomicHabitList);
}
