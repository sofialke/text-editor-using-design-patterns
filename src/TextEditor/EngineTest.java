package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;
    private static String TEST_STRING = "This is a test string that will be asserted";
    private static String EXCEPTION_MESSAGE_WRONG_BEGIN_INDEX = "Begin index can't be bigger than end index";
    private static String EXCEPTION_MESSAGE_WRONG_END_INDEX = "End index can't be smaller than end index";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        engine.insert(TEST_STRING);
        assertEquals(TEST_STRING, engine.getBufferContents());
        engine.delete();
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void getClipboardContents() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length()-1);
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length()-1);
        engine.cutSelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void copySelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length()-1);
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

}
