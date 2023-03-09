package seedu.duke.atomichabit.atomichabit;
import java.util.HashMap;
import java.util.Scanner;

import seedu.duke.atomichabit.command.*;
import seedu.duke.command.BadCommandException;
import seedu.duke.command.CommandParser;
public class AtomicHabitManager {
    private final CommandParser parser = new CommandParser();
    private final String HABIT_DIVIDER = "—---------------------------------------------------------------";
    private AtomicHabitList habitList;
    private static String userInput;

    private static Scanner myScanner = new Scanner(System.in);

    /**
     * Method to be called by MainManager when user wishes to utilise atomichabits
     */
    public void run() {
        start();
        runCommands();
    }

    /**
     * Requests user for input
     * @return user input in a String
     */
    public static String takeInput() {
        String line = myScanner.nextLine();
        return line;
    }

    private void start() {
        habitList = new AtomicHabitList();
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
        userInput = takeInput();
        HashMap<String,String> parsedInputs = new HashMap<>();
        try {
             parsedInputs = parser.parseUserInput(userInput);
        }catch(BadCommandException e) {
            System.out.println(e.getMessage());
        }
        String commandAction = "";
        try {
            commandAction = parser.getMainArgument(userInput);
        }catch (BadCommandException e) {
            System.out.println(e.getMessage());
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
