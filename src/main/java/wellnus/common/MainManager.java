package wellnus.common;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.manager.Manager;
import wellnus.ui.TextUi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MainManager extends Manager {
    private static final String GREETING_MESSAGE = "Enter a command to start using WellNUS++! Try 'help' "
            + "if you're new, or just unsure.";
    private static final String INVALID_COMMAND_MESSAGE = "Don't recognise that command?";
    private static final String INVALID_COMMAND_ADDITIONAL_MESSAGE = "Try 'help' for some guidance";
    private static final String WELLNUS_FEATURE_NAME = "";
    private final TextUi textUi;

    public MainManager() {
        this.textUi = new TextUi();
    }

    private static String getBriefAppDescription() {
        return "WellNUS++ helps you keep track and improve your physical and mental wellness.";
    }

    private static String getLongAppDescription() {
        return "WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to "
                + "keep track and improve their physical and mental wellness in various aspects."
                + " If you can type fast, WellNUS++ can update their wellness progress faster than "
                + "traditional Graphical User Interface(GUI) apps.";
    }

    /**
     * Continuously reads user's commands and executes those that are supported
     *     by WellNUS++ until the `exit` command is given.<br>
     *
     * If an unrecognised command is given, a warning is printed on the user's screen.
     */
    private void executeCommands() {
        boolean isExit = false;
        CommandParser parser = new CommandParser();
        while (!isExit) {
            String NO_ADDITIONAL_MESSAGE = "";
            try {
                String nextCommand = this.getTextUi().getCommand();
                String featureKeyword = parser.getMainArgument(nextCommand);
                HashMap<String, String> arguments = parser.parseUserInput(nextCommand);
                Optional<Manager> featureManager = this.getManagerFor(featureKeyword);
                if (featureManager.isEmpty() && !this.isSupportedCommand(featureKeyword)) {
                    BadCommandException badCommandException =
                            new BadCommandException(MainManager.INVALID_COMMAND_MESSAGE);
                    this.getTextUi().printErrorFor(badCommandException,
                            MainManager.INVALID_COMMAND_ADDITIONAL_MESSAGE);
                }
                // TODO: Replace with Command subclass once those changes are merged
                if (featureKeyword.equals("exit")) {
                    isExit = true;
                }
            } catch (BadCommandException badCommandException) {
                this.getTextUi().printErrorFor(badCommandException, NO_ADDITIONAL_MESSAGE);
            }
        }
    }

    private Optional<Manager> getManagerFor(String featureKeyword) {
        for (Manager featureManager : this.getSupportedFeatureManagers()) {
            if (featureManager.getFeatureName().equals(featureKeyword)) {
                return Optional.of(featureManager);
            }
        }
        return Optional.empty();
    }

    private List<Manager> getSupportedFeatureManagers() {
        return new ArrayList<>();
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private void greet() {
        this.getTextUi().printOutputMessage(MainManager.GREETING_MESSAGE);
    }

    private boolean isSupportedCommand(String commandKeyword) {
        // TODO: Implement proper, extensible logic for checking command validity
        //     when all Command subclasses are defined
        return commandKeyword.equals("exit");
    }

    /**
     * Returns a brief description of the WellNUS++ app
     *
     * @return Brief description of this app
     */
    @Override
    public String getBriefDescription() {
        return MainManager.getBriefAppDescription();
    }

    /**
     * Returns the name of this feature. In this case, it's just empty(not any particular feature).
     *
     * @return Empty String to imply that this Manager is not associated with any feature
     */
    @Override
    public String getFeatureName() {
        return WELLNUS_FEATURE_NAME;
    }

    /**
     * Returns a full description of the WellNUS++ app.
     *
     * @return Full description of the app
     */
    @Override
    public String getFullDescription() {
        return MainManager.getLongAppDescription();
    }

    /**
     * Executes the basic commands(e.g. <code>help</code>) as well as any feature-specific
     *     commands, which are delegated to the corresponding features' Managers.<br>
     * <br>
     * This method will keep reading the user's command until the exit command is given.<br>
     *
     * @throws BadCommandException If an unrecognised command was issued by the user
     */
    @Override
    public void runEventDriver() {
        this.greet();
        this.executeCommands();
    }

    /**
     * Utility function to set a list of main commands the feature supports <br>
     * <br>
     * Suggested implementation: <br>
     * <code> this.supportedCommands.add([cmd1, cmd2, ...]); </code>
     */
    @Override
    protected void setSupportedCommands() {
        // TODO: Implement once basic Command subclasses are in
        // Unable to implement this now since Command subclasses don't exist
    }

    /**
     * Utility function to set a list of main commands the feature supports <br>
     * <br>
     * Suggested implementation: <br>
     * <code> this.supportedManagers.add([mgr1, mgr2, ...]); </code>
     */
    @Override
    protected void setSupportedFeatureManagers() {
        // TODO: Implement once all Managers are in
        // e.g. super.getSupportedFeatureManagers().add(new AtomicHabitManager());
    }

}
