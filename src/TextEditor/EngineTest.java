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
    private static String EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO = "Begin index can't be smaller than 0";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    //private void todo() {
    //    fail("Unimplemented test");
    //}
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
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        engine.delete();
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void getClipboardContents() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        engine.cutSelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void copySelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

    @Test
    void pasteClipboard() {
        engine.insert(TEST_STRING);
        engine.pasteClipboard();
        assertEquals(TEST_STRING, engine.getBufferContents());
        Selection selection = engine.getSelection();
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        engine.pasteClipboard();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForBeginIndexBeingBiggerThanEndIndex(){
        //Write a test class which puts a beginIndex value before the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setEndIndex(6);
        selection.setBeginIndex(5);
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(1));
        assertEquals(EXCEPTION_MESSAGE_WRONG_END_INDEX, exception1.getMessage()); //TODO - define what should be a message of this exception
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForEndIndexBeingSmallerThanBeginIndex(){
        //Write a test class which puts a beginIndex value before the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setEndIndex(5);
        Exception exception2 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(6));
        assertEquals(EXCEPTION_MESSAGE_WRONG_BEGIN_INDEX, exception2.getMessage()); //TODO - define what should be a message of this exception
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForBeforeIndex(){
        //Write a test class which puts a beginIndex value before the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(-1));
        assertEquals(EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO, exception1.getMessage()); //TODO - define what should be a message of this exception
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForEndIndex(){
        //Write a test class which puts a beginIndex value end the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                selection.setEndIndex(-1));
        assertEquals(EXCEPTION_MESSAGE_WRONG_END_INDEX, exception.getMessage()); //TODO- -define what should be a message of this exception
    }
}
