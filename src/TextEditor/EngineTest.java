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
    private static String EXCEPTION_MESSAGE_WRONG_END_INDEX = "End index can't be smaller than begin index";
    private static String EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO = "Begin index can't be smaller than 0";
    private static String EXCEPTION_MESSAGE_EMPTY_SELECTION = "Selection cannot be empty";
    private static String EXCEPTION_MESSAGE_EMPTY_CLIPBOARD = "Clipboard cannot be empty";
    private static String EXCEPTION_MESSAGE_EMPTY_INSERTED_TEXT = "Inserted text cannot be empty";
    private static String EXCEPTION_MESSAGE_WRONG_INDEX_FOR_DELETE = "You cannot delete at the beginning of the buffer";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void testGetInitialSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void testGetBufferContents() {
        engine.insert(TEST_STRING);
        assertEquals(TEST_STRING, engine.getBufferContents());
    }
    
    @Test 
    void testDeleteBuffer() {
    	 engine.insert(TEST_STRING);
         Selection selection = engine.getSelection();
         selection.setBeginIndex(0);
         selection.setEndIndex(TEST_STRING.length());
     	 engine.delete();
         assertEquals("", engine.getBufferContents());
    	}
    
    @Test 
    void testDeleteWithNoSelection() {
        engine.insert(TEST_STRING);
    	engine.delete();
        assertEquals("This is a test string that will be asserte", engine.getBufferContents());
        assertEquals(engine.getSelection().getBeginIndex(),engine.getSelection().getBufferEndIndex());
        assertEquals(engine.getSelection().getEndIndex(),engine.getSelection().getBufferEndIndex());
        assertEquals(engine.getSelection().getBeginIndex(),TEST_STRING.length()-1);

        engine.selectionChange(4, 4);
        int previousIndex = engine.getSelection().getBeginIndex();
        engine.delete();
        engine.delete();
        assertEquals("Th is a test string that will be asserte", engine.getBufferContents());
        assertEquals(engine.getSelection().getBeginIndex(),previousIndex-2);
    }


    @Test
    void testGetClipboardContents() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }

    @Test
    void testCutSelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.cutSelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void testCopySelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

    @Test
    void testPasteClipboard() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        engine.pasteClipboard();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }
    
    @Test
    void testSelectionChange() {
    	engine.insert(TEST_STRING);
    	engine.selectionChange(3, 9);
        assertEquals(3, engine.getSelection().getBeginIndex());
        assertEquals(9, engine.getSelection().getEndIndex());
    }


    @Test
    @Tag("Robustness")
    void testExceptionForBeginIndexBeingBiggerThanEndIndex(){
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(1);
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(0));
        assertEquals(EXCEPTION_MESSAGE_WRONG_END_INDEX, exception1.getMessage());
    }


    @Test
    @Tag("Robustness")
    void testExceptionForEndIndexBeingSmallerThanBeginIndex(){
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(5);
        Exception exception2 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(6));
        assertEquals(EXCEPTION_MESSAGE_WRONG_BEGIN_INDEX, exception2.getMessage());
    }


    @Test
    @Tag("Robustness")
    void testExceptionForBeforeIndex(){
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(-1));
        assertEquals(EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO, exception1.getMessage());
    }

    @Test
    @Tag("Robustness")
    void testExceptionForEndIndex(){
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                selection.setEndIndex(-1));
        assertEquals(EXCEPTION_MESSAGE_WRONG_END_INDEX, exception.getMessage());
    } 
    
    @Test
    @Tag("Robustness")
    void testExceptionForEmptyInsert(){
    	String s = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                engine.insert(s));
        assertEquals(EXCEPTION_MESSAGE_EMPTY_INSERTED_TEXT, exception.getMessage()); 
    }
  

}
