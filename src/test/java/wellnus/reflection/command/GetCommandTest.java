package wellnus.reflection.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.exception.StorageException;
import wellnus.reflection.feature.QuestionList;
import wellnus.reflection.feature.ReflectionManager;
import wellnus.reflection.feature.ReflectionQuestion;

//@@author wenxin-c
/**
 * Class to test different tests for `GetCommand` Class utilising JUnit tests.
 * Test cases will involve expected outputs and correct exception handling.
 */
class GetCommandTest {
    private static final int EXPECTED_ARRAY_LENGTH = 5;
    private static final int EXPECTED_ARGUMENT_PAYLOAD_SIZE = 1;
    private static final String GET_COMMAND = "get";
    private static final String EMPTY_PAYLOAD = "";
    private static final String GET_COMMAND_WRONG_PAYLOAD = "get test";
    private static final String GET_COMMAND_WRONG_ARGUMENT = "get --test";
    private static final QuestionList QUESTION_LIST = new QuestionList();

    /**
     * Test whether the ``get` command is properly parsed and generated.<br/>
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void createGetObject_checkArgumentPayload_success() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND);
        HashMap<String, String> argumentPayload = reflectManager.getArgumentPayload();
        assertEquals(EXPECTED_ARGUMENT_PAYLOAD_SIZE, argumentPayload.size());
        assertTrue(argumentPayload.containsKey(GET_COMMAND));
        assertEquals(EMPTY_PAYLOAD, argumentPayload.get(GET_COMMAND));
    }

    /**
     * Test the number of questions being generated.<br/>
     *
     * @throws BadCommandException If an invalid command is given.
     * @throws StorageException If errors happen at storage.
     */
    @Test
    void getRandomQuestions_checkLength_expectFive() throws BadCommandException, StorageException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> getCmdArgumentPayload = commandParser.parseUserInput(GET_COMMAND);
        GetCommand get = new GetCommand(getCmdArgumentPayload, QUESTION_LIST);
        ArrayList<ReflectionQuestion> selectedQuestions = get.getRandomQuestions();
        assertEquals(EXPECTED_ARRAY_LENGTH, selectedQuestions.size());
    }

    /**
     * Test whether `get` command is validated properly.<br/>
     * 'get' without any payload and arguments, otherwise throw exception.
     *
     * @throws BadCommandException If an invalid command is given.
     */
    @Test
    void validateCommand_getCommand_expectException() throws BadCommandException {
        CommandParser commandParser = new CommandParser();
        HashMap<String, String> getCmdWrongPayload = commandParser.parseUserInput(GET_COMMAND_WRONG_PAYLOAD);
        GetCommand getWrongPayload = new GetCommand(getCmdWrongPayload, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> getWrongPayload.validateCommand(getCmdWrongPayload));
        HashMap<String, String> getCmdWrongArgument = commandParser.parseUserInput(GET_COMMAND_WRONG_ARGUMENT);
        GetCommand getWrongArgument = new GetCommand(getCmdWrongArgument, QUESTION_LIST);
        assertThrows(BadCommandException.class, () -> getWrongArgument.validateCommand(getCmdWrongArgument));
    }
}

