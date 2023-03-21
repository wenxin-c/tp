package wellnus.focus;


import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.manager.Manager;
import wellnus.ui.TextUi;

import java.util.HashMap;


public class FocusManager extends Manager {
    public static final String FEATURE_NAME = "ft";
    private static final String FEATURE_BRIEF_DESCRIPTION = "";
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

    private final TextUi textUi;
    private final Session session;

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
        case CONFIG_COMMAND_KEYWORD:
            //return new UpdateCommand(arguments, getHabitList());
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
            } catch (BadCommandException badCommandException) {
                String NO_ADDITIONAL_MESSAGE = "";
                textUi.printErrorFor(badCommandException, NO_ADDITIONAL_MESSAGE);
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
     * runEventDriver is the entry point into a feature's driver loop <br>
     * <br>
     * This should be the part that contains the infinite loop and switch cases,
     * but it is up to the implementer. <br>
     * Its implementation should include the following:
     * <li>A way to terminate runEventDriver</li>
     * <li>A way to read input from console</li>
     */
    @Override
    public void runEventDriver() throws BadCommandException {
        greet();
        runCommands();
    }
}
