package wellnus.atomichabit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wellnus.atomichabit.command.AddCommand;
import wellnus.atomichabit.command.DeleteCommand;
import wellnus.atomichabit.command.ListCommand;
import wellnus.atomichabit.command.UpdateCommand;
import wellnus.atomichabit.feature.AtomicHabitList;
import wellnus.atomichabit.feature.AtomicHabitManager;
import wellnus.atomichabit.feature.AtomicHabitUi;
import wellnus.command.Command;
import wellnus.command.CommandParser;
import wellnus.exception.AtomicHabitException;
import wellnus.exception.WellNusException;
import wellnus.gamification.util.GamificationData;

public class AtomicHabitTest {
    private static final String ADD_HABIT_COMMAND = "add";
    private static final String UPDATE_HABIT_COMMAND = "update";
    private static final String DELETE_HABIT_COMMAND = "delete";
    private static final String LIST_HABIT_COMMAND = "list";
    private final AtomicHabitList habitList;
    private final ByteArrayOutputStream outputStreamCaptor;
    private final CommandParser parser;
    private final GamificationData gamificationData;

    public AtomicHabitTest() {
        this.habitList = new AtomicHabitList();
        this.outputStreamCaptor = new ByteArrayOutputStream();
        this.parser = new CommandParser();
        this.gamificationData = new GamificationData();
    }

    private String getMessageFrom(String uiOutput) {
        AtomicHabitUi atomicHabitUi = new AtomicHabitUi();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        atomicHabitUi.printSeparator();
        String separator = outputStream.toString().trim();
        StringBuilder resultBuilder = new StringBuilder();
        String[] outputLines = uiOutput.split(System.lineSeparator());
        for (String outputLine : outputLines) {
            String trimmedOutputLine = outputLine.trim();
            if (!trimmedOutputLine.equals(separator)) {
                resultBuilder.append(trimmedOutputLine).append(System.lineSeparator());
            }
        }
        return resultBuilder.toString().trim();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Test AddCommand with a standard payload to check output printed.
     */
    @Test
    public void addHabit_checkOutput_success() throws WellNusException {
        String payload = "junit test";
        String expectedOutput = "Yay! You have added a new habit:"
                + System.lineSeparator()
                + "'"
                + payload
                + "'"
                + " was successfully added";
        String testCommand = String.format("%s --name %s", ADD_HABIT_COMMAND, payload);
        HashMap<String, String> arguments = parser.parseUserInput(testCommand);
        Command command = new AddCommand(arguments, habitList);
        command.execute();
        Assertions.assertEquals(expectedOutput, getMessageFrom(outputStreamCaptor.toString()));
    }

    /**
     * Test AddCommand to throw {@link AtomicHabitException} when an invalid command is given to the AtomicHabitManager.
     */
    @Test
    public void addHabit_invalidCommand_atomicHabitExceptionThrown() {
        // Test false command by user
        AtomicHabitManager atomicHabitManager = new AtomicHabitManager(gamificationData);
        String command = "sleep";
        Assertions.assertThrows(AtomicHabitException.class, () -> {
            atomicHabitManager.testInvalidCommand(command);
        }, "The following is an invalid command:\n"
                + command);
    }

    /**
     * Test AddCommand to throw {@link AtomicHabitException} when a duplicate habit is added.
     *
     * @throws WellNusException
     */
    @Test
    public void addHabit_duplicateHabit_atomicHabitExceptionThrown() throws WellNusException {
        String payload = "junit test";
        String testAddCommand = String.format("%s --name %s", ADD_HABIT_COMMAND, payload + System.lineSeparator());
        HashMap<String, String> arguments = parser.parseUserInput(testAddCommand);
        Command addCommand = new AddCommand(arguments, habitList);
        addCommand.execute();
        Assertions.assertThrows(AtomicHabitException.class, addCommand::execute);
    }

    /**
     * Test UpdateCommand with a standard payload and default increment to check output printed.
     */
    @Test
    public void updateHabit_checkOutputDefaultIncrement_success() throws WellNusException {
        addHabit_checkOutput_success();
        String payload = "junit test";
        String habitIndex = "1";
        String testUpdateCommand = String.format("%s --id %s", UPDATE_HABIT_COMMAND, habitIndex)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testUpdateCommand);
        Command updateCommand = new UpdateCommand(arguments, habitList, gamificationData);
        String expectedUpdateHabitOutput = "The following habit has been incremented! Keep up the good work!"
                + System.lineSeparator()
                + habitIndex + "." + payload + " " + "[1]";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        updateCommand.execute();
        Assertions.assertEquals(expectedUpdateHabitOutput, getMessageFrom(outputStream.toString()));
    }

    /**
     * Test UpdateCommand with a standard payload and user-inputted increment to check output printed.
     */
    @Test
    public void updateHabit_checkOutputUserInputIncrement_success() throws WellNusException {
        addHabit_checkOutput_success();
        String payload = "junit test";
        String habitIndex = "1";
        String increment = "3";
        String testUpdateCommand = String.format("%s --id %s --by %s", UPDATE_HABIT_COMMAND, habitIndex, increment)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testUpdateCommand);
        Command updateCommand = new UpdateCommand(arguments, habitList, gamificationData);
        String expectedUpdateHabitOutput = "The following habit has been incremented! Keep up the good work!"
                + System.lineSeparator()
                + habitIndex + "." + payload + " " + "[3]";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        updateCommand.execute();
        Assertions.assertEquals(expectedUpdateHabitOutput, getMessageFrom(outputStream.toString()));
    }

    /**
     * Test UpdateCommand to throw {@link AtomicHabitException} when a non-integer index is given to the UpdateCommand.
     */
    @Test
    public void updateHabit_indexNotInteger_atomicHabitExceptionThrown() throws WellNusException {
        // Test false command by user
        addHabit_checkOutput_success();
        String habitIndex = "a";
        String testIndexCommand = String.format("%s --id %s", UPDATE_HABIT_COMMAND, habitIndex)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testIndexCommand);
        Command updateCommand = new UpdateCommand(arguments, habitList, gamificationData);
        Assertions.assertThrows(AtomicHabitException.class, updateCommand::execute);
    }

    /**
     * Test UpdateCommand to throw {@link AtomicHabitException} when an out-of-bounds index is given
     * to the UpdateCommand.
     */
    @Test
    public void updateHabit_indexOutOfBounds_atomicHabitExceptionThrown() throws WellNusException {
        // Test false command by user
        addHabit_checkOutput_success();
        String largeHabitIndex = "100000000" + System.lineSeparator();
        String negativeHabitIndex = "-100000000" + System.lineSeparator();
        String testLargeIndexCommand = String.format("%s --id %s", UPDATE_HABIT_COMMAND, largeHabitIndex)
                + System.lineSeparator();
        String testNegativeIndexCommand = String.format("%s --id %s", UPDATE_HABIT_COMMAND, negativeHabitIndex)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testLargeIndexCommand);
        Command updateCommandForLargeIndex = new UpdateCommand(arguments, habitList, gamificationData);
        Assertions.assertThrows(AtomicHabitException.class, updateCommandForLargeIndex::execute);

        arguments = parser.parseUserInput(testNegativeIndexCommand);
        Command updateCommandForNegativeIndex = new UpdateCommand(arguments, habitList, gamificationData);
        Assertions.assertThrows(AtomicHabitException.class, updateCommandForNegativeIndex::execute);
    }

    /**
     * Test UpdateCommand to successfully decrement a habit.
     *
     * @throws WellNusException
     */
    @Test
    public void updateHabit_decrement_success() throws WellNusException {
        updateHabit_checkOutputUserInputIncrement_success();
        String payload = "junit test";
        String habitIndex = "1";
        String decrement = "-3";
        String testUpdateCommand = String.format("%s --id %s --by %s", UPDATE_HABIT_COMMAND, habitIndex, decrement)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testUpdateCommand);
        Command updateCommand = new UpdateCommand(arguments, habitList, gamificationData);
        String expectedUpdateHabitOutput = "The following habit has been decremented."
                + System.lineSeparator()
                + habitIndex + "." + payload + " " + "[0]";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        updateCommand.execute();
        Assertions.assertEquals(expectedUpdateHabitOutput, getMessageFrom(outputStream.toString()));
    }

    /**
     * Test UpdateCommand to throw {@link AtomicHabitException} when a non-integer decrement is given.
     *
     * @throws WellNusException
     */
    @Test
    public void updateHabit_invalidDecrement_atomicHabitExceptionThrown() throws WellNusException {
        updateHabit_checkOutputUserInputIncrement_success();
        String habitIndex = "1";
        String decrement = "-100000000";
        String testUpdateCommand = String.format("%s --id %s --by %s", UPDATE_HABIT_COMMAND, habitIndex, decrement)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testUpdateCommand);
        Command updateCommand = new UpdateCommand(arguments, habitList, gamificationData);
        Assertions.assertThrows(AtomicHabitException.class, updateCommand::execute);
    }

    /**
     * Test UpdateCommand for correct output when list is empty.
     *
     * @throws WellNusException
     */
    @Test
    public void updateHabit_emptyListUnsuccessful() throws WellNusException {
        String habitIndex = "1";
        String decrement = "1";
        String testUpdateCommand = String.format("%s --id %s --by %s", UPDATE_HABIT_COMMAND, habitIndex, decrement)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testUpdateCommand);
        Command updateCommand = new UpdateCommand(arguments, habitList, gamificationData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        updateCommand.execute();
        String expectedOutput = "There are no habits to update!"
                + " Please `add` a habit first!";
        Assertions.assertEquals(expectedOutput, getMessageFrom(outputStream.toString()));
        Assertions.assertEquals(true, habitList.getAllHabits().isEmpty());
    }

    /**
     * Test DeleteCommand to successfully delete a habit and check output is printed correctly.
     *
     * @throws WellNusException
     */
    @Test
    public void delete_habitSuccess() throws WellNusException {
        addHabit_checkOutput_success();
        String habitIndex = "1";
        String testDeleteCommand = String.format("%s --id %s", DELETE_HABIT_COMMAND, habitIndex)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testDeleteCommand);
        Command deleteCommand = new DeleteCommand(arguments, habitList);
        String expectedDeleteHabitOutput = "The following habit has been deleted:"
                + System.lineSeparator()
                + "junit test" + " " + "[0]" + " has been successfully deleted";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        deleteCommand.execute();
        Assertions.assertEquals(true, habitList.getAllHabits().isEmpty());
        Assertions.assertEquals(expectedDeleteHabitOutput, getMessageFrom(outputStream.toString()));
    }

    /**
     * Test DeleteCommand to throw {@link AtomicHabitException} when a non-integer index is given.
     *
     * @throws WellNusException
     */
    @Test
    public void delete_invalidIndex_atomicHabitExceptionThrown() throws WellNusException {
        addHabit_checkOutput_success();
        String habitIndex = "1000000000000";
        String testDeleteCommand = String.format("%s --id %s", DELETE_HABIT_COMMAND, habitIndex)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testDeleteCommand);
        Command deleteCommand = new DeleteCommand(arguments, habitList);
        Assertions.assertThrows(AtomicHabitException.class, deleteCommand::execute);
    }

    /**
     * Test DeleteCommand to throw correct output when list is empty.
     *
     * @throws WellNusException
     */
    @Test
    public void delete_emptyList_unsuccessful() throws WellNusException {
        String habitIndex = "1";
        String testDeleteCommand = String.format("%s --id %s", DELETE_HABIT_COMMAND, habitIndex)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testDeleteCommand);
        Command deleteCommand = new DeleteCommand(arguments, habitList);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        deleteCommand.execute();
        String expectedOutput = "There are no habits to delete!"
                + " Please `add` a habit first!";
        Assertions.assertEquals(expectedOutput, getMessageFrom(outputStream.toString()));
        Assertions.assertEquals(true, habitList.getAllHabits().isEmpty());
    }

    /**
     * Test ListCommand to print correct output when list is empty.
     *
     * @throws WellNusException
     */
    @Test
    public void listEmptyList_successful() throws WellNusException {
        String testListCommand = String.format("%s", LIST_HABIT_COMMAND)
                + System.lineSeparator();
        HashMap<String, String> arguments = parser.parseUserInput(testListCommand);
        Command listCommand = new ListCommand(arguments, habitList);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand.execute();
        String expectedOutput = "You have no habits in your list!" + System.lineSeparator()
                + "Start adding some habits by using 'add'!";
        Assertions.assertEquals(expectedOutput, getMessageFrom(outputStream.toString()));
    }


}
