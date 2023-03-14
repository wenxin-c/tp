package wellnus.atomichabit.command;

import wellnus.atomichabit.feature.AtomicHabitList;
import wellnus.exception.AtomicHabitException;

public abstract class Command {
    /**
     * Method that will be overridden by subclasses which will properly execute the specific commands
     *
     * @param atomicHabits
     * @return CommandResult which contains information to be printed to user
     */
    public abstract CommandResult execute(AtomicHabitList atomicHabits) throws AtomicHabitException;
}


