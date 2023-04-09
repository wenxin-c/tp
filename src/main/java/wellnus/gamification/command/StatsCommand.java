package wellnus.gamification.command;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.gamification.util.GamificationData;
import wellnus.gamification.util.GamificationUi;

/**
 * Provides the 'stats' command for the gamification feature that displays the user's XP statistics, such
 * as current XP level.
 */
public class StatsCommand extends Command {
    public static final String COMMAND_DESCRIPTION = "stats - Displays the user's XP level and points";
    public static final String COMMAND_KEYWORD = "stats";
    public static final String COMMAND_USAGE = "usage: stats";
    public static final String FEATURE_NAME = "gamif";
    private static final String INVALID_PAYLOAD = "Invalid payload given to 'stats'!";
    private static final int NUM_OF_ARGUMENTS = 1;
    private static final String WRONG_ARGUMENTS_MESSAGE = "Invalid arguments given to 'stats'!";
    private static final String WRONG_COMMAND_MESSAGE = "Invalid command issued, expected 'stats'!";
    private final GamificationData gamData;

    /**
     * Returns an instance of the StatsCommand Object to handle the 'stats' command from the user
     *
     * @param arguments Arguments given by the user
     */
    public StatsCommand(HashMap<String, String> arguments, GamificationData gamData) {
        super(arguments);
        this.gamData = gamData;
    }

    /**
     * Returns the keyword for the stats command.
     *
     * @return String Keyword of the stats command
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
     * Prints the user's XP statistics on the user's screen.
     * @throws WellNusException If XP statistics cannot be successfully printed
     * @see GamificationUi#printXpBar(GamificationData, boolean)
     */
    @Override
    public void execute() throws WellNusException {
        validateCommand(getArguments());
        GamificationUi.printXpBar(gamData, true);
    }

    /**
     * Validates the arguments given for the stats command.
     *
     * @param arguments Arguments given by the user
     * @throws BadCommandException If the arguments are invalid for this stats command
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        assert arguments.containsKey(getCommandKeyword()) : WRONG_COMMAND_MESSAGE;
        if (arguments.size() > NUM_OF_ARGUMENTS) {
            throw new BadCommandException(WRONG_ARGUMENTS_MESSAGE);
        }
        String payload = arguments.get(getCommandKeyword());
        if (!payload.isBlank()) {
            throw new BadCommandException(INVALID_PAYLOAD);
        }
    }

    /**
     * Returns the syntax for this stats command.
     *
     * @return String of the stats command's syntax
     */
    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    /**
     * Returns a description of this stats command.
     *
     * @return String of the description of this stats command
     */
    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
