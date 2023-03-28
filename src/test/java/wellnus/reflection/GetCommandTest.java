package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.reflection.command.GetCommand;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectionManager;
import wellnus.reflection.feature.ReflectionQuestion;

/**
 * Class to test different tests for GetCommand Class utilising JUnit tests
 * Test cases will involve expected outputs and correct exception handling
 *
 * @@author wenxin-c
 */
class GetCommandTest {
    private static final int EXPECTED_ARRAY_LENGTH = 5;
    private static final int EXPECTED_ARGUMENT_PAYLOAD_SIZE = 1;
    private static final String GET_COMMAND = "get";
    private static final String EMPTY_PAYLOAD = "";
    private static final String GET_COMMAND_WRONG_FORMAT = "get reflect";
    private static final QuestionList questionList = new QuestionList();

    // Test whether the get command is properly generated
    @Test
    void createGetObject_checkArgumentPayload_success() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> argumentPayload = reflectManager.getArgumentPayload();
        assertEquals(EXPECTED_ARGUMENT_PAYLOAD_SIZE, argumentPayload.size());
        assertTrue(argumentPayload.containsKey(GET_COMMAND));
        assertEquals(EMPTY_PAYLOAD, argumentPayload.get(GET_COMMAND));
    }

    // Test the number of questions being generated
    @Test
    void getRandomQuestions_checkLength_expectFive() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> getCmdArgumentPayload = commandParser.parseUserInput(GET_COMMAND);
        GetCommand get = new GetCommand(getCmdArgumentPayload, questionList);
        ArrayList<ReflectionQuestion> selectedQuestions = get.getRandomQuestions();
        assertEquals(EXPECTED_ARRAY_LENGTH, selectedQuestions.size());
    }

    // Test whether command is validated properly.
    @Test
    void validateCommand_getCommand_expectException() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> getCmdArgumentPayload = commandParser.parseUserInput(GET_COMMAND_WRONG_FORMAT);
        GetCommand get = new GetCommand(getCmdArgumentPayload, questionList);
        assertThrows(BadCommandException.class, () -> get.validateCommand(getCmdArgumentPayload));
    }
}

