package wellnus.focus.feature;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.focus.command.CheckCommand;
import wellnus.focus.command.ConfigCommand;
import wellnus.focus.command.HomeCommand;
import wellnus.focus.command.NextCommand;
import wellnus.focus.command.PauseCommand;
import wellnus.focus.command.ResumeCommand;
import wellnus.focus.command.StartCommand;
import wellnus.focus.command.StopCommand;
import wellnus.manager.Manager;
import wellnus.ui.TextUi;

/**
 * Represents a class to run the event driver for the Focus Timer.
 * This class will be called by the main manager.
 * It will match the user input to the correct command and execute it.
 */
//@@author YongbinWang
public class FocusManager extends Manager {
    public static final String FEATURE_HELP_DESCRIPTION = "Focus Timer (ft) - Set a configurable timer "
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
    private static final String UNKNOWN_COMMAND_MESSAGE = "No such command in focus timer!";
    private static final String FOCUS_TIMER_GREET = "Welcome to Focus Timer.\n"
            + "Start a focus session with `start`, or `config` the session first!";
    private static final String COMMAND_KEYWORD_ASSERTION = "The key cannot be null"
            + ", check user-guide for valid commands";
    private static final String ERROR_SESSION_RUNNING = "Sorry, you cant `start` or `config` a ";
    private final TextUi textUi;
    private final Session session;

    /**
     * Constructs a FocusManager object.
     * Initialise a session and textUi.
     * Session will be passed into different commands to be utilised.
     */
    public FocusManager() {
        this.textUi = new TextUi();
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
        default:
            throw new BadCommandException(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private void greet() {
        textUi.printOutputMessage(FOCUS_TIMER_GREET);
    }

    private void runCommands() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = textUi.getCommand();
                Command command = getCommandFor(commandString);
                command.execute();
                isExit = HomeCommand.isExit(command);
            } catch (BadCommandException exception) {
                String noAdditionalMessage = "";
                textUi.printErrorFor(exception, noAdditionalMessage);
            } catch (WellNusException exception) {
                textUi.printErrorFor(exception, "Check user guide for valid commands!");
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
     * Abstract function to ensure developers add in a getter for the feature's help description.
     * <p>
     * This description will be shown when the user types in the help command. <br>
     * The description should be a brief overview of what the feature does. <br>
     * For example: <br>
     * "reflect: Reflect is your go-to tool to get, save and reflect on our specially
     * curated list of questions to reflect on"
     *
     * @return String of the feature's help description
     */
    @Override
    public String getFeatureHelpDescription() {
        return null;
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
