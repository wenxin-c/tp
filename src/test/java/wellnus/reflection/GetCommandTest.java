package wellnus.reflection;

import org.junit.jupiter.api.Test;
import wellnus.exception.BadCommandException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class to test different tests for GetCommand Class utilising JUnit tests
 * Test cases will involve expected outputs and correct exception handling
 */
class GetCommandTest {
    private static final int EXPECTED_ARRAY_LENGTH = 5;
    private static final int EXPECTED_ARGUMENT_PAYLOAD_SIZE = 1;
    private static final String GET_COMMAND = "get";
    private static final String EMPTY_PAYLOAD = "";
    private static final String GET_COMMAND_WRONG_FORMAT = "get reflect";

    // Test whether the get command is properly generated
    @Test
    void createGetObject_checkArgumentPayload_success() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> getCmdArgumentPayload = reflectManager.getArgumentPayload();
        GetCommand get = new GetCommand(getCmdArgumentPayload);
        HashMap<String, String> argumentPayload = get.getArgumentPayload();
        assertEquals(EXPECTED_ARGUMENT_PAYLOAD_SIZE, argumentPayload.size());
        assertTrue(argumentPayload.containsKey(GET_COMMAND));
        assertEquals(EMPTY_PAYLOAD, argumentPayload.get(GET_COMMAND));
    }

    // Test the number of questions being generated
    @Test
    void getRandomQuestions_checkLength_expectFive() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> getCmdArgumentPayload = reflectManager.getArgumentPayload();
        GetCommand get = new GetCommand(getCmdArgumentPayload);
        ArrayList<ReflectionQuestion> selectedQuestions = get.getRandomQuestions();
        assertEquals(EXPECTED_ARRAY_LENGTH, selectedQuestions.size());
    }

    // Test whether command is validated properly.
    @Test
    void validateCommand_getCommand_expectException() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND_WRONG_FORMAT);
        HashMap<String, String> getCmdArgumentPayload = reflectManager.getArgumentPayload();
        GetCommand get = new GetCommand(getCmdArgumentPayload);
        assertThrows(BadCommandException.class,
                () -> get.validateCommand(getCmdArgumentPayload));
    }
}

