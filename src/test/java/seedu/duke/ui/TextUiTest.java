package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.ui.TextUi.getCommand;
import static seedu.duke.ui.TextUi.printErrorFor;
import static seedu.duke.ui.TextUi.printOutputMessage;


class TextUiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getCommand_trimSpace_success() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream((" My string "+System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner readLine = new Scanner(System.in);
        String command = getCommand(readLine);
        assertEquals("My string", command);
        System.setIn(sysInBackup);
    }

    @Test
    void printSeparator_defaultVersion_success() {
        int numOfChar = 5;
        TextUi.printSeparator(numOfChar);
        assertEquals("-----", outputStreamCaptor.toString().trim());
    }

    @Test
    void printErrorFor_arithmeticException_success() {
        try {
            int result = 2/0;
        } catch (ArithmeticException exception) {
            printErrorFor(exception);
        }
        assertEquals("--------------------"+
                    "--------------------"+System.lineSeparator()+
                    "    / by zero"+System.lineSeparator()+
                    "--------------------"+
                    "--------------------",
                    outputStreamCaptor.toString().trim());
    }

    @Test
    void printOutputMessage_greeting_success() {
        String greeting = "How are you?";
        printOutputMessage(greeting);
        assertEquals("--------------------" +
                "--------------------" + System.lineSeparator()+
                "    How are you?" + System.lineSeparator() +
                "--------------------" +
                "--------------------", outputStreamCaptor.toString().trim());
    }

}

