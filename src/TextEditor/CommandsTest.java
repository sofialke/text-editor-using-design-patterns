package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {
    private Engine engine;
    private Command command;
    private static String TEST_STRING = "This is a test string that will be asserted";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    @Test
    @DisplayName("Text must be inserted using InsertCommand")
    void insertTextUsingInsertCommandClass() {
        command = new InsertCommand(engine, TEST_STRING);
        command.execute();
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Empty text must be copied using CopyCommand")
    void copyEmptyTextUsingCopyCommandClassWhenNoTextWasInserted() {
        command = new CopyCommand(engine);
        command.execute();
        assertEquals("",engine.getClipboardContents());
    }

    @Test
    @DisplayName("Not-empty text must be copied using CopyCommand")
    void copyTextUsingCopyCommandClassWhenTextWasInsertedAndSelected() {
        command = new InsertCommand(engine, TEST_STRING);
        command.execute();
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        command = new CopyCommand(engine);
        command.execute();
        assertEquals(TEST_STRING,engine.getClipboardContents());
    }

    @Test
    @DisplayName("Text must be pasted using PasteCommand")
    void pasteTextUsingPasteCommandClassWhenTextWasInsertedSelectedAndCopied() {
        command = new InsertCommand(engine, TEST_STRING);
        command.execute();
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        command = new CopyCommand(engine);
        command.execute();
        selection.setEndIndex(0);
        command = new PasteCommand(engine);
        command.execute();
        assertEquals(TEST_STRING+TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Empty text must be pasted using PasteCommand")
    void pasteEmptyTextUsingPasteCommandClassWhenCopiedTextWasEmpty() {
        command = new InsertCommand(engine, TEST_STRING);
        command.execute();
        command = new CopyCommand(engine);
        command.execute();
        command = new PasteCommand(engine);
        command.execute();
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Text must be cut using CutCommand")
    void cutEmptyTextUsingCutCommandClassWhenSelectedTextWasEmpty() {
        command = new InsertCommand(engine, TEST_STRING);
        command.execute();
        command = new CutCommand(engine);
        command.execute();
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Text must be cut using CutCommand")
    void cutAllTextUsingCutCommandClassWhenTheWholeTextWasSelected() {
        command = new InsertCommand(engine, TEST_STRING);
        command.execute();
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        command = new CutCommand(engine);
        command.execute();
        assertEquals("",engine.getBufferContents());
    }
}
