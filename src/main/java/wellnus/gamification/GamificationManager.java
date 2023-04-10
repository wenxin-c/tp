package wellnus.gamification;

import java.util.HashMap;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;
import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.exception.WellNusException;
import wellnus.gamification.command.HelpCommand;
import wellnus.gamification.command.HomeCommand;
import wellnus.gamification.command.StatsCommand;
import wellnus.gamification.util.GamificationData;
import wellnus.gamification.util.GamificationStorage;
import wellnus.gamification.util.GamificationUi;
import wellnus.manager.Manager;

/**
 * Manager for the gamification feature. Entry point for this class is the runEventDriver() method.
 */
public class GamificationManager extends Manager {
    public static final String FEATURE_NAME = "gamif";
    public static final String FEATURE_HELP_DESCRIPTION = "gamif - Gamification - Gamification gives you the "
            + "motivation to continue improving your wellness by rewarding you for your efforts!";
    private static final String CLEAN_DATA_FILE_ERROR_MESSAGE = "Gamification data file may remain corrupted.";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_HOME = "home";
    private static final String COMMAND_STATS = "stats";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String UNRECOGNISED_COMMAND_ERROR = "Invalid command issued!";
    private static final String COMMAND_INVALID_COMMAND_NOTE =
            "Supported commands in Gamification: " + LINE_SEPARATOR
            + "stats command " + StatsCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "help command " + HelpCommand.COMMAND_USAGE + LINE_SEPARATOR
            + "home command " + HomeCommand.COMMAND_USAGE;
    private static final String STATS_USAGE = "stats command " + StatsCommand.COMMAND_USAGE;
    private static final String HOME_USAGE = "home command " + HomeCommand.COMMAND_USAGE;
    private static final String LOAD_GAMIF_DATA_ERROR_MESSAGE = "Previous gamification data will not be restored.";
    private GamificationData gamificationData;
    private final GamificationUi gamificationUi;

    /**
     * Returns an instance of the GamificationManager.
     */
    public GamificationManager() {
        this.gamificationUi = new GamificationUi();
        try {
            GamificationStorage gamificationStorage = new GamificationStorage();
            this.gamificationData = gamificationStorage.loadData();
        } catch (StorageException loadDataException) {
            gamificationUi.printErrorFor(loadDataException, LOAD_GAMIF_DATA_ERROR_MESSAGE);
            this.gamificationData = new GamificationData();
        } catch (TokenizerException loadDataException) {
            gamificationUi.printErrorFor(loadDataException, LOAD_GAMIF_DATA_ERROR_MESSAGE);
            try {
                GamificationStorage gamificationStorage = new GamificationStorage();
                gamificationStorage.cleanDataFile();
            } catch (StorageException storageException) {
                gamificationUi.printErrorFor(storageException, CLEAN_DATA_FILE_ERROR_MESSAGE);
            }
            this.gamificationData = new GamificationData();
        }
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
            throw new BadCommandException(UNRECOGNISED_COMMAND_ERROR);
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

    public GamificationData getGamificationData() {
        return gamificationData;
    }

    /**
     * runEventDriver is the entry point into GamificationManager. <br>
     * <br>
     * It is calls the relevant methods to present the user with the gamification feature's interface
     * and manage the user's commands.
     */
    @Override
    public void runEventDriver() {
        GamificationUi.printLogo();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = gamificationUi.getCommand();
                Command command = getCommandFor(commandString);
                command.execute();
                isExit = HomeCommand.isHome(command);
            } catch (WellNusException exception) {
                String errorMessage = exception.getMessage();
                if (errorMessage.contains(COMMAND_STATS)) {
                    gamificationUi.printErrorFor(exception, STATS_USAGE);
                } else if (errorMessage.contains(COMMAND_HOME)) {
                    gamificationUi.printErrorFor(exception, HOME_USAGE);
                } else {
                    gamificationUi.printErrorFor(exception, COMMAND_INVALID_COMMAND_NOTE);
                }
            }
        }
    }
}

