package wellnus.reflection.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.exception.BadCommandException;

// @@author wenxin-c
/**
 * Class to test different tests for `ReflectionManager` Class utilising JUnit tests.
 * Test cases will involve expected outputs and correct exception handling.
 */
class ReflectionManagerTest {
    private static final String EMPTY_STRING = "";
    private static final String GET_COMMAND = "get";
    private static final String INVALID_COMMAND = "test";
    private static final String SEPARATOR = " ";

    /**
     * Test whether commands are validated correctly.<br/>
     *
     * @throws BadCommandException If unrecognised command is given.
     */
    @Test
    void execution_invalidCommand_expectException() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setCommandType(INVALID_COMMAND);
        assertThrows(BadCommandException.class,
                reflectionManager::executeCommands);
    }

    /**
     * Test whether empty string input exception is properly thrown and caught.
     */
    @Test
    void setCommandType_emptyString_expectException() {
        ReflectionManager reflectionManager = new ReflectionManager();
        String[] input = EMPTY_STRING.split(SEPARATOR);
        System.out.println(input.length);
        assertThrows(BadCommandException.class, (
        ) -> reflectionManager.setCommandType(EMPTY_STRING));
        assertThrows(BadCommandException.class, (
        ) -> reflectionManager.setArgumentPayload(EMPTY_STRING));
    }

    /**
     * Test whether command argument_payload pair is properly generated.<br/>
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void setArgumentPayload_singleCommand_expectEmptyPayload() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> argumentPayload = reflectionManager.getArgumentPayload();
        String value = argumentPayload.get(GET_COMMAND);
        assertEquals(EMPTY_STRING, value);
    }
}

