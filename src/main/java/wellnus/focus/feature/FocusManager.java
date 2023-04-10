package wellnus.focus.feature;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.focus.command.CheckCommand;
import wellnus.focus.command.ConfigCommand;
import wellnus.focus.command.HelpCommand;
import wellnus.focus.command.HomeCommand;
import wellnus.focus.command.NextCommand;
import wellnus.focus.command.PauseCommand;
import wellnus.focus.command.ResumeCommand;
import wellnus.focus.command.StartCommand;
import wellnus.focus.command.StopCommand;
import wellnus.manager.Manager;

/**
 * Represents a class to run the event driver for the Focus Timer.
 * This class will be called by the main manager.
 * It will match the user input to the correct command and execute it.
 */
//@@author YongbinWang
public class FocusManager extends Manager {

    public static final String FEATURE_HELP_DESCRIPTION = "ft - Focus Timer - Set a configurable 'Pomodoro' timer "
            + "with work and rest cycles to keep yourself focused and productive!";
    public static final String FEATURE_NAME = "ft";
    private static final String START_COMMAND_KEYWORD = "start";
    private static final String PAUSE_COMMAND_KEYWORD = "pause";
    private static final String RESUME_COMMAND_KEYWORD = "resume";
    private static final String NEXT_COMMAND_KEYWORD = "next";
    private static final String CONFIG_COMMAND_KEYWORD = "config";
    private static final String HOME_COMMAND_KEYWORD = "home";
    private static final String STOP_COMMAND_KEYWORD = "stop";
    private static final String CHECK_COMMAND_KEYWORD = "check";
    private static final String HELP_COMMAND_KEYWORD = "help";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Invalid command issued!";
    private static final String FOCUS_TIMER_GREET = "Welcome to Focus Timer." + System.lineSeparator()
            + "Start a focus session with `start`, or `config` the session first!";
    private static final String FOCUS_GREETING_LOGO = " /$$$$$$$$                                     "
            + System.lineSeparator()
            +
            "| $$_____/                                     " + System.lineSeparator()
            +
            "| $$     /$$$$$$   /$$$$$$$ /$$   /$$  /$$$$$$$" + System.lineSeparator()
            +
            "| $$$$$ /$$__  $$ /$$_____/| $$  | $$ /$$_____/ " + System.lineSeparator()
            +
            "| $$__/| $$  \\ $$| $$      | $$  | $$|  $$$$$$ " + System.lineSeparator()
            +
            "| $$   | $$  | $$| $$      | $$  | $$ \\____  $$" + System.lineSeparator()
            +
            "| $$   |  $$$$$$/|  $$$$$$$|  $$$$$$/ /$$$$$$$/" + System.lineSeparator()
            +
            "|__/    \\______/  \\_______/ \\______/ |_______/" + System.lineSeparator();
    private static final String COMMAND_KEYWORD_ASSERTION = "The key cannot be null"
            + ", check user-guide for valid commands";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COMMAND_INVALID_COMMAND_NOTE = "Supported commands in Focus Timer: " + LINE_SEPARATOR
            + "check command " + CheckCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "config command " + ConfigCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "next command " + NextCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "pause command " + PauseCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "resume command " + ResumeCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "start command " + StartCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "stop command " + StopCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "help command " + HelpCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "home command " + HomeCommand.COMMAND_USAGE;
    private static final String HOME_USAGE = "home command " + HomeCommand.COMMAND_USAGE;
    private static final String CONFIG_USAGE = "config command " + ConfigCommand.COMMAND_USAGE;
    private final FocusUi focusUi;
    private final Session session;

    /**
     * Constructs a FocusManager object.
     * Initialise a session and textUi.
     * Session will be passed into different commands to be utilised.
     */
    public FocusManager() {
        this.focusUi = new FocusUi();
        this.focusUi.setCursorName(FEATURE_NAME);
        this.session = new Session();
    }

    /**
     * Parses the given command from the user.
     * Determines the correct Command subclass that can handle its execution.
     *
     * @param commandString Full command issued by the user
     * @return Command object that can execute the user's command
     * @throws BadCommandException If an unknown command was issued by the user
     */
    private Command getCommandFor(String commandString) throws BadCommandException {
        HashMap<String, String> arguments = getCommandParser().parseUserInput(commandString);
        String commandKeyword = getCommandParser().getMainArgument(commandString);
        assert commandKeyword != null : COMMAND_KEYWORD_ASSERTION;
        switch (commandKeyword) {
        case START_COMMAND_KEYWORD:
            return new StartCommand(arguments, session);
        case PAUSE_COMMAND_KEYWORD:
            return new PauseCommand(arguments, session);
        case RESUME_COMMAND_KEYWORD:
            return new ResumeCommand(arguments, session);
        case HOME_COMMAND_KEYWORD:
            return new HomeCommand(arguments, session);
        case STOP_COMMAND_KEYWORD:
            return new StopCommand(arguments, session);
        case CHECK_COMMAND_KEYWORD:
            return new CheckCommand(arguments, session);
        case NEXT_COMMAND_KEYWORD:
            return new NextCommand(arguments, session);
        case CONFIG_COMMAND_KEYWORD:
            return new ConfigCommand(arguments, session);
        case HELP_COMMAND_KEYWORD:
            return new HelpCommand(arguments);
        default:
            throw new BadCommandException(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private void greet() {
        focusUi.printLogoWithSeparator(FOCUS_GREETING_LOGO);
        focusUi.printOutputMessage(FOCUS_TIMER_GREET);
    }

    private void runCommands() {
        boolean isExit = false;
        while (!isExit) {
            // Ignore ALL user input if the command is in its printing phase
            try {
                String commandString = focusUi.getCommand(session);
                // Edge case guard clause to ensure that
                Command command = getCommandFor(commandString);
                command.execute();
                isExit = HomeCommand.isExit(command);
            } catch (WellNusException exception) {
                String errorMessage = exception.getMessage();
                if (errorMessage.contains(CONFIG_COMMAND_KEYWORD)) {
                    focusUi.printErrorFor(exception, CONFIG_USAGE);
                } else if (errorMessage.contains(HOME_COMMAND_KEYWORD)) {
                    focusUi.printErrorFor(exception, HOME_USAGE);
                } else {
                    focusUi.printErrorFor(exception, COMMAND_INVALID_COMMAND_NOTE);
                }
            }
        }
    }

    /**
     * Utility function to get the featureName this Manager is administering.
     *
     * @return name of the feature that this Manager handles
     */
    @Override
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    /**
     * runEventDriver is the entry point into a feature's driver loop.
     */
    @Override
    public void runEventDriver() throws BadCommandException {
        greet();
        runCommands();
    }

    /**
     * Public method used for testing FocusManager's handling of invalidCommands.
     *
     * @param userCommand simulated user input
     * @return Command object that can execute the user's command
     * @throws BadCommandException
     */
    public Command testInvalidCommand(String userCommand) throws BadCommandException {
        String startCommand = "focusStart";
        String pauseCommand = "focusPause";
        String resumeCommand = "focusResume";
        String homeCommand = "focusHome";
        String stopCommand = "focusStop";
        String checkCommand = "focusCheck";
        HashMap<String, String> arguments;
        switch (userCommand) {
        case START_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(startCommand);
            return new StartCommand(arguments, session);
        case PAUSE_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(pauseCommand);
            return new PauseCommand(arguments, session);
        case RESUME_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(resumeCommand);
            return new ResumeCommand(arguments, session);
        case HOME_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(homeCommand);
            return new HomeCommand(arguments, session);
        case STOP_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(stopCommand);
            return new StopCommand(arguments, session);
        case CHECK_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(checkCommand);
            return new CheckCommand(arguments, session);
        case CONFIG_COMMAND_KEYWORD:
            arguments = getCommandParser().parseUserInput(checkCommand);
            return new ConfigCommand(arguments, session);
        default:
            throw new BadCommandException(UNKNOWN_COMMAND_MESSAGE);
        }
    }
}
