package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.feature.AtomicHabit;
import seedu.duke.atomichabit.feature.AtomicHabitList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DOT = ".";
    private static final String FIRST_STRING = "Here are all the habits in your list:";

    @Override
    public CommandResult execute(AtomicHabitList atomicHabits) {
        int taskNo = 1;
        String formattedStringOfHabits = FIRST_STRING + LINE_SEPARATOR;
        for (AtomicHabit habit : atomicHabits.getAllHabits()) {
            formattedStringOfHabits += taskNo + DOT + habit + " " + "[" + habit.getCount() + "]" + LINE_SEPARATOR;
            taskNo += 1;
        }
        return new CommandResult(formattedStringOfHabits.substring(0, formattedStringOfHabits.length() - 1));
    }

}

