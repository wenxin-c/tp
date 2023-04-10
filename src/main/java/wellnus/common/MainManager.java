package wellnus.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import wellnus.atomichabit.feature.AtomicHabitManager;
import wellnus.command.Command;
import wellnus.command.CommandParser;
import wellnus.command.ExitCommand;
import wellnus.command.HelpCommand;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.focus.feature.FocusManager;
import wellnus.gamification.GamificationManager;
import wellnus.manager.Manager;
import wellnus.reflection.feature.ReflectionManager;
import wellnus.ui.TextUi;

/**
 * MainManager is the primary event driver for WellNUS++ <br>
 * <br>
 * MainManager creates and stores exactly one instance of each feature's Manager in WellNUS++.
 * <p>
 * It runs an event driver, matches user input to the selected feature
 * and executes its instance to launch the feature Manager.
 */
public class MainManager extends Manager {
    public static final String FEATURE_HELP_DESCRIPTION = "WellNUS++ is a Command Line Interface (CLI)"
            + " app for you to keep track, manage and improve your physical and mental wellness.";
    protected static final String EXIT_COMMAND_KEYWORD = "exit";
    protected static final String HELP_COMMAND_KEYWORD = "help";
    private static final String COMMAND_IS_BLANK_MESSAGE = "Command is blank - please check user input code for "
            + "MainManager.";
    private static final String COMMAND_IS_NULL_MESSAGE = "Command is null - please check user input code for "
            + "MainManager.";
    private static final String FEATURE_NAME = "main";
    private static final String GREETING_MESSAGE = "Enter a command to start using WellNUS++! Try 'help' "
            + "if you're new, or just unsure.";
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command issued!";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INVALID_COMMAND_ADDITIONAL_MESSAGE =
            "Supported features: " + LINE_SEPARATOR
            + "Access Atomic Habit: hb" + LINE_SEPARATOR
            + "Access Self Reflection: reflect" + LINE_SEPARATOR
            + "Access Focus Timer: ft" + LINE_SEPARATOR
            + "Access Gamification: gamif" + LINE_SEPARATOR
            + "Help command: help" + LINE_SEPARATOR
            + "Exit program: exit";
    private static final String INVALID_FEATURE_KEYWORD_MESSAGE = "Feature keyword can't be empty dear";
    private static final int NUM_OF_ARGUMENTS = 1;
    private static final String INVALID_ARGUMENTS_MESSAGE = "Invalid arguments given to '%s'!";
    private static final String UNNECESSARY_PAYLOAD_MESSAGE = "Invalid payload given to '%s', drop the '%s' "
            + "and try again!";
    private static final String WELLNUS_FEATURE_NAME = "";
    private final ArrayList<Manager> featureManagers;
    private boolean hasExecutedCommands = false;
    private final TextUi textUi;

    /**
     * Constructs an instance of MainManager. <br>
     * Instantiates boilerplate utilities like TextUi
     * and populates featureManagers with exactly one instance to be executed on user selection.
     */
    public MainManager() {
        super();
        this.featureManagers = new ArrayList<>();
        this.textUi = new TextUi();
        this.textUi.setCursorName(FEATURE_NAME);
    }

    /**
     * Parses the given command String issued by the user and returns the corresponding
     * Command object that can execute it.
     *
     * @param command Command issued by the user
     * @return Command object that can execute the user's command
     * @throws BadCommandException If command issued is not supported or invalid
     */
    protected Command getMainCommandFor(String command) throws BadCommandException {
        String commandKeyword = getCommandParser().getMainArgument(command);
        HashMap<String, String> arguments = getCommandParser().parseUserInput(command);
        switch (commandKeyword) {
        case MainManager.HELP_COMMAND_KEYWORD:
            return new HelpCommand(arguments);
        case MainManager.EXIT_COMMAND_KEYWORD:
            return new ExitCommand(arguments);
        default:
            throw new BadCommandException(MainManager.INVALID_COMMAND_MESSAGE);
        }
    }

    protected Optional<Manager> getManagerFor(String featureKeyword) {
        assert (featureKeyword != null && !featureKeyword.isBlank())
                : MainManager.INVALID_FEATURE_KEYWORD_MESSAGE;
        for (Manager featureManager : this.getSupportedFeatureManagers()) {
            if (featureManager.getFeatureName().equals(featureKeyword)) {
                return Optional.of(featureManager);
            }
        }
        return Optional.empty();
    }

    /**
     * Continuously reads user's commands and executes those that are supported
     * by WellNUS++ until the `exit` command is given.<br>
     * <p>
     * If an unrecognised command is given, a warning is printed on the user's screen.
     */
    private void executeCommands() {
        if (!hasExecutedCommands) {
            this.setSupportedFeatureManagers();
            hasExecutedCommands = true;
        }
        boolean isExit = false;
        CommandParser parser = new CommandParser();
        while (!isExit) {
            try {
                String nextCommand = this.getTextUi().getCommand();
                validate(nextCommand);
                // nextCommand now guaranteed to be a supported feature/main command
                String featureKeyword = parser.getMainArgument(nextCommand);
                Optional<Manager> featureManager = this.getManagerFor(featureKeyword);
                // User issued a feature keyword, pass control to the corresponding feature's Manager
                featureManager.ifPresent((manager) -> {
                    try {
                        manager.runEventDriver();
                    } catch (BadCommandException badCommandException) {
                        this.getTextUi().printErrorFor(badCommandException, INVALID_COMMAND_ADDITIONAL_MESSAGE);
                    }
                });
                // User issued a main command, e.g. 'help'
                if (featureManager.isEmpty()) {
                    Command mainCommand = this.getMainCommandFor(nextCommand);
                    mainCommand.execute();
                    isExit = ExitCommand.isExit(mainCommand);
                }
            } catch (BadCommandException badCommandException) {
                this.getTextUi().printErrorFor(badCommandException, MainManager.INVALID_COMMAND_ADDITIONAL_MESSAGE);
            } catch (WellNusException exception) {
                this.getTextUi().printErrorFor(exception, INVALID_COMMAND_ADDITIONAL_MESSAGE);
            }
        }
        // We are about to quit WellNUS++. Close the log file used in this session
        WellNusLogger.closeLogFile();
    }

    private List<String> getSupportedCommandKeywords() {
        List<String> commandKeywords = new ArrayList<>();
        commandKeywords.add(MainManager.HELP_COMMAND_KEYWORD);
        commandKeywords.add(MainManager.EXIT_COMMAND_KEYWORD);
        return commandKeywords;
    }

    private List<Manager> getSupportedFeatureManagers() {
        return this.featureManagers;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private void greet() {
        this.getTextUi().printOutputMessage(MainManager.GREETING_MESSAGE);
    }

    private boolean isSupportedCommand(String commandKeyword) {
        List<String> cmdKeywords = this.getSupportedCommandKeywords();
        for (String cmdKeyword : cmdKeywords) {
            if (commandKeyword.equals(cmdKeyword)) {
                return true;
            }
        }
        return false;
    }

    private void validate(String command) throws BadCommandException {
        assert command != null : MainManager.COMMAND_IS_NULL_MESSAGE;
        assert !command.isBlank() : MainManager.COMMAND_IS_BLANK_MESSAGE;
        String featureKeyword = commandParser.getMainArgument(command);
        Optional<Manager> featureManager = this.getManagerFor(featureKeyword);
        // User gave a command that's not any feature's keyword nor a recognised main command
        if (featureManager.isEmpty() && !this.isSupportedCommand(featureKeyword)) {
            throw new BadCommandException(MainManager.INVALID_COMMAND_MESSAGE);
        }
        HashMap<String, String> arguments = commandParser.parseUserInput(command);
        if (arguments.size() > NUM_OF_ARGUMENTS) {
            throw new BadCommandException(String.format(MainManager.INVALID_ARGUMENTS_MESSAGE,
                    featureKeyword));
        }
        String argumentPayload = arguments.get(featureKeyword);
        if (!featureKeyword.equals(HELP_COMMAND_KEYWORD) && !argumentPayload.isBlank()) {
            throw new BadCommandException(String.format(MainManager.UNNECESSARY_PAYLOAD_MESSAGE,
                    featureKeyword, argumentPayload));
        }
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
     * Executes the basic commands(e.g. <code>help</code>) as well as any feature-specific
     * commands, which are delegated to the corresponding features' Managers.<br>
     * <br>
     * This method will keep reading the user's command until the exit command is given.
     */
    @Override
    public void runEventDriver() {
        this.greet();
        this.executeCommands();
    }

    /**
     * Returns a list of features supported by WellNUS++. <br>
     * <br>
     * Suggested implementation: <br>
     * <code> this.supportedManagers.add([mgr1, mgr2, ...]); </code>
     */
    protected void setSupportedFeatureManagers() {
        GamificationManager gamificationManager = new GamificationManager();
        this.getSupportedFeatureManagers().add(gamificationManager);
        this.getSupportedFeatureManagers().add(
                new AtomicHabitManager(gamificationManager.getGamificationData()));
        this.getSupportedFeatureManagers().add(new ReflectionManager());
        this.getSupportedFeatureManagers().add(new FocusManager());
    }

}
