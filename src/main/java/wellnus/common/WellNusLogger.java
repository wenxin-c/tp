package wellnus.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import wellnus.exception.StorageException;
import wellnus.ui.TextUi;

/**
 * Wrapper class for <code>java.util.logging.Logger</code> that redirects logging
 * to a specific log file instead of printing it on the user's screen.
 * @see Logger
 */
public class WellNusLogger {
    private static final String CREATE_LOG_FILE_IO_EXCEPTION_MESSAGE = "Failed to create log file.";
    private static final String EXCEPTION_NOTE_MESSAGE = "Logging will not be performed during this app session.";
    private static final String INVALID_LOG_PATH_MESSAGE = "Invalid log path, cannot create log file.";
    private static final String LOG_DIR_PATH = "log/";
    private static final String LOG_FILE_NAME = "wellnus.log";
    private static final String LOG_PATH_BLANK_MESSAGE = "Blank log path passed to WellNusLogger.checkLogPath().";
    private static final String LOG_PATH_NULL_MESSAGE = "null log path passed to WellNusLogger.checkLogPath().";
    private static final String LOGGER_NAME_BLANK_MESSAGE = "Name parameter given to WellNusLogger.getLogger() cannot "
            + "be blank";
    private static final String LOGGER_NAME_NULL_MESSAGE = "Name parameter given to WellNusLogger.getLogger() cannot "
            + "be null";
    private static final String IO_EXCEPTION_MESSAGE = "Failed to load log file.";
    private static final int MAX_LOG_FILE_SIZE_MEGA_BYTES = 5;
    private static final String SECURITY_EXCEPTION_MESSAGE = "Unable to create log file due to security policies.";

    private static void checkLogPath(String logPath) {
        assert logPath != null : LOG_PATH_NULL_MESSAGE;
        assert !logPath.isBlank() : LOG_PATH_BLANK_MESSAGE;
        TextUi textUi = new TextUi();
        try {
            File logFile = new File(logPath);
            Path parentDir = logFile.getParentFile().toPath();
            Files.createDirectories(parentDir);
            if (!logFile.exists()) {
                logFile.createNewFile();
            } else {
                int megabyteDivisor = 1024 * 1024;
                long logFileSizeInMegaBytes = logFile.length() / megabyteDivisor;
                if (logFileSizeInMegaBytes <= MAX_LOG_FILE_SIZE_MEGA_BYTES) {
                    return;
                }
                // Log file is more than 5 MBs in size, clear it to preserve user's storage space
                logFile.delete();
                logFile.createNewFile();
            }
        } catch (InvalidPathException invalidPathException) {
            StorageException storageException = new StorageException(INVALID_LOG_PATH_MESSAGE);
            textUi.printErrorFor(storageException, EXCEPTION_NOTE_MESSAGE);
        } catch (IOException ioException) {
            StorageException storageException = new StorageException(CREATE_LOG_FILE_IO_EXCEPTION_MESSAGE);
            textUi.printErrorFor(storageException, EXCEPTION_NOTE_MESSAGE);
        } catch (SecurityException securityException) {
            StorageException storageException = new StorageException(SECURITY_EXCEPTION_MESSAGE);
            textUi.printErrorFor(storageException, EXCEPTION_NOTE_MESSAGE);
        }
    }

    /**
     * Returns an instance of Java's Logger class that directs all logging to a specific
     * log file instead of the user's screen.
     * @param loggerName Name of the logger instance to create and return
     * @return Instance of Logger that logs to a specific file
     */
    public static Logger getLogger(String loggerName) {
        assert loggerName != null : LOGGER_NAME_NULL_MESSAGE;
        assert !loggerName.isBlank() : LOGGER_NAME_BLANK_MESSAGE;
        Logger logger = Logger.getLogger(loggerName);
        FileHandler fileHandler;
        TextUi textUi = new TextUi();
        try {
            String logPath = LOG_DIR_PATH + LOG_FILE_NAME;
            checkLogPath(logPath);
            fileHandler = new FileHandler(logPath, true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (SecurityException securityException) {
            StorageException storageException = new StorageException(SECURITY_EXCEPTION_MESSAGE);
            textUi.printErrorFor(storageException, EXCEPTION_NOTE_MESSAGE);
        } catch (IOException ioException) {
            StorageException storageException = new StorageException(IO_EXCEPTION_MESSAGE);
            textUi.printErrorFor(storageException, EXCEPTION_NOTE_MESSAGE);
        }
        return logger;
    }
}
