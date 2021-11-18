package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;
//WE HAVE TO CHANGE THIS CLASS COMPLETELY!
class CommandsTest {
    private Engine engine;
    private Invoker invoker;
    private static String TEST_STRING = "This is a test string that will be asserted";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        invoker = new InvokerImpl(engine);
    }

    @Test
    @DisplayName("Text must be inserted using InsertCommand")
    void insertTextUsingInsertCommandClass() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.execute("I");
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Empty text must be copied using CopyCommand")
    void copyEmptyTextUsingCopyCommandClassWhenNoTextWasInserted() {
        invoker.execute("CO");
        assertEquals("",engine.getClipboardContents());
    }

    @Test
    @DisplayName("Not-empty text must be copied using CopyCommand")
    void copyTextUsingCopyCommandClassWhenTextWasInsertedAndSelected() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.execute("I");
        invoker.setEndIndex(TEST_STRING.length());
        invoker.execute("S");
        invoker.execute("CO");
        assertEquals(TEST_STRING,engine.getClipboardContents());
    }

    @Test
    @DisplayName("Text must be pasted using PasteCommand")
    void pasteTextUsingPasteCommandClassWhenTextWasInsertedSelectedAndCopied() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.execute("I");
        invoker.setEndIndex(TEST_STRING.length());
        invoker.execute("S");
        invoker.execute("CO");
        invoker.setEndIndex(0);
        invoker.execute("S");
        invoker.execute("P");
        assertEquals(TEST_STRING+TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Empty text must be pasted using PasteCommand")
    void pasteEmptyTextUsingPasteCommandClassWhenCopiedTextWasEmpty() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.execute("I");
        invoker.execute("CO");
        invoker.execute("P");
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Text must be cut using CutCommand")
    void cutEmptyTextUsingCutCommandClassWhenSelectedTextWasEmpty() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.execute("I");
        invoker.execute("CU");
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    @DisplayName("Text must be cut using CutCommand")
    void cutAllTextUsingCutCommandClassWhenTheWholeTextWasSelected() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.execute("I");
        invoker.setEndIndex(TEST_STRING.length());
        invoker.execute("S");
        invoker.execute("CU");
        assertEquals("",engine.getBufferContents());
    }
}
