package wellnus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import wellnus.common.WellNusLogger;
import wellnus.exception.StorageException;

//@@author nichyjt

/**
 * Storage is the common interface for all Features to save and load data from. <br>
 * <p>
 * To save data, the manager should call <code>saveData()</code> and input a list of Strings.
 * Each string represents one instance of the tokenized form of the data structure the manager is handling,
 * such as ReflectionQuestion or AtomicHabit. <br>
 * <p>
 * To load data, the manager should call <code>loadData()</code> and input the correct filename of
 * the data to be loaded. The filename should be obtained from the public constant Storage.FILE_[name].
 * <p>
 */
public class Storage {
    // These constant strings are intentionally made public
    // to allow any FeatureManager to call the (de)tokenize functions with the correct filename
    public static final String FILE_HABIT = "habit";
    public static final String FILE_REFLECT = "reflect";
    public static final String FILE_GAMIFICATION = "gamif";
    protected static final String FILE_DEBUG = "debug";

    // Delimiter constants
    protected static final String DELIMITER = " --" + System.lineSeparator();
    protected static final String NEWLINE = System.lineSeparator();
    private static final String FILE_EXTENTION = ".txt";
    private static final String WORKING_DIRECTORY = ".";
    private static final String DATA_DIRECTORY_NAME = "data";

    // Message constants
    private static final String ERROR_GENERAL = "WellNUS++ faced an internal error in storage!";
    private static final String ERROR_CANNOT_MAKE_FILE = "WellNUS++ couldn't make the data file!";
    private static final String ERROR_CANNOT_MAKE_DIR = "WellNUS++ couldn't make the data directory!";
    private static final String ERROR_CANNOT_DELETE_FILE = "WellNUS++ couldn't delete the data file!";
    private static final String ERROR_CANNOT_RESOLVE_PATH = "WellNUS++ couldn't resolve a path internally!";
    private static final String ERROR_CANNOT_WRITE_FILE = "WellNUS++ couldn't write to a file!";
    private static final String ERROR_CANNOT_LOAD_FILE = "WellNUS++ couldn't load a file!";
    private static final String ERROR_INVALID_FILENAME = "WellNUS++ cannot create a file that is not registered!";
    private static final String ASSERT_FILENAME_NOT_NULL = "fileName should not be null!";
    private static final String ASSERT_FILENAME_NOT_EMPTY = "fileName should have a length > 0!";
    private static final String ASSERT_PATH_NOT_NULL = "path should not be null!";
    private static final String ASSERT_LIST_NOT_NULL = "list input should not be null!";
    private static final String ASSERT_STRING_NOT_NULL = "string input should not be null!";
    private static final String ASSERT_FILE_NOT_NULL = "file input should not be null!";
    private static final Logger LOGGER = WellNusLogger.getLogger("StorageLogger");
    private static final String LOG_ACCESS_ERROR = "WellNUS++ has encountered a severe input/output error! \n"
            + "Check if file permissions and data directory are properly instantiated?";
    private static final String LOG_MISSING_FILE = "WellNUS++ could not find a file.\n"
            + "Check if this method was called before any data file instantiation?";
    private static final String LOG_INVALID_FILENAME = "WellNUS++ cannot create the file as its name is invalid.\n"
            + "Check if its filename is registered in the Storage class.";
    private static final int FILENAME_EMPTY = 0;
    private Path wellNusDataDirectory;

    /**
     * Construct an instance of Storage to call saveData and loadData from.
     *
     * @throws StorageException when creating the data directory fails
     */
    //@@author nichyjt
    public Storage() throws StorageException {
        wellNusDataDirectory = Paths.get(WORKING_DIRECTORY, DATA_DIRECTORY_NAME);
        // For safety, check that the data folder actually exists
        // If it doesn't, create it.
        verifyDataDirectory();
    }

    /**
     * Method to check if the specific file exists.
     * Used to check if the file exists before attempting to load it for atomic habit manager.
     *
     * @param fileName name of the file storing the feature data
     * @return boolean representing if the file exists
     * @throws StorageException When querying the fileName fails
     */
    public boolean checkFileExists(String fileName) throws StorageException {
        Path pathToFile;
        File dataFile;
        try {
            pathToFile = wellNusDataDirectory.resolve(fileName + FILE_EXTENTION);
            dataFile = pathToFile.toFile();
        } catch (InvalidPathException exception) {
            String errorMessage = ERROR_CANNOT_RESOLVE_PATH;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        } catch (UnsupportedOperationException exception) {
            String errorMessage = ERROR_GENERAL;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
        return dataFile.exists();
    }

    /**
     * Check if the supplied fileName is a valid WellNUS++ file.
     *
     * @param fileName name of file to be used in WellNUS++
     * @return boolean representing if the fileName exists
     */
    //@@author nichyjt
    private boolean isValidFileName(String fileName) {
        assert fileName != null : ASSERT_STRING_NOT_NULL;
        switch (fileName) {
        case FILE_GAMIFICATION:
        case FILE_HABIT:
        case FILE_REFLECT:
        case FILE_DEBUG:
            // fallthrough
            return true;
        default:
            return false;
        }
    }

    /**
     * Check that the data folder exists. If it does not, try creating it.
     *
     * @throws StorageException if directory cannot be made
     */
    //@@author nichyjt
    private void verifyDataDirectory() throws StorageException {
        assert wellNusDataDirectory != null : "wellNusDataDirectory path should be set up!";
        boolean directoryExists = Files.exists(wellNusDataDirectory);
        if (directoryExists) {
            return;
        }
        createDataFolder(wellNusDataDirectory);
    }

    /**
     * Creates a <code>File</code> relative to the data folder.
     *
     * @param fileName data file to retrieve
     */
    protected File getFile(String fileName) throws StorageException {
        assert fileName != null : ASSERT_FILENAME_NOT_NULL;
        assert fileName.length() > FILENAME_EMPTY : ASSERT_FILENAME_NOT_EMPTY;
        if (!isValidFileName(fileName)) {
            LOGGER.log(Level.WARNING, LOG_INVALID_FILENAME);
            throw new StorageException(ERROR_INVALID_FILENAME);
        }
        verifyDataDirectory();
        Path pathToFile;
        File dataFile;
        try {
            pathToFile = wellNusDataDirectory.resolve(fileName + FILE_EXTENTION);
            dataFile = pathToFile.toFile();
        } catch (InvalidPathException exception) {
            String errorMessage = ERROR_CANNOT_RESOLVE_PATH;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        } catch (UnsupportedOperationException exception) {
            String errorMessage = ERROR_GENERAL;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
        boolean fileExists = dataFile.exists();
        if (!fileExists) {
            createFile(dataFile);
        }
        return dataFile;
    }

    /**
     * Create the data folder for WellNUS++.
     *
     * @param directoryPath path of the directory
     * @throws StorageException if directory cannot be made
     */
    //@@author nichyjt
    private void createDataFolder(Path directoryPath) throws StorageException {
        assert directoryPath != null : ASSERT_PATH_NOT_NULL;
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, LOG_ACCESS_ERROR);
            String errorMessage = ERROR_CANNOT_MAKE_DIR + System.lineSeparator();
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
    }

    /**
     * Create a file in the path specified by its URI.
     *
     * @param file to be created
     * @throws StorageException if the file cannot be made
     */
    //@@author nichyjt
    private void createFile(File file) throws StorageException {
        assert file != null : ASSERT_FILE_NOT_NULL;
        try {
            file.createNewFile();
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, LOG_ACCESS_ERROR);
            String errorMessage = ERROR_CANNOT_MAKE_FILE;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
    }

    /**
     * Tokenize every String entry with the delimiter suffix and append them together.
     *
     * @param tokenizedStrings strings to be tokenized
     * @return String of all tokenized string entries
     */
    //@@author nichyjt
    protected String tokenizeStringList(ArrayList<String> tokenizedStrings) {
        assert tokenizedStrings != null : ASSERT_LIST_NOT_NULL;
        StringBuilder stringBuilder = new StringBuilder();
        for (String entry : tokenizedStrings) {
            String entryDelimited = entry + DELIMITER;
            stringBuilder.append(entryDelimited);
        }
        return stringBuilder.toString();
    }

    /**
     * Splits a dataString by the " --\n" delimiter.
     *
     * @param dataString string to be split
     * @return String[] of words belonging to the dataString
     */
    //@@author nichyjt
    private String[] splitIntoEntries(String dataString) {
        assert dataString != null : ASSERT_STRING_NOT_NULL;
        return dataString.split(DELIMITER);
    }

    /**
     * Detokenizing raw dataString into ArrayList of strings, where each string
     * is an entry in the associated Manager's data structure.
     *
     * @param dataString raw string loaded from the text file
     * @return ArrayList of strings to be parsed by tokenizer
     */
    //@@author nichyjt
    protected ArrayList<String> detokenizeDataString(String dataString) {
        assert dataString != null : ASSERT_STRING_NOT_NULL;
        String[] entries = splitIntoEntries(dataString);
        return new ArrayList<>(Arrays.asList(entries));
    }

    private void writeDataToDisk(String data, File file) throws StorageException {
        assert data != null : ASSERT_STRING_NOT_NULL;
        assert file != null : ASSERT_FILE_NOT_NULL;
        // assume file exists
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            writer.write(data);
            writer.close();
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, LOG_MISSING_FILE);
            String errorMessage = ERROR_CANNOT_WRITE_FILE;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
    }

    private String loadDataFromDisk(File file) throws StorageException {
        assert file != null : ASSERT_FILE_NOT_NULL;
        // assume file exists
        StringBuilder data = new StringBuilder();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                data.append(reader.nextLine()).append(NEWLINE);
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            LOGGER.log(Level.SEVERE, LOG_ACCESS_ERROR);
            String errorMessage = ERROR_CANNOT_LOAD_FILE;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        } catch (IllegalStateException exception) {
            LOGGER.log(Level.SEVERE, LOG_ACCESS_ERROR);
            String errorMessage = ERROR_GENERAL;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
        return data.toString();
    }

    /**
     * Save the pre-tokenized data onto Disk. <br>
     * <p>
     * The data will be saved into the /data folder. <br>
     * Each entry in the ArrayList should be an instance of the underlying data structure being `Managed`,
     * with each instance being tokenized into a String beforehand. <br>
     * The fileName should be accessed via the public constant Storage.FILE_[feature].
     *
     * @param tokenizedManager ArrayList of tokenized Manager data string
     * @param fileName         name of the file to be saved
     * @throws StorageException when there are unexpected IO errors
     */
    //@@author nichyjt
    public void saveData(ArrayList<String> tokenizedManager, String fileName) throws StorageException {
        assert fileName != null : ASSERT_FILENAME_NOT_NULL;
        assert fileName.length() > FILENAME_EMPTY : ASSERT_FILENAME_NOT_EMPTY;
        assert tokenizedManager != null : ASSERT_LIST_NOT_NULL;
        if (!isValidFileName(fileName)) {
            LOGGER.log(Level.WARNING, LOG_INVALID_FILENAME);
            throw new StorageException(ERROR_INVALID_FILENAME);
        }
        File file = getFile(fileName);
        String tokenizedString = tokenizeStringList(tokenizedManager);
        writeDataToDisk(tokenizedString, file);
    }

    /**
     * Load a feature's data from the Disk. <br>
     * <p>
     * The data will be laoded from the /data folder. <br>
     * Each entry in the ArrayList will be an instance of the underlying data structure being `Managed`,
     * with each instance being tokenized into a String beforehand <br>
     * The fileName should be accessed via the public constant Storage.FILE_[feature].
     *
     * @param fileName name of the file to be loaded
     * @return ArrayList of tokenized Manager data string
     * @throws StorageException when there are unexpected IO errors
     */
    //@@author nichyjt
    public ArrayList<String> loadData(String fileName) throws StorageException {
        assert fileName != null : ASSERT_FILENAME_NOT_NULL;
        assert fileName.length() > FILENAME_EMPTY : ASSERT_FILENAME_NOT_EMPTY;
        if (!isValidFileName(fileName)) {
            LOGGER.log(Level.WARNING, LOG_INVALID_FILENAME);
            throw new StorageException(ERROR_INVALID_FILENAME);
        }
        File file = getFile(fileName);
        String data = loadDataFromDisk(file);
        return detokenizeDataString(data);
    }

    /**
     * Deletes the file from the /data directory.
     *
     * @param fileName name of the file to be deleted
     * @throws StorageException when there are unexpected IO errors
     */
    //@@author nichyjt
    protected void deleteFile(String fileName) throws StorageException {
        assert fileName != null : ASSERT_FILENAME_NOT_NULL;
        assert fileName.length() > FILENAME_EMPTY : ASSERT_FILENAME_NOT_EMPTY;
        if (!isValidFileName(fileName)) {
            LOGGER.log(Level.WARNING, LOG_INVALID_FILENAME);
            throw new StorageException(ERROR_INVALID_FILENAME);
        }
        File file = getFile(fileName);
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            throw new StorageException(ERROR_CANNOT_DELETE_FILE);
        }
    }

}
