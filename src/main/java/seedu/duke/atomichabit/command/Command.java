package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.atomichabit.AtomicHabitList;

public abstract class Command {
    /**
     * Method that will be overridden by subclasses which will properly execute the specific commands
     * @param atomicHabitList
     * @return CommandResult which contains information to be printed to user
     */
    public abstract CommandResult execute(AtomicHabitList atomicHabitList);
}
