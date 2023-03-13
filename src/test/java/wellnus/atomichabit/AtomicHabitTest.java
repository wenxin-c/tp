package wellnus.atomichabit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import wellnus.atomichabit.command.AddCommand;
import wellnus.atomichabit.command.Command;
import wellnus.exception.AtomicHabitException;
import wellnus.atomichabit.feature.AtomicHabitList;
import wellnus.atomichabit.feature.AtomicHabitManager;


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
}



