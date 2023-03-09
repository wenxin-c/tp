package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.atomichabit.AtomicHabitList;

public class InvalidCommand extends Command {

    @Override
    public CommandResult execute(AtomicHabitList atomicHabitList) {
        return new CommandResult("Invalid command! Please" +
                "enter a valid command");

    }

}
