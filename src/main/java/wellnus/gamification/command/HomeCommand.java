package wellnus.gamification.command;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.gamification.util.GamificationUi;

/**
 * Provides the 'home' command for the gamification feature.
 */
public class HomeCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "home - Returns the user to the main WellNus++ session";
    public static final String COMMAND_KEYWORD = "home";
    public static final String COMMAND_USAGE = "usage: home";
    public static final String FEATURE_NAME = "gamif";
    private static final int NUM_OF_ARGUMENTS = 1;
    private static final String WRONG_COMMAND_KEYWORD_MESSAGE = "Invalid command issued, expected 'home'!";
    private static final String WRONG_ARGUMENTS_MESSAGE = "Invalid arguments given to 'home'!";
    private static final String COMMAND_INVALID_PAYLOAD = "Invalid payload given to 'home'!";

    /**
     * Initialises a Command Object to handle the 'home' command from the user
     *
     * @param arguments Arguments issued by the user
     */
    public HomeCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    public static boolean isHome(Command command) {
        return command instanceof HomeCommand;
    }

    /**
     * Returns the home command's activation keyword.
     *
     * @return String Keyword of the home command
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    /**
     * Returns the keyword for the gamification feature.
     *
     * @return String Keyword for the gamification feature
     */
    @Override
    protected String getFeatureKeyword() {
        return FEATURE_NAME;
    }

    /**
     * Executes the 'home' command from the user to return to the main WellNus feature.<br>
     * <p>
     * May throw Exceptions if command fails.
     *
     * @throws WellNusException If the home command fails
     */
    @Override
    public void execute() throws WellNusException {
        validateCommand(getArguments());
        GamificationUi.printGoodbye();
    }

    /**
     * Validate the arguments given by the user. <br>
     *
     * @param arguments Arguments given by the user
     * @throws BadCommandException If the arguments have any issues
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        assert arguments.containsKey(getCommandKeyword()) : WRONG_COMMAND_KEYWORD_MESSAGE;
        if (arguments.size() > NUM_OF_ARGUMENTS) {
            throw new BadCommandException(WRONG_ARGUMENTS_MESSAGE);
        }
        String payload = arguments.get(getCommandKeyword());
        if (!payload.isBlank()) {
            throw new BadCommandException(COMMAND_INVALID_PAYLOAD);
        }
    }

    /**
     * Returns a description of the home command's syntax.
     *
     * @return String of the home command's syntax
     */
    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Returns a description of the home command's description.
     *
     * @return String of the description of this home command
     */
    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
