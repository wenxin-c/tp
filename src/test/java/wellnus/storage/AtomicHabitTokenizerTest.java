package wellnus.storage;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.exception.TokenizerException;

/**
 * This class provides tests for the AtomicHabitTokenizer class. It tests the functionality of the methods of the
 * AtomicHabitTokenizer class using various inputs.
 */
public class AtomicHabitTokenizerTest {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final String EXPECTED_TOKENIZED_HABIT_ONE = "--description foo --count 1";
    private static final String EXPECTED_TOKENIZED_HABIT_TWO = "--description bar --count 2";
    private static final String ATOMIC_HABIT_TEST_ONE_DESCRIPTION = "foo";
    private static final String ATOMIC_HABIT_TEST_TWO_DESCRIPTION = "bar";
    private static final int ATOMIC_HABIT_TEST_ONE_COUNT = 1;
    private static final int ATOMIC_HABIT_TEST_TWO_COUNT = 2;

    private static final String TOKENIZED_HABIT_TEST_ONE = "--description foo --count 1";
    private static final String TOKENIZED_HABIT_TEST_TWO = "--description bar baz --count 1000";
    private static final String EXPECTED_DESCRIPTION_TEST_ONE = "foo";
    private static final String EXPECTED_DESCRIPTION_TEST_TWO = "bar baz";
    private static final int EXPECTED_COUNT_TEST_ONE = 1;
    private static final int EXPECTED_COUNT_TEST_TWO = 1000;
    private static final String INVALID_STRING_ONE = "foo";
    private static final String INVALID_STRING_TWO = "--description";
    private static final String INVALID_STRING_THREE = "description count";
    private static final String INVALID_STRING_FOUR = "--description --count";
    private static final String INVALID_STRING_FIVE = "--description foo --count bar";
    private static final String INVALID_STRING_SIX = "--description foo --count 1 --baz baz";

    private ArrayList<String> getInvalidTokenizedArrayList(String invalidString) {
        ArrayList<String> invalidTokenizedArrayList = new ArrayList<>();
        invalidTokenizedArrayList.add(invalidString);
        return invalidTokenizedArrayList;
    }

    /**
     * Tests the {@link AtomicHabitTokenizer#tokenize(ArrayList)} method to ensure that it correctly
     * tokenizes a list of {@link AtomicHabit} objects.
     */
    @Test
    void tokenizeHabit_checkOutput_success() {
        ArrayList<AtomicHabit> habitsToTokenize = new ArrayList<>();
        AtomicHabit atomicHabitTestOne = new AtomicHabit(ATOMIC_HABIT_TEST_ONE_DESCRIPTION,
                ATOMIC_HABIT_TEST_ONE_COUNT);
        AtomicHabit atomicHabitTestTwo = new AtomicHabit(ATOMIC_HABIT_TEST_TWO_DESCRIPTION,
                ATOMIC_HABIT_TEST_TWO_COUNT);
        habitsToTokenize.add(atomicHabitTestOne);
        habitsToTokenize.add(atomicHabitTestTwo);
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        ArrayList<String> actualTokenizedAtomicHabits = habitTokenizer.tokenize(habitsToTokenize);
        Assertions.assertEquals(EXPECTED_TOKENIZED_HABIT_ONE, actualTokenizedAtomicHabits.get(INDEX_ZERO));
        Assertions.assertEquals(EXPECTED_TOKENIZED_HABIT_TWO, actualTokenizedAtomicHabits.get(INDEX_ONE));
    }

    /**
     * Tests the {@link AtomicHabitTokenizer#detokenize(ArrayList)} method to ensure that it correctly
     * detokenizes a list of tokenized {@link AtomicHabit} objects.
     *
     * @throws TokenizerException if an error occurs during tokenization.
     */
    @Test
    void detokenizeHabit_checkOutput_success() throws TokenizerException {
        ArrayList<String> tokenizedHabits = new ArrayList<>();
        tokenizedHabits.add(TOKENIZED_HABIT_TEST_ONE);
        tokenizedHabits.add(TOKENIZED_HABIT_TEST_TWO);
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        ArrayList<AtomicHabit> actualDetokenizedAtomicHabits = habitTokenizer.detokenize(tokenizedHabits);
        assertEquals(EXPECTED_DESCRIPTION_TEST_ONE, actualDetokenizedAtomicHabits.get(INDEX_ZERO).getDescription());
        assertEquals(EXPECTED_COUNT_TEST_ONE, actualDetokenizedAtomicHabits.get(INDEX_ZERO).getCount());
        assertEquals(EXPECTED_DESCRIPTION_TEST_TWO, actualDetokenizedAtomicHabits.get(INDEX_ONE).getDescription());
        assertEquals(EXPECTED_COUNT_TEST_TWO, actualDetokenizedAtomicHabits.get(INDEX_ONE).getCount());
    }

    /**
     * Tests the {@link AtomicHabitTokenizer#detokenize(ArrayList)} method to ensure that it correctly
     * detokenizes an empty list of tokenized {@link AtomicHabit} objects.
     *
     * @throws TokenizerException if an error occurs during tokenization.
     */
    @Test
    void detokenizeHabit_checkOutputEmptyString_success() throws TokenizerException {
        ArrayList<AtomicHabit> expectedDetokenizedAtomicHabit = new ArrayList<>();
        ArrayList<String> tokenizedHabits = new ArrayList<>();
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        ArrayList<AtomicHabit> actualDetokenizedAtomicHabits = habitTokenizer.detokenize(tokenizedHabits);
        assertEquals(expectedDetokenizedAtomicHabit, actualDetokenizedAtomicHabits);
    }

    /**
     * Tests the {@link AtomicHabitTokenizer#detokenize(ArrayList)} method to ensure that it throws a
     * {@link TokenizerException} when attempting to detokenize an invalid tokenized {@link AtomicHabit}
     * string.
     */
    @Test
    void detokenizeHabit_invalidTokenizedAtomicHabitString_tokenizerExceptionThrown() {
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(INVALID_STRING_ONE));
        });
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(INVALID_STRING_TWO));
        });

        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(INVALID_STRING_THREE));
        });

        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(INVALID_STRING_FOUR));
        });

        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(INVALID_STRING_FIVE));
        });

        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(INVALID_STRING_SIX));
        });
    }
}
