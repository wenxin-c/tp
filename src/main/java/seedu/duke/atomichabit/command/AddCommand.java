package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.feature.AtomicHabit;
import seedu.duke.atomichabit.feature.AtomicHabitList;

public class AddCommand extends Command {
    private static final String FEEDBACK_STRING_ONE = "Yay! You have added a new habit:";
    private static final String FEEDBACK_STRING_TWO = " was successfully added";
    public static final String COMMAND_WORD = "add";
    private String description;

    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Method to execute adding of an atomic habit feature into atomicHabitList
     * @param atomicHabits
     * @return CommandResult that contains feedback to the user
     */
    @Override
    public CommandResult execute(AtomicHabitList atomicHabits) {
        AtomicHabit habit = new AtomicHabit(description);
        atomicHabits.addAtomicHabit(habit);
        return new CommandResult(FEEDBACK_STRING_ONE
                                                + System.lineSeparator() + "'"
                                                + habit + "'"
                                                + FEEDBACK_STRING_TWO);
    }
}


