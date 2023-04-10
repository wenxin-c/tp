package wellnus.focus.command;

import java.util.ArrayList;
import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.focus.feature.FocusManager;
import wellnus.focus.feature.FocusUi;
import wellnus.ui.TextUi;

/**
 * Implementation of Focus Timer WellNus' <code>help</code> command. Explains to the user what commands are supported
 * by Focus Timer and how to use each command.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "help - Get help on what commands can be used "
            + "in Focus Timer WellNUS++";
    public static final String COMMAND_USAGE = "usage: help [command-to-check]";
    private static final String BAD_ARGUMENTS_MESSAGE = "Invalid arguments given to 'help'!";
    private static final String COMMAND_INVALID_PAYLOAD = "Invalid payload given to 'help'!";
    private static final String COMMAND_INVALID_COMMAND_NOTE = "help command " + COMMAND_USAGE;
    private static final String COMMAND_KEYWORD = "help";
    private static final String NO_FEATURE_KEYWORD = "";
    private static final String HELP_PREAMBLE = "Input `help` to see all available commands."
            + System.lineSeparator()
            + "Input `help [command-to-check]` to get usage help for a specific command."
            + System.lineSeparator()
            + "Here are all the commands available for you!";
    private static final String PADDING = " ";
    private static final String DOT = ".";
    private static final int ONE_OFFSET = 1;
    private static final int EXPECTED_PAYLOAD_SIZE = 1;
    private final FocusUi focusUi;

    /**
     * Initialises a HelpCommand Object using the command arguments issued by the user.
     *
     * @param arguments Command arguments issued by the user
     */
    public HelpCommand(HashMap<String, String> arguments) {
        super(arguments);
        this.focusUi = new FocusUi();
    }

    private TextUi getFocusUi() {
        return this.focusUi;
    }

    private ArrayList<String> getCommandDescriptions() {
        ArrayList<String> commandDescriptions = new ArrayList<>();
        commandDescriptions.add(CheckCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(ConfigCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(HelpCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(HomeCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(NextCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(PauseCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(ResumeCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(StartCommand.COMMAND_DESCRIPTION);
        commandDescriptions.add(StopCommand.COMMAND_DESCRIPTION);
        return commandDescriptions;
    }

    /**
     * Prints either the general help message or the command-specific help message
     * based on the presence of a payload.
     */
    private void printHelpMessage() {
        HashMap<String, String> argumentPayload = getArguments();
        String commandToSearch = argumentPayload.get(COMMAND_KEYWORD).trim().toLowerCase();
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
        String outputMessage = FocusManager.FEATURE_HELP_DESCRIPTION;
        outputMessage = outputMessage.concat(System.lineSeparator());
        outputMessage = outputMessage.concat(HELP_PREAMBLE);
        outputMessage = outputMessage.concat(System.lineSeparator() + System.lineSeparator());

        for (int i = 0; i < commandDescriptions.size(); i += 1) {
            outputMessage = outputMessage.concat(i + ONE_OFFSET + DOT + PADDING);
            outputMessage = outputMessage.concat(commandDescriptions.get(i) + System.lineSeparator());
        }
        this.getFocusUi().printOutputMessage(outputMessage);
    }

    /**
     * Prints the help message for a given commandToSearch. <br/>
     * If the commandToSearch does not exist, help will print an unknown command
     * error message.
     */
    public void printSpecificHelpMessage(String commandToSearch) {
        switch (commandToSearch) {
        case CheckCommand.COMMAND_KEYWORD:
            printUsageMessage(CheckCommand.COMMAND_DESCRIPTION, CheckCommand.COMMAND_USAGE);
            break;
        case ConfigCommand.COMMAND_KEYWORD:
            printUsageMessage(ConfigCommand.COMMAND_DESCRIPTION, ConfigCommand.COMMAND_USAGE);
            break;
        case HelpCommand.COMMAND_KEYWORD:
            printUsageMessage(HelpCommand.COMMAND_DESCRIPTION, HelpCommand.COMMAND_USAGE);
            break;
        case HomeCommand.COMMAND_KEYWORD:
            printUsageMessage(HomeCommand.COMMAND_DESCRIPTION, HomeCommand.COMMAND_USAGE);
            break;
        case NextCommand.COMMAND_KEYWORD:
            printUsageMessage(NextCommand.COMMAND_DESCRIPTION, NextCommand.COMMAND_USAGE);
            break;
        case PauseCommand.COMMAND_KEYWORD:
            printUsageMessage(PauseCommand.COMMAND_DESCRIPTION, PauseCommand.COMMAND_USAGE);
            break;
        case ResumeCommand.COMMAND_KEYWORD:
            printUsageMessage(ResumeCommand.COMMAND_DESCRIPTION, ResumeCommand.COMMAND_USAGE);
            break;
        case StartCommand.COMMAND_KEYWORD:
            printUsageMessage(StartCommand.COMMAND_DESCRIPTION, StartCommand.COMMAND_USAGE);
            break;
        case StopCommand.COMMAND_KEYWORD:
            printUsageMessage(StopCommand.COMMAND_DESCRIPTION, StopCommand.COMMAND_USAGE);
            break;
        default:
            BadCommandException unknownCommand = new BadCommandException(COMMAND_INVALID_PAYLOAD);
            focusUi.printErrorFor(unknownCommand, COMMAND_INVALID_COMMAND_NOTE);
        }
    }

    private void printUsageMessage(String commandDescription, String usageString) {
        String message = commandDescription + System.lineSeparator() + usageString;
        focusUi.printOutputMessage(message);
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
     * Prints a brief description of all of Focus Timer WellNus' supported commands if
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
            getFocusUi().printErrorFor(exception, COMMAND_INVALID_COMMAND_NOTE);
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
            throw new BadCommandException(BAD_ARGUMENTS_MESSAGE);
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
