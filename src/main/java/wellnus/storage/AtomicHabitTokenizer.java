package wellnus.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import wellnus.atomichabit.feature.AtomicHabit;
import wellnus.atomichabit.feature.AtomicHabitManager;
import wellnus.exception.TokenizerException;
import wellnus.manager.Manager;

/**
 * Class to tokenize and detokenize the AtomicHabit list inside AtomicHabitManager <br>
 */
public class AtomicHabitTokenizer implements Tokenizer<AtomicHabit> {
    private static final String DESCRIPTION_KEY = "description";
    private static final String COUNT_KEY = "count";
    private static final String PARAMETER_DELIMITER = "--";
    private static final String DETOKENIZE_ERROR_MESSAGE = "Detokenization failed!"
            + "The file might be corrupted";
    private static int INDEX_ZERO = 0;
    private static int INDEX_FIRST = 1;
    private String[] splitIntoParameter(String fullString) {
        fullString = fullString.strip();
        int noLimit = -1;
        String[] rawCommands = fullString.split(PARAMETER_DELIMITER, noLimit);
        rawCommands = Arrays.copyOfRange(rawCommands, INDEX_FIRST, rawCommands.length);
        String[] cleanCommands = new String[rawCommands.length];
        for (int i = 0; i < rawCommands.length; ++i) {
            String currentCommand = rawCommands[i];
            currentCommand = currentCommand.strip();
            cleanCommands[i] = currentCommand;
        }
        return cleanCommands;
    }

    private AtomicHabit parseTokenizedHabit(String tokenizedString) throws TokenizerException {
        HashMap<String, String> parameterHashMap = new HashMap<>();
        String[] parameterList = splitIntoParameter(tokenizedString);
        for (String parameterString: parameterList) {
            int i = parameterString.indexOf(' ');
            String parameterKey = parameterString.substring(INDEX_ZERO, i);
            String parameterValue = parameterString.substring(i).trim();
            parameterHashMap.put(parameterKey, parameterValue);
        }
        if (!parameterHashMap.containsKey(DESCRIPTION_KEY) || !parameterHashMap.containsKey(COUNT_KEY)) {
            throw new TokenizerException(DETOKENIZE_ERROR_MESSAGE);
        }
        String description = parameterHashMap.get(DESCRIPTION_KEY);
        String countString = parameterHashMap.get(COUNT_KEY);
        try {
            int count = Integer.parseInt(countString);
            AtomicHabit parsedHabit = new AtomicHabit(description, count);
            return parsedHabit;
        } catch (NumberFormatException numberFormatException) {
            throw new TokenizerException(DETOKENIZE_ERROR_MESSAGE);
        }
    }
    /**
     * Tokenize List of Atomic Habits in the manager to be saved as ArrayList of Strings. <br>
     * Each habit will be tokenized with the following format:
     * --description [description of habit] --count [count of habit]. <br>
     */
    public ArrayList<String> tokenize(Manager managerToTokenize) throws TokenizerException {
        AtomicHabitManager atomicHabitManager = (AtomicHabitManager) managerToTokenize;
        ArrayList<AtomicHabit> habitList = atomicHabitManager.getHabitList().getAllHabits();
        ArrayList<String> tokenizedHabitList = new ArrayList<>();
        for (AtomicHabit habit: habitList) {
            String tokenizedHabit = PARAMETER_DELIMITER + DESCRIPTION_KEY
                    + " " + habit.getDescription()
                    + " " + PARAMETER_DELIMITER + COUNT_KEY
                    + " " + habit.getCount();
            tokenizedHabitList.add(tokenizedHabit);
        }
        return tokenizedHabitList;
    }
    /**
     * Convert string of tokenized AtomicHabitManager into ArrayList of AtomicHabit.
     */
    public ArrayList<AtomicHabit> detokenize(ArrayList<String> tokenizedAtomicHabitManager) throws TokenizerException {
        ArrayList<AtomicHabit> detokenizedAtomicHabit = new ArrayList<>();
        for (String tokenizedString : tokenizedAtomicHabitManager) {
            AtomicHabit parsedHabit = parseTokenizedHabit(tokenizedString);
            detokenizedAtomicHabit.add(parsedHabit);
        }
        return detokenizedAtomicHabit;
    }
}

