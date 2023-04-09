package wellnus.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.exception.TokenizerException;

/**
 * Class to tokenize and detokenize the AtomicHabit list. <br>
 */
public class AtomicHabitTokenizer implements Tokenizer<AtomicHabit> {
    private static final String DESCRIPTION_KEY = "description";
    private static final String COUNT_KEY = "count";
    private static final String PARAMETER_DELIMITER = "--";
    private static final String DETOKENIZE_ERROR_MESSAGE = "Invalid habit data '%s' found in storage!";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_FIRST = 1;
    private static final int NUM_ATOMIC_HABIT_PARAMETER = 2;
    private static final String REGEX_NUMBER_AND_SYMBOL_ONLY_PATTERN = "^[\\d\\p{Punct}\\p{S}]*$";

    private String[] splitTokenizedHabitIntoParameter(String tokenizedHabit) {
        tokenizedHabit = tokenizedHabit.strip();
        int noLimit = -1;
        String[] rawStrings = tokenizedHabit.split(PARAMETER_DELIMITER, noLimit);
        rawStrings = Arrays.copyOfRange(rawStrings, INDEX_FIRST, rawStrings.length);
        String[] cleanString = new String[rawStrings.length];
        for (int i = 0; i < rawStrings.length; ++i) {
            String currentCommand = rawStrings[i];
            currentCommand = currentCommand.strip();
            cleanString[i] = currentCommand;
        }
        return cleanString;
    }

    private String convertToBase(String habitName) {
        return habitName.toLowerCase().replaceAll("\\s", "");
    }

    private ArrayList<AtomicHabit> removeDuplicatedHabits(ArrayList<AtomicHabit> uncheckedAtomicHabits) {
        HashMap<String, AtomicHabit> uniqueHabits = new LinkedHashMap<>();
        for (AtomicHabit habit : uncheckedAtomicHabits) {
            String description = convertToBase(habit.getDescription());
            if (!uniqueHabits.containsKey(description)) {
                uniqueHabits.put(description, habit);
            }
        }
        return new ArrayList<>(uniqueHabits.values());
    }

    private AtomicHabit parseTokenizedHabit(String tokenizedHabit) throws TokenizerException {
        HashMap<String, String> parameterHashMap = new HashMap<>();
        String[] parameterStrings = splitTokenizedHabitIntoParameter(tokenizedHabit);
        try {
            for (String parameterString : parameterStrings) {
                int i = parameterString.indexOf(' ');
                String parameterKey = parameterString.substring(INDEX_ZERO, i);
                String parameterValue = parameterString.substring(i).trim();
                parameterHashMap.put(parameterKey, parameterValue);
            }
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, tokenizedHabit));
        }
        if ((!parameterHashMap.containsKey(DESCRIPTION_KEY) || !parameterHashMap.containsKey(COUNT_KEY))
                && !parameterHashMap.isEmpty()) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, tokenizedHabit));
        }
        if (parameterHashMap.size() != NUM_ATOMIC_HABIT_PARAMETER) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, tokenizedHabit));
        }
        String description = parameterHashMap.get(DESCRIPTION_KEY);
        String countString = parameterHashMap.get(COUNT_KEY);
        if (description.matches(REGEX_NUMBER_AND_SYMBOL_ONLY_PATTERN)) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, tokenizedHabit));
        }
        try {
            int count = Integer.parseInt(countString);
            return new AtomicHabit(description, count);
        } catch (NumberFormatException numberFormatException) {
            throw new TokenizerException(String.format(DETOKENIZE_ERROR_MESSAGE, tokenizedHabit));
        }
    }

    /**
     * Tokenize List of Atomic Habits to be saved as ArrayList of Strings. <br>
     * Each habit will be tokenized with the following format:
     * --description [description of habit] --count [count of habit]. <br>
     *
     * @param habitsToTokenize List of atomic habits to be tokenized as ArrayList of strings.
     * @return ArrayList of Strings representing the tokenized habits that we can write to storage.
     */
    public ArrayList<String> tokenize(ArrayList<AtomicHabit> habitsToTokenize) {
        ArrayList<String> tokenizedHabits = new ArrayList<>();
        for (AtomicHabit habit : habitsToTokenize) {
            String tokenizedHabit = PARAMETER_DELIMITER + DESCRIPTION_KEY
                    + " " + habit.getDescription()
                    + " " + PARAMETER_DELIMITER + COUNT_KEY
                    + " " + habit.getCount();
            tokenizedHabits.add(tokenizedHabit);
        }
        return tokenizedHabits;
    }

    /**
     * Convert strings of tokenized AtomicHabit into ArrayList of AtomicHabit. <br>
     * This method can be called in the constructor of AtomicHabitManager to detokenize.
     * ArrayList of atomic habits from storage. <br>
     *
     * @param tokenizedAtomicHabits List of tokenized atomic habit strings from the storage.
     * @return ArrayList containing all the atomic habit saved in the storage.
     * @throws TokenizerException When the data can't be detokenized.
     */
    public ArrayList<AtomicHabit> detokenize(ArrayList<String> tokenizedAtomicHabits) throws TokenizerException {
        ArrayList<AtomicHabit> detokenizedAtomicHabits = new ArrayList<>();
        for (String tokenizedString : tokenizedAtomicHabits) {
            if (!tokenizedString.isBlank()) {
                AtomicHabit parsedHabit = parseTokenizedHabit(tokenizedString);
                detokenizedAtomicHabits.add(parsedHabit);
            }
        }
        detokenizedAtomicHabits = removeDuplicatedHabits(detokenizedAtomicHabits);
        return detokenizedAtomicHabits;
    }
}
