package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.feature.AtomicHabitList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT = "Thank you for using atomic habits. Do not forget about me!";

    /**
     * Executes the command and returns the result.
     *
     * @return CommandResult which is a exit message to user
     */
    @Override
    public CommandResult execute(AtomicHabitList atomicHabits) {
        return new CommandResult(MESSAGE_EXIT);
    }

    /**
     * Check if a ByeCommand is executed and user wants to exit program
     *
     * @param command user command
     * @return true if user wants to exit false if not
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}


