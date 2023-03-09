package tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UITest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void draw_main_separator() {
        String feature = "m";
        int length = 5;
        UI.drawLineSeparator(feature, length);
        assertEquals("=====", outputStreamCaptor.toString().trim());
    }

    @Test
    void draw_atomic_separator() {
        String feature = "A";
        int length = 5;
        UI.drawLineSeparator(feature, length);
        assertEquals("-----", outputStreamCaptor.toString().trim());
    }

    @Test
    void draw_reflect_separator() {
        String feature = "r";
        int length = 5;
        UI.drawLineSeparator(feature, length);
        assertEquals("~~~~~", outputStreamCaptor.toString().trim());
    }
}