package wellnus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import wellnus.exception.StorageException;

// TODO add logger and more defensive checks

/**
 * Storage is the common interface for all Features to save and load data from. <br>
 * <p>
 * To save data, the manager should call <code>saveData()</code> and input a list of Strings.
 * Each string represents one instance of the tokenized form of the data structure the manager is handling,
 * such as ReflectionQuestion or AtomicHabit. <br>
 * <p>
 * To load data, the manager should call <code>loadData()</code> and input the correct filename of
 * the data to be loaded. The filename should be obtained from the public constant Storage.FILE_[name]
 *
 * @author nichyjt
 */
public class Storage {
    // These constant strings are intentionally made public
    // to allow any FeatureManager to call the (de)tokenize functions with the correct filename
    public static final String FILE_HABIT = "habit";
    public static final String FILE_REFLECT = "reflect";
    protected static final String FILE_DEBUG = "debug";
    protected static final String DIRECTORY_DEBUG = "debug";

    // Delimiter constants
    protected static final String DELIMITER = " --\n";
    protected static final String EMPTY_STRING = "";
    protected static final String WHITESPACE_PADDING = " ";
    protected static final String NEWLINE = System.lineSeparator();
    protected static final int ATTRIBUTE_INDEX = 0;
    protected static final int PAYLOAD_INDEX_START = 1;

    private static final String FILE_EXTENTION = ".txt";
    private static final String WORKING_DIRECTORY = ".";
    private static final String DATA_DIRECTORY_NAME = "data";

    // Message constants
    private static final String ERROR_CANNOT_MAKE_FILE = "WellNUS++ couldn't make the data file!";
    private static final String ERROR_CANNOT_MAKE_DIR = "WellNUS++ couldn't make the data directory!";
    private static final String ERROR_CANNOT_DELETE_FILE = "WellNUS++ couldn't delete the data file!";

    private Path wellNusDataDirectory;

    /**
     * Construct an instance of Storage to call saveData and loadData from.
     *
     * @throws wellnus.exception.StorageException when creating the data directory fails
     * @author nichyjt
     */
    public Storage() throws StorageException {
        wellNusDataDirectory = Paths.get(WORKING_DIRECTORY, DATA_DIRECTORY_NAME);
        // For safety, check that the data folder actually exists
        // If it doesn't, create it.
        verifyDataDirectory();
    }

    /**
     * TODO
     * ensure that data folder exists, else create it
     */
    private void verifyDataDirectory() throws StorageException {
        assert wellNusDataDirectory != null : "wellNusDataDirectory path should be set up!";
        boolean directoryExists = Files.exists(wellNusDataDirectory);
        if (directoryExists) {
            return;
        }
        createDataFolder(wellNusDataDirectory);
    }

    /**
     * @param fileName
     */
    protected File getFile(String fileName) throws StorageException {
        Path pathToFile = wellNusDataDirectory.resolve(fileName + FILE_EXTENTION);
        File dataFile = pathToFile.toFile();
        boolean fileExists = dataFile.exists();
        if (!fileExists) {
            createFile(dataFile);
        }
        return dataFile;
    }

    private void createDataFolder(Path directoryPath) throws StorageException {
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException exception) {
            String errorMessage = ERROR_CANNOT_MAKE_DIR + System.lineSeparator();
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
    }

    private void createFile(File file) throws StorageException {
        try {
            file.createNewFile();
        } catch (IOException exception) {
            String errorMessage = ERROR_CANNOT_MAKE_FILE;
            errorMessage = errorMessage.concat(exception.getMessage());
            throw new StorageException(errorMessage);
        }
    }

    /**
     * Tokenized format: .
     * Because the delimiter includes a space,
     * Example: <br>
     * <code>
     * [space]--attr1 payload1\n<br>
     * [space]--attr2 payload2 payload2-1\n<br>
     * [space]--attr3 \n<br>
     * </code>
     *
     * @param tokenizedStrings
     * @return
     */
    protected String tokenizeStringList(ArrayList<String> tokenizedStrings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String entry : tokenizedStrings) {
            StringBuilder entryBuilder = new StringBuilder();
            entryBuilder.append(entry);
            entryBuilder.append(DELIMITER);
            stringBuilder.append(entryBuilder);
        }
        return stringBuilder.toString();
    }

    /**
     * Splits a dataString by the " --" delimiter
     *
     * @param dataString string to be split
     * @return a String[] of words belonging to the dataString
     */
    private String[] splitIntoEntries(String dataString) {
        return dataString.split(DELIMITER);
    }

    /**
     * Detokenizing raw string
     *
     * @param dataString raw string loaded from the text file
     * @return
     */
    protected ArrayList<String> detokenizeDataString(String dataString) {
        String[] entries = splitIntoEntries(dataString);
        return new ArrayList<>(Arrays.asList(entries));
    }

    private void writeDataToDisk(String data, File file) throws StorageException {
        // assume file exists
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            writer.write(data);
            writer.close();
        } catch (IOException exception) {
            throw new StorageException(exception.getMessage());
        }
    }

    private String loadDataFromDisk(File file) throws StorageException {
        // assume file exists
        StringBuilder data = new StringBuilder();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                data.append(reader.nextLine()).append(NEWLINE);
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            throw new StorageException(exception.getMessage());
        }
        return data.toString();
    }

    public void saveData(ArrayList<String> tokenizedManager, String fileName) throws StorageException {
        File file = getFile(fileName);
        String tokenizedString = tokenizeStringList(tokenizedManager);
        writeDataToDisk(tokenizedString, file);
    }

    public ArrayList<String> loadData(String fileName) throws StorageException {
        File file = getFile(fileName);
        String data = loadDataFromDisk(file);
        return detokenizeDataString(data);
    }

    protected void deleteFile(String fileName) throws StorageException {
        File file = getFile(fileName);
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            throw new StorageException(ERROR_CANNOT_DELETE_FILE);
        }
    }

}
