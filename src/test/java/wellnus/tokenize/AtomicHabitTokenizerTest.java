package wellnus.tokenize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.exception.TokenizerException;
import wellnus.storage.AtomicHabitTokenizer;



public class AtomicHabitTokenizerTest {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private ArrayList<String> getInvalidTokenizedArrayList(String invalidString) {
        ArrayList<String> invalidTokenizedArrayList = new ArrayList<>();
        invalidTokenizedArrayList.add(invalidString);
        return invalidTokenizedArrayList;
    }
    @Test
    void tokenizeHabit_checkOutput_success() throws TokenizerException {
        String expectedTokenizedHabitOne = "--description foo --count 1";
        String expectedTokenizedHabitTwo = "--description bar --count 2";
        ArrayList<AtomicHabit> habitsToTokenize = new ArrayList<>();
        AtomicHabit atomicHabitTestOne = new AtomicHabit("foo", 1);
        AtomicHabit atomicHabitTestTwo = new AtomicHabit("bar", 2);
        habitsToTokenize.add(atomicHabitTestOne);
        habitsToTokenize.add(atomicHabitTestTwo);
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        ArrayList<String> actualTokenizedAtomicHabits = habitTokenizer.tokenize(habitsToTokenize);
        Assertions.assertEquals(expectedTokenizedHabitOne, actualTokenizedAtomicHabits.get(INDEX_ZERO));
        Assertions.assertEquals(expectedTokenizedHabitTwo, actualTokenizedAtomicHabits.get(INDEX_ONE));
    }
    @Test
    void detokenizeHabit_checkOutput_success() throws TokenizerException {
        String tokenizedHabitTestOne = "--description foo --count 1";
        String tokenizedHabitTestTwo = "--description bar baz --count 1000";
        String expectedDescriptionTestOne = "foo";
        String expectedDescriptionTestTwo = "bar baz";
        int expectedCountTestOne = 1;
        int expectedCountTestTwo = 1000;
        ArrayList<String> tokenizedHabits = new ArrayList<>();
        tokenizedHabits.add(tokenizedHabitTestOne);
        tokenizedHabits.add(tokenizedHabitTestTwo);
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        ArrayList<AtomicHabit> actualDetokenizedAtomicHabits = habitTokenizer.detokenize(tokenizedHabits);
        assertEquals(expectedDescriptionTestOne, actualDetokenizedAtomicHabits.get(INDEX_ZERO).getDescription());
        assertEquals(expectedCountTestOne, actualDetokenizedAtomicHabits.get(INDEX_ZERO).getCount());
        assertEquals(expectedDescriptionTestTwo, actualDetokenizedAtomicHabits.get(INDEX_ONE).getDescription());
        assertEquals(expectedCountTestTwo, actualDetokenizedAtomicHabits.get(INDEX_ONE).getCount());
    }
    @Test
    void detokenizeHabit_invalidTokenizedAtomicHabitString_tokenizerExceptionThrown() throws TokenizerException {
        AtomicHabitTokenizer habitTokenizer = new AtomicHabitTokenizer();
        String invalidStringOne = "foo";
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(invalidStringOne));
        });
        String invalidStringTwo = "--description";
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(invalidStringTwo));
        });
        String invalidStringThree = "description count";
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(invalidStringThree));
        });
        String invalidStringFour = "--description --count";
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(invalidStringFour));
        });
        String invalidStringFive = "--description foo --count bar";
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(invalidStringFive));
        });
        String invalidStringSix = "--description foo --count 1 --baz baz";
        Assertions.assertThrows(TokenizerException.class, () -> {
            habitTokenizer.detokenize(getInvalidTokenizedArrayList(invalidStringSix));
        });
    }
}
