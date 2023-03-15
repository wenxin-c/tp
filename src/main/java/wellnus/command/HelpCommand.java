package wellnus.command;

import wellnus.common.MainManager;
import wellnus.exception.BadCommandException;
import wellnus.manager.Manager;
import wellnus.ui.TextUi;

import java.util.HashMap;

/**
 * Implementation of WellNus' <code>help</code> command. Explains to the user what commands are supported
 *   by WellNus and how to use each command.
 */
public class HelpCommand extends Command {
    private static final String BAD_COMMAND_MESSAGE = "Invalid arguments given for %s command";
    private static final String BAD_COMMAND_ADVICE_MESSAGE = "Try 'help' for a list of "
            + "commands/features supported by WellNUS++";
    private static final String COMMAND_ARGUMENTS = "<feature>";
    private static final String COMMAND_BRIEF_DESCRIPTION = "Lists all features supported by WellNUS++, or all " +
            "commands supported by a specific feature";
    private static final String COMMAND_DETAILED_DESCRIPTION = "";
    private static final String COMMAND_INVALID_KEYWORD_MESSAGE = "Wrong command for 'help'";
    private static final String COMMAND_KEYWORD = "help";
    private static final String NO_FEATURE_KEYWORD = "";
    // TODO: Refactor this out as an atribute/method in MainManager instead
    private static final String UNKNOWN_FEATURE_MESSAGE = "Unsupported feature '%s'";
    private final MainManager mainManager;
    private final TextUi textUi;

    public HelpCommand(HashMap<String, String> arguments, MainManager mainManager)
            throws BadCommandException {
        super(arguments);
        this.mainManager = mainManager;
        this.textUi = new TextUi();
    }

    private String getBriefDescription() {
        return HelpCommand.COMMAND_BRIEF_DESCRIPTION;
    }

    /*
     * Checks the command arguments for this help command to determine if
     *   the user wants an overall description of WellNus's supported features
     *   or a detailed description of a specific feature.
     *
     * @return boolean Whether the user just wants an overall (brief) description of WellNus's features
     */
    private boolean isBriefHelp() {
        String featureKeywordIfExists = super.getArguments().get(this.getCommandKeyword());
        return featureKeywordIfExists.isBlank();
    }

    private MainManager getMainManager() {
        return this.mainManager;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private void printBriefHelp() {
        String appBriefDescription = this.getMainManager().getBriefDescription();
        this.getTextUi().printOutputMessage(appBriefDescription);
    }

    private void printDetailedHelp(String featureKeyword) {
        // We already check that the feature is supported in execute(), getManagerFor() cannot return
        //     an empty Optional
        Manager featureManager = this.getMainManager().getManagerFor(featureKeyword).get();
        String helpCommandDetailedDescription = featureManager.getFullDescription();
        this.getTextUi().printOutputMessage(helpCommandDetailedDescription);
    }

    @Override
    protected String getCommandKeyword() {
        return HelpCommand.COMMAND_KEYWORD;
    }

    @Override
    protected String getDetailedDescription() {
        return HelpCommand.COMMAND_DETAILED_DESCRIPTION;
    }

    @Override
    protected String getFeatureKeyword() {
        return HelpCommand.NO_FEATURE_KEYWORD;
    }

    @Override
    protected String getSupportedCommandArguments() {
        return HelpCommand.COMMAND_ARGUMENTS;
    }

    /**
     * Executes the issued help command.<br>
     *
     * Prints a brief description of all of WellNus' supported commands if
     *   the basic 'help' command was issued.<br>
     *
     * Prints a detailed description of a specific feature if the specialised
     *   'help' command was issued.
     */
    @Override
    public void execute() {
        try {
            validateCommand(super.getArguments());
        } catch (BadCommandException badCommandException) {
            this.getTextUi().printErrorFor(badCommandException,
                    HelpCommand.BAD_COMMAND_ADVICE_MESSAGE);
            return;
        }
        if (isBriefHelp()) {
            this.printBriefHelp();
        } else {
            String featureKeyword = super.getArguments().get(HelpCommand.COMMAND_KEYWORD);
            this.printDetailedHelp(featureKeyword);
        }
    }

    /**
     * Checks whether the given arguments are valid for our help command.
     *
     * @param arguments Argument-Payload map generated by CommandParser using user's command
     * @throws BadCommandException If the command is invalid
     */
    @Override
    public void validateCommand(HashMap<String, String> arguments) throws BadCommandException {
        if (!arguments.containsKey(HelpCommand.COMMAND_KEYWORD)) {
            throw new BadCommandException(COMMAND_INVALID_KEYWORD_MESSAGE);
        }
        String featureKeyword = arguments.get(HelpCommand.COMMAND_KEYWORD);
        boolean isFeatureSupported = this.getMainManager().isSupportedFeature(featureKeyword);
        if (!featureKeyword.isBlank() && !isFeatureSupported) {
            throw new BadCommandException(String.format(HelpCommand.UNKNOWN_FEATURE_MESSAGE,
                    featureKeyword));
        }
    }
}