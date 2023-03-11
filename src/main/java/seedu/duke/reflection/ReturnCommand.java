package seedu.duke.reflection;

import seedu.duke.command.Command;
import seedu.duke.exception.BadCommandException;

import java.util.HashMap;

public class ReturnCommand extends Command {
    private static final String FEATURE_NAME = "Self Reflection";
    private static final String COMMAND_KEYWORD = "return";
    private static final String FULL_DESCRIPTION = "";
    private static final String ARGUMENT = "";

    public ReturnCommand(HashMap<String, String> arguments) throws BadCommandException {
        super(arguments);
    }

    /**
     * To check, this keyword refers to command itself or with a short description?
     *
     * @return
     */
    @Override
    protected String getCommandKeyword() {
        return COMMAND_KEYWORD;
    }

    @Override
    protected String getDetailedDescription() {
        return FULL_DESCRIPTION;
    }

    @Override
    protected String getFeatureKeyword() {
        return FEATURE_NAME;
    }

    @Override
    protected String getSupportedCommandArguments() {
        return ARGUMENT;
    }

    /**
     * Return back to WellNUS++ main interface
     */
    @Override
    public void execute() {
        SelfReflection.setIsExit();
    }
}


