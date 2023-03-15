package wellnus.atomichabit.command;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.atomichabit.feature.AtomicHabitList;
import wellnus.exception.AtomicHabitException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateCommand extends Command {
    public static final String COMMAND_WORD = "update";
    private static final String DOT = ".";
    private static final int DEFAULT_INCREMENT = 1;
    private static final String FEEDBACK_STRING = "The following habit has been incremented! Keep up the good work!";
    private static final String FEEDBACK_INDEX_NOT_INTEGER_ERROR = "Invalid index! Please enter an integer";
    private static final String FEEDBACK_INDEX_OUT_OF_BOUNDS_ERROR = "Index out of Range! Please enter a valid index";
    private static final int INDEX_OFFSET = 1;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String REGEX_INTEGER_ONLY_PATTERN = "\\s*-?\\d+\\s*";
    private static final Logger logger = Logger.getLogger("UpdateAtomicHabitLogger");
    private static final String LOG_STR_INPUT_NOT_INTEGER = "Input string is not an integer."
            + "This should be properly handled";

    private static final String LOG_INDEX_OUT_OF_BOUNDS = "Input index is out of bounds."
            + "This should be properly handled";
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
        assert indexString.matches(REGEX_INTEGER_ONLY_PATTERN) : "Index must be an integer";
        try {
            int index = Integer.parseInt(indexString.trim()) - INDEX_OFFSET;
            AtomicHabit habit = atomicHabits.getHabitByIndex(index);
            habit.increaseCount(DEFAULT_INCREMENT);
            String stringOfUpdatedHabit = indexString + DOT + habit + " " + "[" + habit.getCount() + "]"
                    + LINE_SEPARATOR;
            return new CommandResult(FEEDBACK_STRING
                    + LINE_SEPARATOR
                    + stringOfUpdatedHabit);
        } catch (NumberFormatException e) {
            logger.log(Level.INFO, LOG_STR_INPUT_NOT_INTEGER);
            throw new AtomicHabitException(FEEDBACK_INDEX_NOT_INTEGER_ERROR);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.INFO, LOG_INDEX_OUT_OF_BOUNDS);
            throw new AtomicHabitException(FEEDBACK_INDEX_OUT_OF_BOUNDS_ERROR);
        }

    }

}
