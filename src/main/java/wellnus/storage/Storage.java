package wellnus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * TODO add logger and defensive checks
 * <p>
 * Each manager provides
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
    protected static final String DELIMITER = " --";
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
            throw new StorageException(exception.getMessage());
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
     * @param mapToTokenize
     * @return
     */
    protected String tokenizeHashmap(HashMap<String, String> mapToTokenize) {
        StringBuilder stringBuilder = new StringBuilder();
        // Prefer enhanced for loop over lambda for developer accessibility
        for (Map.Entry<String, String> attributePayloadPair : mapToTokenize.entrySet()) {
            // An entry is of format "--keyName payload \n"
            StringBuilder entryBuilder = new StringBuilder();

            // Append the attribute key
            entryBuilder.append(DELIMITER);
            entryBuilder.append(attributePayloadPair.getKey());

            // Append the attribute payload
            entryBuilder.append(WHITESPACE_PADDING);
            entryBuilder.append(attributePayloadPair.getValue());

            entryBuilder.append(NEWLINE);
            stringBuilder.append(entryBuilder);
        }
        return stringBuilder.toString();
    }

    private String getAttributeFromEntry(String[] entry) {
        return entry[ATTRIBUTE_INDEX];
    }

    private String getPayloadFromEntry(String[] entry) {
        StringBuilder builder = new StringBuilder();
        for (int i = PAYLOAD_INDEX_START; i < entry.length; i += 1) {
            builder.append(entry[i]);
            builder.append(WHITESPACE_PADDING);
        }
        return builder.toString().trim();
    }

    /**
     * Splits a datastring by the " --" delimiter
     *
     * @param dataString
     * @return
     */
    private String[] splitIntoEntries(String dataString) {
        String[] tokens = dataString.split(DELIMITER);
        return tokens;
    }

    private String[] splitEntryIntoWords(String entry) {
        return entry.split(WHITESPACE_PADDING);
    }

    /**
     * Detokenizing raw string
     *
     * @param dataString
     * @return
     */
    protected HashMap<String, String> detokenizeDataString(String dataString) {
        String[] entries = splitIntoEntries(dataString);
        HashMap<String, String> attributePayloadPair = new HashMap<>();
        for (String entry : entries) {
            String[] entryWords = splitEntryIntoWords(entry);
            String attribute = getAttributeFromEntry(entryWords);
            String payload = getPayloadFromEntry(entryWords);
            attributePayloadPair.put(attribute, payload);
        }
        // Remove trailing empty attribute
        attributePayloadPair.remove(EMPTY_STRING);
        return attributePayloadPair;
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

    public void saveData(HashMap<String, String> tokenizedManager, String fileName) throws StorageException {
        File file = getFile(fileName);
        String tokenizedString = tokenizeHashmap(tokenizedManager);
        writeDataToDisk(tokenizedString, file);
    }

    public HashMap<String, String> loadData(String fileName) throws StorageException {
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
