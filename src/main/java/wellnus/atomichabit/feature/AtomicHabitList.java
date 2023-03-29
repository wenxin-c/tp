package wellnus.atomichabit.feature;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.exception.StorageException;
import wellnus.exception.TokenizerException;
import wellnus.storage.AtomicHabitTokenizer;
import wellnus.storage.Storage;
import wellnus.ui.TextUi;

/**
 * Class to represent a container that will contain all unique AtomicHabit objects in an arraylist
 */
public class AtomicHabitList {

    private static final String FILE_NAME = "habit";
    private static final String STORAGE_ERROR = "The file data cannot be stored properly!!";
    private static final String TOKENIZER_ERROR = "The data cannot be tokenized for storage properly!!";
    private static final Logger LOGGER = Logger.getLogger("AtomicHabitListLogger");
    private static final AtomicHabitTokenizer atomicHabitTokenizer = new AtomicHabitTokenizer();
    //Changed to non-final to assign from load data, can change accordingly
    private ArrayList<AtomicHabit> allAtomicHabits;

    private Storage storage;
    private TextUi textUi;

    /**
     * Constructor for AtomicHabitList class, initializes the storage,textUi and allAtomicHabits objects.
     * Loads the data from the data file into the arraylist of atomic habits.
     */
    public AtomicHabitList() {
        try {
            storage = new Storage();
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
            textUi.printErrorFor(storageException, STORAGE_ERROR);
        }
        textUi = new TextUi();
        allAtomicHabits = new ArrayList<>();
        try {
            this.loadHabitData();
        } catch (StorageException storageException) {
            LOGGER.log(Level.WARNING, TOKENIZER_ERROR);
            textUi.printErrorFor(storageException, TOKENIZER_ERROR);
        } catch (TokenizerException tokenizerException) {
            LOGGER.log(Level.WARNING, STORAGE_ERROR);
            textUi.printErrorFor(tokenizerException, STORAGE_ERROR);
        }
    }

    /**
     * Method to add atomicHabit to list containing all habits
     *
     * @param atomicHabit New atomic habit to add into the list that this class manages
     */

    public void addAtomicHabit(AtomicHabit atomicHabit) {
        allAtomicHabits.add(atomicHabit);
    }

    /**
     * Tokenize the atomic habits and store them in a data file.
     *
     * @throws TokenizerException If there is error during tokenization
     * @throws StorageException   If data cannot be stored properly
     */
    public void storeHabitData() throws StorageException {
        ArrayList<String> tokenizedHabitList = atomicHabitTokenizer.tokenize(allAtomicHabits);
        storage.saveData(tokenizedHabitList, FILE_NAME);
    }

    /**
     * Load a list of strings from data file and detokenize it into the values of atomic habits.
     *
     * @throws StorageException   If there is error during tokenization
     * @throws TokenizerException If there is error during detokenization
     */
    public void loadHabitData() throws StorageException, TokenizerException {
        ArrayList<String> loadedHabitList = storage.loadData(FILE_NAME);
        ArrayList<AtomicHabit> detokenizedHabitList = atomicHabitTokenizer.detokenize(loadedHabitList);
        allAtomicHabits = detokenizedHabitList;
    }

    /**
     * Method to get list containing all habits
     *
     * @return allAtomicHabits which is an arraylist containing all habits
     */
    public ArrayList<AtomicHabit> getAllHabits() {
        return allAtomicHabits;
    }

    public AtomicHabit getHabitByIndex(int index) {
        return allAtomicHabits.get(index);
    }
}

