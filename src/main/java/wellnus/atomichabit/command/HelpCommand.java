package wellnus.atomichabit.command;

import java.util.ArrayList;
import java.util.HashMap;

import wellnus.atomichabit.feature.AtomicHabitManager;
import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.ui.TextUi;

/**
 * Implementation of Atomic Habit WellNus' <code>help</code> command. Explains to the user what commands are supported
 * by Atomic Habit and how to use each command.
 */

public class HelpCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "help - Get help on what commands can be used "
            + "in Atomic Habit WellNUS++";
    public static final String COMMAND_USAGE = "usage: help [command-to-check]";
    private static final String BAD_COMMAND_MESSAGE = "help does not take in any arguments!";
    private static final String COMMAND_KEYWORD = "help";
    private static final String NO_FEATURE_KEYWORD = "";
    private static final String HELP_PREAMBLE = "Input `help` to see all available commands.\n"
            + "Input `help [command-to-check] to get usage help for a specific command.\n"
            + "Here are all the commands available for you!";
    private static final String ERROR_UNKNOWN_COMMAND = "Sorry, we couldn't find that command!\n"
            + "To find a command accessible in this part of WellNUS++, try `help`!";
    private static final String PADDING = " ";
    private static final String DOT = ".";
    private static final int ONE_OFFSET = 1;
    private static final int EXPECTED_PAYLOAD_SIZE = 1;
    private final TextUi textUi;

    /**
     * Initialises a HelpCommand Object using the command arguments issued by the user.
     *
     * @param arguments Command arguments issued by the user
     */
    public HelpCommand(HashMap<String, String> arguments) {
        super(arguments);
        this.textUi = new TextUi();
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private ArrayList<String> getCommandDescriptions() {
        ArrayList<String> commandDescriptions = new ArrayList<>();
        commandDescriptions.add(AddCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(HelpCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(HomeCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(ListCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(UpdateCommand.COMMAND_DESCRIPTION);
        return commandDescriptions;
    }

    /**
     * Prints either the general help message or the command-specific help message
     * based on the presence of a payload.
     */
    private void printHelpMessage() {
        HashMap<String, String> argumentPayload = getArguments();
        String commandToSearch = argumentPayload.get(COMMAND_KEYWORD);
        if (commandToSearch.equals(NO_FEATURE_KEYWORD)) {
            printGeneralHelpMessage();
            return;
        }
        printSpecificHelpMessage(commandToSearch);
    }

    /**
     * Lists all features available in Atomic Habit WellNUS++ and a short description.
     */
    public void printGeneralHelpMessage() {
        ArrayList<String> commandDescriptions = getCommandDescriptions();
        String outputMessage = AtomicHabitManager.FEATURE_HELP_DESCRIPTION;
        outputMessage = outputMessage.concat(System.lineSeparator());
        outputMessage = outputMessage.concat(HELP_PREAMBLE);
        outputMessage = outputMessage.concat(System.lineSeparator() + System.lineSeparator());

        for (int i = 0; i < commandDescriptions.size(); i += 1) {
            outputMessage = outputMessage.concat(i + ONE_OFFSET + DOT + PADDING);
            outputMessage = outputMessage.concat(commandDescriptions.get(i) + System.lineSeparator());
        }
        this.getTextUi().printOutputMessage(outputMessage);
    }

    /**
     * Prints the help message for a given commandToSearch.
     * If it does not exist,
     */
    public void printSpecificHelpMessage(String commandToSearch) {
        switch (commandToSearch) {
        case AddCommand.COMMAND_KEYWORD:
            printUsageMessage(AddCommand.COMMAND_DESCRIPTION, AddCommand.COMMAND_USAGE);
            break;
        case HelpCommand.COMMAND_KEYWORD:
            printUsageMessage(HelpCommand.COMMAND_DESCRIPTION, HelpCommand.COMMAND_USAGE);
            break;
        case HomeCommand.COMMAND_KEYWORD:
            printUsageMessage(HomeCommand.COMMAND_DESCRIPTION, HomeCommand.COMMAND_USAGE);
            break;
        case ListCommand.COMMAND_KEYWORD:
            printUsageMessage(ListCommand.COMMAND_DESCRIPTION, ListCommand.COMMAND_USAGE);
            break;
        case UpdateCommand.COMMAND_KEYWORD:
            printUsageMessage(UpdateCommand.COMMAND_DESCRIPTION, UpdateCommand.COMMAND_USAGE);
            break;
        default:
            textUi.printOutputMessage(ERROR_UNKNOWN_COMMAND);
        }
    }

    private void printUsageMessage(String commandDescription, String usageString) {
        String message = commandDescription + System.lineSeparator() + usageString;
        textUi.printOutputMessage(message);
    }
    @Override
    protected String getCommandKeyword() {
        return HelpCommand.COMMAND_KEYWORD;
    }

    @Override
    protected String getFeatureKeyword() {
        return HelpCommand.NO_FEATURE_KEYWORD;
    }

    /**
     * Executes the issued help command.<br>
     * <p>
     * Prints a brief description of all of Atomic Habit WellNus' supported commands if
     * the basic 'help' command was issued.<br>
     * <p>
     * Prints a detailed description of a specific feature if the specialised
     * 'help' command was issued.
     */
    @Override
    public void execute() {
        try {
            validateCommand(getArguments());
        } catch (BadCommandException exception) {
            getTextUi().printOutputMessage(exception.getMessage());
            return;
        }
        this.printHelpMessage();
    }

    /**
     * Checks whether the given arguments are valid for our help command.
     *
     * @param arguments Argument-Payload map generated by CommandParser using user's command
     * @throws BadCommandException If the command is invalid
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        assert arguments.containsKey(COMMAND_KEYWORD) : "HelpCommand's payload map does not contain 'help'!";
        // Check if user put in unnecessary payload or arguments
        if (arguments.size() > EXPECTED_PAYLOAD_SIZE) {
            throw new BadCommandException(BAD_COMMAND_MESSAGE);
        }
    }

    /**
     * Abstract method to ensure developers add in a command usage.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "usage: add --name (name of habit)"
     *
     * @return String of the proper usage of the habit
     */
    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Method to ensure that developers add in a description for the command.
     * <p>
     * For example, for the 'add' command in AtomicHabit package: <br>
     * "add - add a habit to your list"
     *
     * @return String of the description of what the command does
     */
    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
