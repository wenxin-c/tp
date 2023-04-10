package wellnus.atomichabit.feature;

import java.util.HashMap;

import wellnus.atomichabit.command.AddCommand;
import wellnus.atomichabit.command.DeleteCommand;
import wellnus.atomichabit.command.HelpCommand;
import wellnus.atomichabit.command.HomeCommand;
import wellnus.atomichabit.command.ListCommand;
import wellnus.atomichabit.command.UpdateCommand;
import wellnus.command.Command;
import wellnus.exception.AtomicHabitException;
import wellnus.exception.BadCommandException;
import wellnus.exception.StorageException;
import wellnus.exception.WellNusException;
import wellnus.gamification.util.GamificationData;
import wellnus.manager.Manager;


/**
 * Class to represent the event driver of Atomic Habits feature
 * This class will handle calling the different available commands for Atomic Habits according to user input
 */
public class AtomicHabitManager extends Manager {
    public static final String FEATURE_HELP_DESCRIPTION = "hb - Atomic Habits - Track and manage your habits "
            + "with our suite of tools to help you grow and nurture a better you!";
    public static final String FEATURE_NAME = "hb";
    private static final String ADD_COMMAND_KEYWORD = "add";
    private static final String ATOMIC_HABIT_LOGO = "    _  _             _      _  _      _    _ _      "
            + System.lineSeparator()
            +
            "   /_\\| |_ ___ _ __ (_)__  | || |__ _| |__(_) |_ ___" + System.lineSeparator()
            +
            "  / _ \\  _/ _ \\ '  \\| / _| | __ / _` | '_ \\ |  _(_-<" + System.lineSeparator()
            +
            " /_/ \\_\\__\\___/_|_|_|_\\__| |_||_\\__,_|_.__/_|\\__/__/" + System.lineSeparator();
    private static final String GREETING_MESSAGE = "Welcome to WellNUS++ Atomic Habits section!"
            + System.lineSeparator() + "Track and inculcate good habits into your life with us!";
    private static final String HOME_COMMAND_KEYWORD = "home";
    private static final String LIST_COMMAND_KEYWORD = "list";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Invalid command issued!";
    private static final String UPDATE_COMMAND_KEYWORD = "update";
    private static final String HELP_COMMAND_KEYWORD = "help";
    private static final String DELETE_COMMAND_KEYWORD = "delete";
    private static final String ERROR_STORAGE_MESSAGE = "Error saving to storage!";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COMMAND_INVALID_COMMAND_NOTE =
            "Supported commands in Atomic Habit: " + LINE_SEPARATOR
                    + "add command " + AddCommand.COMMAND_USAGE + LINE_SEPARATOR
                    + "delete command " + DeleteCommand.COMMAND_USAGE + LINE_SEPARATOR
                    + "list command " + ListCommand.COMMAND_USAGE + LINE_SEPARATOR
                    + "update command " + UpdateCommand.COMMAND_USAGE + LINE_SEPARATOR
                    + "help command " + HelpCommand.COMMAND_USAGE + LINE_SEPARATOR
                    + "home command " + HomeCommand.COMMAND_USAGE;
    private static final String ADD_USAGE = "add command " + AddCommand.COMMAND_USAGE;
    private static final String DELETE_USAGE = "delete command " + DeleteCommand.COMMAND_USAGE;
    private static final String HOME_USAGE = "home command " + HomeCommand.COMMAND_USAGE;
    private static final String UPDATE_USAGE = "update command " + UpdateCommand.COMMAND_USAGE;
    private final AtomicHabitUi atomicHabitUi;
    private final AtomicHabitList habitList;
    private final GamificationData gamificationData;

    /**
     * Constructor of AtomicHabitManager
     * Will initialise the private objects habitList and textUi
     */
    public AtomicHabitManager(GamificationData gamificationData) {
        this.gamificationData = gamificationData;
        this.habitList = new AtomicHabitList();
        this.atomicHabitUi = new AtomicHabitUi();
        this.atomicHabitUi.setCursorName(FEATURE_NAME);
    }

    /**
     * Parses the given command from the user and determines the correct Command
     * subclass that can handle its execution.
     *
     * @param commandString Full command issued by the user
     * @return Command object that can execute the user's command
     * @throws BadCommandException If an unknown command was issued by the user
     */
    private Command getCommandFor(String commandString) throws BadCommandException {
        HashMap<String, String> arguments = getCommandParser().parseUserInput(commandString);
        String commandKeyword = getCommandParser().getMainArgument(commandString);
        switch (commandKeyword) {
        case ADD_COMMAND_KEYWORD:
            return new AddCommand(arguments, getHabitList());
        case DELETE_COMMAND_KEYWORD:
            return new DeleteCommand(arguments, getHabitList());
        case HOME_COMMAND_KEYWORD:
            return new HomeCommand(arguments);
        case LIST_COMMAND_KEYWORD:
            return new ListCommand(arguments, getHabitList());
        case UPDATE_COMMAND_KEYWORD:
            return new UpdateCommand(arguments, getHabitList(), gamificationData);
        case HELP_COMMAND_KEYWORD:
            return new HelpCommand(arguments);
        default:
            throw new BadCommandException(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private AtomicHabitList getHabitList() {
        return this.habitList;
    }

    private AtomicHabitUi getTextUi() {
        return this.atomicHabitUi;
    }

    private void greet() {
        getTextUi().printLogoWithSeparator(ATOMIC_HABIT_LOGO);
        getTextUi().printOutputMessage(GREETING_MESSAGE);
    }

    /**
     * Reads user commands continuously and execute those that are supported
     * until the exit command is given.
     */
    private void runCommands() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = getTextUi().getCommand();
                Command command = getCommandFor(commandString);
                command.execute();
                try {
                    habitList.storeHabitData();
                } catch (StorageException exception) {
                    this.getTextUi().printErrorFor(exception, ERROR_STORAGE_MESSAGE);
                }
                isExit = HomeCommand.isExit(command);
            } catch (WellNusException badCommandException) {
                String errorMessage = badCommandException.getMessage();
                if (errorMessage.contains(ADD_COMMAND_KEYWORD)) {
                    getTextUi().printErrorFor(badCommandException, ADD_USAGE);
                } else if (errorMessage.contains(DELETE_COMMAND_KEYWORD)) {
                    getTextUi().printErrorFor(badCommandException, DELETE_USAGE);
                } else if (errorMessage.contains(HOME_COMMAND_KEYWORD)) {
                    getTextUi().printErrorFor(badCommandException, HOME_USAGE);
                } else if (errorMessage.contains(UPDATE_COMMAND_KEYWORD)) {
                    getTextUi().printErrorFor(badCommandException, UPDATE_USAGE);
                } else {
                    getTextUi().printErrorFor(badCommandException, COMMAND_INVALID_COMMAND_NOTE);
                }
            }
        }
    }

    /**
     * Returns the commandline name of the atomic habits feature
     *
     * @return Commandline name of this feature
     */
    @Override
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    /**
     * First welcomes user with our unique greeting.<br>
     * <br>
     * Then continuously read commands from the user and execute those that are supported.
     */
    @Override
    public void runEventDriver() {
        greet();
        runCommands();
    }

    /**
     * Method to test for exception handling of invalid command using JUnit
     *
     * @param userCommand Command identified after parsing through userInput
     * @return Command according to userInput
     * @throws AtomicHabitException For every invalid command being tested below
     */
    public Command testInvalidCommand(String userCommand) throws AtomicHabitException {
        String descriptionTest = "testing";
        String exitCommand = "hb exit";
        String listCommand = "hb list";
        String indexTest = "1";
        String invalidCommandErrorMessage = "Invalid command issued!!";
        HashMap<String, String> arguments;
        try {
            switch (userCommand) {
            case ADD_COMMAND_KEYWORD:
                arguments = getCommandParser().parseUserInput(descriptionTest);
                return new AddCommand(arguments, new AtomicHabitList());
            case DELETE_COMMAND_KEYWORD:
                arguments = getCommandParser().parseUserInput(indexTest);
                return new DeleteCommand(arguments, new AtomicHabitList());
            case LIST_COMMAND_KEYWORD:
                arguments = getCommandParser().parseUserInput(listCommand);
                return new ListCommand(arguments, new AtomicHabitList());
            case HOME_COMMAND_KEYWORD:
                arguments = getCommandParser().parseUserInput(exitCommand);
                return new HomeCommand(arguments);
            case UPDATE_COMMAND_KEYWORD:
                arguments = getCommandParser().parseUserInput(indexTest);
                return new UpdateCommand(arguments, new AtomicHabitList(), gamificationData);
            default:
                throw new AtomicHabitException(invalidCommandErrorMessage);
            }
        } catch (BadCommandException badCommandException) {
            getTextUi().printErrorFor(badCommandException, COMMAND_INVALID_COMMAND_NOTE);
            return null;
        }
    }
}



