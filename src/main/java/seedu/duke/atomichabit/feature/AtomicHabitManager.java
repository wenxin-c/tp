package seedu.duke.atomichabit.feature;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.duke.atomichabit.command.Command;
import seedu.duke.atomichabit.command.AddCommand;
import seedu.duke.atomichabit.command.CommandResult;
import seedu.duke.atomichabit.command.ExitCommand;
import seedu.duke.atomichabit.command.ListCommand;
import seedu.duke.atomichabit.command.UpdateCommand;
import seedu.duke.exception.AtomicHabitException;
import seedu.duke.command.CommandParser;
import seedu.duke.exception.BadCommandException;
import seedu.duke.ui.TextUi;

public class AtomicHabitManager {
    private static final Scanner myScanner = new Scanner(System.in);
    private final CommandParser parser = new CommandParser();
    private final TextUi ui = new TextUi();
    private AtomicHabitList habitList;

    public AtomicHabitManager() {
        this.habitList = new AtomicHabitList();
    }

    /**
     * Method to be called by MainManager when user wishes to utilise atomichabit feature
     * To be expanded on with the inclusion of UI/Game class
     */
    public void run() {
        runCommands();
    }

    /**
     * Requests user for input
     *
     * @return user input in a String
     */
    public static String takeInput() {
        String nextCommand = "";
        try {
            nextCommand = myScanner.nextLine();
        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
        }
        return nextCommand;
    }

    /**
     * Continuously runs different user commands if not exit
     */
    private void runCommands() {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = returnCommand();
                CommandResult result = command.execute(habitList);
                String feedback = result.getCommandResult();
                ui.printOutputMessage(feedback);
                isExit = ExitCommand.isExit(command);
            } catch (AtomicHabitException exception) {
                ui.printErrorFor(exception, "Check user guide for valid commands!");
            }
        }
    }

    /**
     * Method that returns a command to be executed after parsing the user input
     * to identify which command to return
     *
     * @return specific command to be executed by method runCommands
     */
    private Command returnCommand() throws AtomicHabitException {
        String userInput = takeInput();
        HashMap<String, String> parsedInputs = new HashMap<>();
        try {
            parsedInputs = parser.parseUserInput(userInput);
        } catch (BadCommandException exception) {
            System.out.println(exception.getMessage());
        }
        String commandAction = "";
        try {
            commandAction = parser.getMainArgument(userInput);
        } catch (BadCommandException exception) {
            System.out.println(exception.getMessage());
        }
        switch (commandAction) {
        case AddCommand.COMMAND_WORD:
            return new AddCommand(parsedInputs.get(commandAction));
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommand(parsedInputs.get(commandAction));
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new AtomicHabitException("Invalid command! Please"
                    + " enter a valid command");
        }
    }

    /**
     * Method to test for exception handling of invalid command using JUnit
     *
     * @param userCommand command identified after parsing through userInput
     * @return Command according to userInput
     * @throws AtomicHabitException
     */

    public Command testInvalidCommand(String userCommand) throws AtomicHabitException {
        switch (userCommand) {
        case AddCommand.COMMAND_WORD:
            return new AddCommand("testing");
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommand("1");
        default:
            throw new AtomicHabitException("Invalid command! Please"
                    + " enter a valid command");
        }
    }
}



