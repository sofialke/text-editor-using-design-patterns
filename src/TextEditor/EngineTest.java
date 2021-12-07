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
    private static String EXCEPTION_MESSAGE_EMPTY_INSERTED_TEXT = "Inserted text cannot be empty";

    
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    //private void todo() {
    //    fail("Unimplemented test");
    //}
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getInitialSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }
    
    @Test
    void insertTextIntoTheBuffer(){
        engine.insert(TEST_STRING);
        assertEquals(TEST_STRING.length(), engine.getSelection().getBeginIndex());
        assertEquals(TEST_STRING.length(), engine.getSelection().getEndIndex());
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        engine.insert(TEST_STRING);
        assertEquals(TEST_STRING, engine.getBufferContents());
    }
    
    @Test 
    void deleteBuffer() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
    	engine.delete();
        assertEquals("", engine.getBufferContents());
    	}

    
    @Test 
    void deleteWithNoSelection() {
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
    void getClipboardContents() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.cutSelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void copySelectedText() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        engine.copySelectedText();
        assertEquals(TEST_STRING, engine.getClipboardContents());
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

    @Test
    void pasteClipboard() {
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(TEST_STRING.length());
        engine.copySelectedText();
        engine.pasteClipboard();
        assertEquals(TEST_STRING, engine.getClipboardContents());
    }
    
    @Test
    void selectionChange() {
    	engine.insert(TEST_STRING);
    	engine.selectionChange(3, 9);
        assertEquals(3, engine.getSelection().getBeginIndex());
        assertEquals(9, engine.getSelection().getEndIndex());
    }
    
    @Test
    void copyAndPasteNothing() {
    	engine.insert(TEST_STRING);
    	engine.copySelectedText();
    	assertEquals(engine.getClipboardContents(), "");
    	engine.pasteClipboard();
    	assertEquals(engine.getBufferContents(), TEST_STRING);
    }
    
    @Test
    void cutNothing() {
    	engine.insert(TEST_STRING);
    	engine.cutSelectedText();
    	assertEquals(engine.getClipboardContents(), "");
    	assertEquals(engine.getBufferContents(), TEST_STRING);
    }
    
    @Test 
    void deleteAtTheBeginningOfBuffer() {
    	engine.insert(TEST_STRING);
    	engine.selectionChange(0, 0);
    	engine.delete();
    	assertEquals(engine.getBufferContents(), TEST_STRING);
    }



    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForBeginIndexBeingBiggerThanEndIndex(){
        //Write a test case which puts a beginIndex value before the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(1);
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(0));
        assertEquals(EXCEPTION_MESSAGE_WRONG_END_INDEX, exception1.getMessage()); //TODO - define what should be a message of this exception
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForEndIndexBeingSmallerThanBeginIndex(){
        //Write a test case which puts a beginIndex value before the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(0);
        selection.setEndIndex(5);
        Exception exception2 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(6));
        assertEquals(EXCEPTION_MESSAGE_WRONG_BEGIN_INDEX, exception2.getMessage()); //TODO - define what should be a message of this exception
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForBeforeIndex(){
        //Write a test case which puts a beginIndex value before the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(-1));
        assertEquals(EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO, exception1.getMessage()); //TODO - define what should be a message of this exception
    }

    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForEndIndex(){
        //Write a test case which puts a beginIndex value end the index value of a buffer and check if exception is
        //thrown correctly. the same for end Index @Tag("Robustness")
        engine.insert(TEST_STRING);
        Selection selection = engine.getSelection();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                selection.setEndIndex(-1));
        assertEquals(EXCEPTION_MESSAGE_WRONG_END_INDEX, exception.getMessage()); //TODO- -define what should be a message of this exception
    }
    
	/*
	 * @Test
	 * 
	 * @Tag("Robustness") //needs importing a tag junit library void
	 * exceptionTestingForEmptySelectionCopy(){ //Write a test case which tries to
	 * copy an empty selection and check if exception is //thrown correctly.
	 * engine.insert(TEST_STRING); Exception exception =
	 * assertThrows(IllegalArgumentException.class, () ->
	 * engine.copySelectedText()); assertEquals(EXCEPTION_MESSAGE_EMPTY_SELECTION,
	 * exception.getMessage()); }
	 */
	/*
	 * @Test
	 * 
	 * @Tag("Robustness") //needs importing a tag junit library void
	 * exceptionTestingForEmptySelectionCut(){ //Write a test case which tries to
	 * cut an empty selection and check if exception is //thrown correctly.
	 * engine.insert(TEST_STRING); Exception exception =
	 * assertThrows(IllegalArgumentException.class, () -> engine.cutSelectedText());
	 * assertEquals(EXCEPTION_MESSAGE_EMPTY_SELECTION, exception.getMessage()); }
	 */
	/*
	 * @Test
	 * 
	 * @Tag("Robustness") //needs importing a tag junit library void
	 * exceptionTestingForEmptyClipboardPaste(){ //Write a test case which tries to
	 * paste with an empty clipboard and check if exception is //thrown correctly.
	 * Exception exception = assertThrows(IllegalArgumentException.class, () ->
	 * engine.pasteClipboard()); assertEquals(EXCEPTION_MESSAGE_EMPTY_CLIPBOARD,
	 * exception.getMessage()); }
	 */
    
    @Test
    @Tag("Robustness") //needs importing a tag junit library
    void exceptionTestingForEmptyInsert(){
        //Write a test case which tries to insert an empty text and check if exception is
        //thrown correctly.
    	String s = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                engine.insert(s));
        assertEquals(EXCEPTION_MESSAGE_EMPTY_INSERTED_TEXT, exception.getMessage()); 
    }
    
	/*
	 * @Test
	 * 
	 * @Tag("Robustness") //needs importing a tag junit library void
	 * exceptionTestingForDeleteAtBegginingOfBuffer(){ //Write a test case which
	 * tries to delete at the beggining of the buffer //and check if exception is
	 * thrown correctly. Exception exception =
	 * assertThrows(IndexOutOfBoundsException.class, () -> engine.delete());
	 * assertEquals(EXCEPTION_MESSAGE_WRONG_INDEX_FOR_DELETE,
	 * exception.getMessage()); }
	 */
}
