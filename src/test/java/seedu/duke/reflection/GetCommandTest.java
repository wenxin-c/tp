package seedu.duke.reflection;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.BadCommandException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetCommandTest {
    private static final int EXPECTED_ARRAY_LENGTH = 5;
    private static final String GET_COMMAND = "get";
    private static final String GET_COMMAND_WRONG_FORMAT = "get reflect";

    // Test the number of questions being generated
    @Test
    void getRandomQuestions_checkLength_expectFive() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND);
        HashMap<String,String> getCmdArgumentPayload = reflectManager.getArgumentPayload();
        GetCommand get = new GetCommand(getCmdArgumentPayload);
        SelfReflection selfReflection = new SelfReflection();
        ArrayList<ReflectionQuestion> selectedQuestions = get.getRandomQuestions();
        assertEquals(EXPECTED_ARRAY_LENGTH, selectedQuestions.size());
        SelfReflection.clearQuestions();
    }

    // Test whether command is validated properly.
    @Test
    void validateCommand_getCommand_expectException() throws BadCommandException {
        ReflectionManager reflectManager = new ReflectionManager();
        reflectManager.setArgumentPayload(GET_COMMAND_WRONG_FORMAT);
        HashMap<String,String> getCmdArgumentPayload = reflectManager.getArgumentPayload();
        GetCommand get = new GetCommand(getCmdArgumentPayload);
        assertThrows(BadCommandException.class,
                () -> get.validateCommand(getCmdArgumentPayload));
    }
}
