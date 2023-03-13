package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.feature.AtomicHabit;
import seedu.duke.atomichabit.feature.AtomicHabitList;
import seedu.duke.exception.AtomicHabitException;

public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";
    private static final String DOT = ".";
    private static final int DEFAULT_INCREMENT = 1;
    private static final String FEEDBACK_STRING = "The following habit has been incremented! Keep up the good work!";
    private static final String FEEDBACK_INDEX_NOT_INTEGER_ERROR = "Invalid index! Please enter an integer";
    private static final String FEEDBACK_INDEX_OUT_OF_BOUNDS_ERROR = "Index out of Range! Please enter a valid index";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private String indexString;

    public UpdateCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Method to execute updating an atomic habit count
     *
     * @param atomicHabits
     * @return CommandResult that contains feedback to the user
     */
    @Override
    public CommandResult execute(AtomicHabitList atomicHabits) throws AtomicHabitException {
        try {
            int index = Integer.parseInt(indexString) - 1;
            AtomicHabit habit = atomicHabits.getHabitByIndex(index);
            habit.setCount(DEFAULT_INCREMENT);
            String stringOfUpdatedHabit = indexString + DOT + habit + " " + "[" + habit.getCount() + "]"
                    + LINE_SEPARATOR;
            return new CommandResult(FEEDBACK_STRING
                    + LINE_SEPARATOR
                    + stringOfUpdatedHabit);
        } catch (NumberFormatException e) {
            throw new AtomicHabitException(FEEDBACK_INDEX_NOT_INTEGER_ERROR);
        } catch (IndexOutOfBoundsException e) {
            throw new AtomicHabitException(FEEDBACK_INDEX_OUT_OF_BOUNDS_ERROR);
        }

    }

}
