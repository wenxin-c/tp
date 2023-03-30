package wellnus.gamification;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.gamification.command.HelpCommand;
import wellnus.gamification.command.HomeCommand;
import wellnus.gamification.command.StatsCommand;
import wellnus.gamification.util.GamificationData;
import wellnus.gamification.util.GamificationUi;
import wellnus.manager.Manager;
import wellnus.ui.TextUi;

/**
 * Manager for the gamification feature. Entry point for this class is the runEventDriver() method.
 */
public class GamificationManager extends Manager {
    public static final String FEATURE_NAME = "gamif";
    public static final String FEATURE_HELP_DESCRIPTION = "Gamification (gamif) - Gamification gives you the "
            + "motivation to continue improving your wellness by rewarding you for your efforts!";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_HOME = "home";
    private static final String COMMAND_STATS = "stats";
    private static final String UNRECOGNISED_COMMAND_ERROR = "Unrecognised command %s, see 'help' on our available "
            + "commands";
    private final GamificationData gamificationData;
    private final TextUi textUi;

    /**
     * Returns an instance of the GamificationManager.
     */
    public GamificationManager() {
        this.gamificationData = new GamificationData();
        this.textUi = new TextUi();
    }

    private Command getCommandFor(String command) throws BadCommandException {
        HashMap<String, String> arguments = commandParser.parseUserInput(command);
        String cmdKeyword = commandParser.getMainArgument(command);
        switch (cmdKeyword) {
        case COMMAND_HELP:
            return new HelpCommand(arguments);
        case COMMAND_HOME:
            return new HomeCommand(arguments);
        case COMMAND_STATS:
            return new StatsCommand(arguments, gamificationData);
        default:
            throw new BadCommandException(String.format(UNRECOGNISED_COMMAND_ERROR, cmdKeyword));
        }
    }

    /**
     * Returns the name of this feature.
     *
     * @return Name of the feature that this Manager handles
     */
    @Override
    public String getFeatureName() {
        return FEATURE_NAME;
    }

    /**
     * Returns a description of the gamification feature for the 'help' command.
     *
     * @return String of the feature's help description
     */
    @Override
    public String getFeatureHelpDescription() {
        return FEATURE_HELP_DESCRIPTION;
    }

    public GamificationData getGamificationData() {
        return gamificationData;
    }

    /**
     * runEventDriver is the entry point into GamificationManager. <br>
     * <br>
     * It is calls the relevant methods to present the user with the gamification feature's interface
     * and manage the user's commands.
     *
     * @throws BadCommandException If an unrecognised command is given or invalid arguments are given for
     *                             a recognised command
     */
    @Override
    public void runEventDriver() throws BadCommandException {
        GamificationUi.printLogo();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = textUi.getCommand();
                HashMap<String, String> arguments = getCommandParser().parseUserInput(commandString);
                Command command = getCommandFor(commandString);
                command.execute();
                isExit = HomeCommand.isHome(command, arguments);
            } catch (WellNusException exception) {
                String noAdditionalMsg = "";
                textUi.printErrorFor(exception, noAdditionalMsg);
            }
        }
    }
}

