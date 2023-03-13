package seedu.duke.atomichabit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import seedu.duke.atomichabit.command.AddCommand;
import seedu.duke.atomichabit.command.Command;
import seedu.duke.atomichabit.command.UpdateCommand;
import seedu.duke.exception.AtomicHabitException;
import seedu.duke.atomichabit.feature.AtomicHabitList;
import seedu.duke.atomichabit.feature.AtomicHabitManager;


public class AtomicHabitTest {
    private AtomicHabitList habitList;

    /**
     * Test AddCommand with a standard payload to check output printed
     */
    @Test
    public void addHabit_checkOutput_success() throws AtomicHabitException {
        habitList = new AtomicHabitList();
        String payload = "junit test";
        String expectedOutput = "Yay! You have added a new habit:"
                + System.lineSeparator()
                + "'"
                + payload
                + "'"
                + " was successfully added";
        Command command = new AddCommand(payload);
        Assertions.assertEquals(expectedOutput, command.execute(habitList).getCommandResult());
    }

    /**
     * Test AddCommand to throw {@link AtomicHabitException} when an invalid command is given to the AtomicHabitManager
     */
    @Test
    public void addHabit_invalidCommand_atomicHabitExceptionThrown() {
        // Test false command by user
        AtomicHabitManager atomicHabitManager = new AtomicHabitManager();
        String command = "sleep";
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            atomicHabitManager.testInvalidCommand(command);
        }, "The following is an invalid command:\n"
                + command);
    }

    /**
     * Test UpdateCommand with a standard payload and default increment to check output printed
     */
    @Test
    public void updateHabit_checkOutput_success() throws AtomicHabitException {
        addHabit_checkOutput_success();
        String payload = "junit test";
        String habitIndex = "1";
        Command updateCommand = new UpdateCommand(habitIndex);
        String expectedUpdateHabitOutput = "The following habit has been incremented! Keep up the good work!"
                + System.lineSeparator()
                + habitIndex + "." + payload + " " + "[2]"
                + System.lineSeparator();
        Assertions.assertEquals(expectedUpdateHabitOutput, updateCommand.execute(habitList).getCommandResult());
    }

    /**
     * Test UpdateCommand to throw {@link AtomicHabitException} when a non-integer index is given to the UpdateCommand
     */
    @Test
    public void updateHabit_indexNotInteger_atomicHabitExceptionThrown() throws AtomicHabitException {
        // Test false command by user
        addHabit_checkOutput_success();
        String habitIndex = "a";
        Command updateCommand = new UpdateCommand(habitIndex);
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            updateCommand.execute(habitList);
        });
    }

    /**
     * Test UpdateCommand to throw {@link AtomicHabitException} when an out-of-bounds index is given to the UpdateCommand
     */
    @Test
    public void updateHabit_indexOutOfBounds_atomicHabitExceptionThrown() throws AtomicHabitException {
        // Test false command by user
        addHabit_checkOutput_success();
        String largeHabitIndex = "100000000";
        Command updateCommandForLargeIndex = new UpdateCommand(largeHabitIndex);
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            updateCommandForLargeIndex.execute(habitList);
        });

        String negativeHabitIndex = "-100000000";
        Command updateCommandForNegativeIndex = new UpdateCommand(negativeHabitIndex);
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            updateCommandForNegativeIndex.execute(habitList);
        });
    }
}



