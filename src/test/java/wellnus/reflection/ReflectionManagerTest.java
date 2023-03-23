package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.Command;
import wellnus.exception.BadCommandException;

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

    // Test whether exceptions are thrown for empty string for commandType.
    @Test
    void setCommandType_emptyString_expectException() {
        ReflectionManager reflectionManager = new ReflectionManager();
        String[] input = EMPTY_STRING.split(" ");
        System.out.println(input.length);
        assertThrows(BadCommandException.class, (
        ) -> reflectionManager.setCommandType(EMPTY_STRING));
    }

    // Test whether exceptions are thrown for empty string for argument-payload pairs.
    @Test
    void setArgumentPayload_emptyCommand_expectException() {
        ReflectionManager reflectionManager = new ReflectionManager();
        assertThrows(BadCommandException.class, (
        ) -> reflectionManager.setArgumentPayload(EMPTY_STRING));
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
    
}

