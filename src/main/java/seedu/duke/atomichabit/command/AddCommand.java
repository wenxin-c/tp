package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.atomichabit.AtomicHabit;
import seedu.duke.atomichabit.atomichabit.AtomicHabitList;

public class AddCommand extends Command{

    private String description;
    private static final String FIRST_STRING = "Yay! You have added a new habit:";
    private static final String SECOND_STRING = " was successfully added";
    public static final String COMMAND_WORD = "add";


    public AddCommand(String description) {
        this.description = description;
    }

    /**
     *
     * @param atomicHabitList
     * @return
     */
    @Override
    public CommandResult execute(AtomicHabitList atomicHabitList) {
        AtomicHabit habit = new AtomicHabit(description);
        atomicHabitList.setAtomicHabits(habit);
        return new CommandResult(FIRST_STRING + System.lineSeparator() + "'" + habit + "'" + SECOND_STRING);
    }

}
