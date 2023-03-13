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
    public void addHabit_correct_output() throws AtomicHabitException {
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

    @Test
    public void addHabit_invalidCommand_exceptionThrown() {
        // Test false command by user
        AtomicHabitManager atomicHabitManager = new AtomicHabitManager();
        String command = "sleep";
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            atomicHabitManager.testInvalidCommand(command);
        }, "The following is an invalid command:\n"
                + command);
    }

    @Test
    public void updateHabit_correct_output() throws AtomicHabitException {
        addHabit_correct_output();
        String payload = "junit test";
        String habitIndex = "1";
        Command updateCommand = new UpdateCommand(habitIndex);
        String expectedUpdateHabitOutput = "The following habit has been incremented! Keep up the good work!"
                + System.lineSeparator()
                + habitIndex + "." + payload + " " + "[2]"
                + System.lineSeparator();
        Assertions.assertEquals(expectedUpdateHabitOutput, updateCommand.execute(habitList).getCommandResult());
    }

    @Test
    public void updateHabit_indexNotInteger_exceptionThrown() throws AtomicHabitException {
        // Test false command by user
        addHabit_correct_output();
        String habitIndex = "a";
        Command updateCommand = new UpdateCommand(habitIndex);
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            updateCommand.execute(habitList);
        });
    }

    @Test
    public void updateHabit_indexOutOfBounds_exceptionThrown() throws AtomicHabitException {
        // Test false command by user
        addHabit_correct_output();
        String habitIndex = "1000";
        Command updateCommand = new UpdateCommand(habitIndex);
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            updateCommand.execute(habitList);
        });
    }
}



