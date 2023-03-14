package wellnus.atomichabit.command;

import wellnus.atomichabit.feature.AtomicHabitList;

public class InvalidCommand extends Command {

    @Override
    public CommandResult execute(AtomicHabitList atomicHabits) {
        return new CommandResult("Invalid command! Please" +
                "enter a valid command");

    }
}

