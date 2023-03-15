package wellnus.reflection;

import org.junit.jupiter.api.Test;
import wellnus.command.Command;
import wellnus.exception.BadCommandException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReflectionManagerTest {
    private static final String EMPTY_STRING = "";
    private static final String GET_COMMAND = "get";
    private static final String INVALID_COMMAND = "test";

    // Test whether exceptions are thrown for invalid command
    @Test
    void execution_invalidCommand_expectException() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setCommandType(INVALID_COMMAND);
        assertThrows(BadCommandException.class,
                reflectionManager::executeCommands);
    }

    // Test whether exceptions are thrown for empty string, buggy at this moment, to be fixed.
    @Test
    void getCommandType_emptyString_expectException() {
        ReflectionManager reflectionManager = new ReflectionManager();
        String[] input = EMPTY_STRING.split(" ");
        System.out.println(input.length);
        assertThrows(BadCommandException.class,
                () -> reflectionManager.setCommandType(EMPTY_STRING));
    }

    // Test whether argument_payload pair is properly generated.
    @Test
    void setArgumentPayload_singleCommand_expectEmptyPayload() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> argumentPayload = reflectionManager.getArgumentPayload();
        String value = argumentPayload.get(GET_COMMAND);
        assertEquals(EMPTY_STRING, value);
    }

    // Test whether supported commands are properly set up.
    @Test
    void setSupportedCommands_checkFirstCommand_expectGetCmd() {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setSupportedCommands();
        ArrayList<Command> supportedCommands = reflectionManager.getSupportedCommands();
        assertEquals(true, supportedCommands.get(0) instanceof GetCommand);
    }
}

