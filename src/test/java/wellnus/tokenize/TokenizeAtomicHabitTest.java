package wellnus.tokenize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.exception.TokenizerException;
import wellnus.storage.AtomicHabitTokenizer;
public class TokenizeAtomicHabitTest {
    private static final String DESCRIPTION_TEST_ONE = "test1";
    private static final String DESCRIPTION_TEST_TWO = "test2";
    private static final int COUNT_TEST_ONE = 1;
    private static final int COUNT_TEST_TWO = 2;
    private static final String TOKENIZED_STRING_TEST_1 = "--description test1 --count 1";
    private static final String TOKENIZED_STRING_TEST_2 = "--description test2 --count 2";
    @Test
    void execute_checkDetokenize_success() throws TokenizerException {
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();

        ArrayList<String> tokenizedHabitString = new ArrayList<>();
        tokenizedHabitString.add(TOKENIZED_STRING_TEST_1);
        tokenizedHabitString.add(TOKENIZED_STRING_TEST_2);
        ArrayList<AtomicHabit> habitList = habitTokenizer.detokenize(tokenizedHabitString);
        assertEquals(habitList.get(0).getDescription(), DESCRIPTION_TEST_ONE);
        assertEquals(habitList.get(1).getDescription(), DESCRIPTION_TEST_TWO);
        assertEquals(habitList.get(0).getCount(), COUNT_TEST_ONE);
        assertEquals(habitList.get(1).getCount(), COUNT_TEST_TWO);
    }
}
