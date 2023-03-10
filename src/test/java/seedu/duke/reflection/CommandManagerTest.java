package seedu.duke.reflection;

import org.junit.jupiter.api.Test;
import seedu.duke.command.BadCommandException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CommandManagerTest {
    private static final String EMPTY_STRING = "";
    private static final String GET_COMMAND = "get";
    private static final String INVALID_COMMAND = "test";

    // Test whether exceptions are thrown for invalid command
    @Test
    void execution_invalidCommand_expectException() {
        assertThrows(InvalidCommandException.class,
                () -> CommandManager.execute(INVALID_COMMAND));
    }

    // Test whether exceptions are thrown for empty string, buggy at this moment, to be fixed.
    @Test
    void getCommandType_emptyString_expectException() {
        String[] input = EMPTY_STRING.split(" ");
        System.out.println(input.length);
        assertThrows(BadCommandException.class,
                () -> CommandManager.setCommandType(EMPTY_STRING));
    }

    // Test whether argument_payload pair is properly generated.
    @Test
    void setArgumentPayload_singleCommand_expectEmptyPayload() throws BadCommandException {
        CommandManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> argumentPayload = CommandManager.getArgumentPayload();
        String value = argumentPayload.get(GET_COMMAND);
        assertEquals(EMPTY_STRING, value);
    }
}


