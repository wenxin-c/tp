package seedu.duke.atomichabit.feature;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.duke.atomichabit.command.*;
import seedu.duke.command.BadCommandException;
import seedu.duke.command.CommandParser;

public class AtomicHabitManager {
    private final CommandParser parser = new CommandParser();
    private AtomicHabitList habitList;
    private static final Scanner myScanner = new Scanner(System.in);

    /**
     * Method to be called by MainManager when user wishes to utilise atomichabit feature
     * To be expanded on with the inclusion of UI/Game class
     */
    public void run() {
        runCommands();
    }

    /**
     * Requests user for input
     * @return user input in a String
     */
    public static String takeInput() {
        String nextCommand = "";
        try {
            nextCommand = myScanner.nextLine();
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return nextCommand;
    }

    public AtomicHabitManager() {
        this.habitList = new AtomicHabitList();
    }

    /**
     * Continuously runs different user commands if not exit
     */
    private void runCommands() {
        boolean isExit = false;
        while (!isExit) {
            Command command = returnCommand();
            CommandResult result = command.execute(habitList);
            String feedback = result.getCommandResult();
            System.out.println(feedback);
            isExit = ExitCommand.isExit(command);
        }
    }

    /**
     * Method that returns a command to be executed after parsing the user input
     * to identify which command to return
     * @return specific command to be executed by method runCommands
     */
    private Command returnCommand() {
        String userInput = takeInput();
        HashMap<String, String> parsedInputs = new HashMap<>();
        try {
             parsedInputs = parser.parseUserInput(userInput);
        } catch (BadCommandException error) {
            System.out.println(error.getMessage());
        }
        String commandAction = "";
        try {
            commandAction = parser.getMainArgument(userInput);
        } catch (BadCommandException error) {
            System.out.println(error.getMessage());
        }
        switch (commandAction) {
        case AddCommand.COMMAND_WORD:
            return new AddCommand(parsedInputs.get(commandAction));
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}

