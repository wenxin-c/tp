package wellnus.focus.feature;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.focus.command.CheckCommand;
import wellnus.focus.command.HomeCommand;
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
public class FocusManager extends Manager {
    public static final String FEATURE_NAME = "ft";
    private static final String FEATURE_BRIEF_DESCRIPTION = "Users can set a timer to focus on a task.";
    private static final String FEATURE_FULL_DESCRIPTION = "";
    private static final String START_COMMAND_KEYWORD = "start";
    private static final String PAUSE_COMMAND_KEYWORD = "pause";
    private static final String RESUME_COMMAND_KEYWORD = "resume";
    private static final String CONFIG_COMMAND_KEYWORD = "config";
    private static final String HOME_COMMAND_KEYWORD = "home";
    private static final String STOP_COMMAND_KEYWORD = "stop";
    private static final String CHECK_COMMAND_KEYWORD = "check";
    private static final String UNKNOWN_COMMAND_MESSAGE = "No such command in focus timer!";
    private static final String FOCUS_TIMER_GREET = "Welcome to Focus Timer";
    private static final String COMMAND_KEYWORD_ASSERTION = "The key cannot be null"
            + ", check user-guide for valid commands";

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
     * Utility function to get the featureName this Manager is administering
     *
     * @return name of the feature that this Manager handles
     */
    @Override
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    /**
     * Utility function to get a summary description of the feature this Manager is administering
     *
     * @return summary description of the feature that this Manager handles
     */
    @Override
    public String getBriefDescription() {
        return FEATURE_BRIEF_DESCRIPTION;
    }

    /**
     * Utility function to get the full description of the feature this Manager is administering
     *
     * @return full description of the feature that this Manager handles
     */
    @Override
    public String getFullDescription() {
        return FEATURE_FULL_DESCRIPTION;
    }

    /**
     * Utility function to set a list of main commands the feature supports <br>
     * <br>
     * Suggested implementation: <br>
     * <code> this.supportedCommands.add([cmd1, cmd2, ...]); </code>
     */
    @Override
    protected void setSupportedCommands() {

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
        default:
            throw new BadCommandException(UNKNOWN_COMMAND_MESSAGE);
        }

    }

}
