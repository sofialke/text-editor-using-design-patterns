package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;


public class SelectionTest {
    private Engine engine;
    private static String TEST_STRING = "This is a test string that will be asserted";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }
    
    @Test
    void getBeginIndex() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex(),selection.getEndIndex());
        engine.insert(TEST_STRING);
        selection.setEndIndex(6);
        selection.setBeginIndex(4);
        assertEquals(selection.getBeginIndex(),4);
        //these?
        assertTrue(selection.getBeginIndex() <= selection.getEndIndex());
        assertTrue(selection.getBeginIndex() >= selection.getBufferBeginIndex());
        assertTrue(selection.getBeginIndex() <= selection.getBufferEndIndex());
    }
    
    @Test
    void getEndIndex() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferEndIndex(),selection.getBeginIndex(),selection.getEndIndex());
        engine.insert(TEST_STRING);
        selection.setEndIndex(4);;
        assertEquals(selection.getEndIndex(),4);
        assertTrue(selection.getBeginIndex() <= selection.getEndIndex());
        assertTrue(selection.getEndIndex() >= selection.getBufferBeginIndex());
        assertTrue(selection.getEndIndex() <= selection.getBufferEndIndex());
    }
    
    @Test
    void getBufferBeginIndex() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),0);
        engine.insert(TEST_STRING);
        assertEquals(selection.getBufferBeginIndex(),0);
    }
    
    @Test
    void getBufferEndIndex() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferEndIndex(),0);
        engine.insert(TEST_STRING);
        assertEquals(selection.getBufferEndIndex(),TEST_STRING.length());
    }
    
    @Test 
    void setBeginIndex() {
        Selection selection = engine.getSelection();
        engine.insert(TEST_STRING);
        selection.setEndIndex(6);
        selection.setBeginIndex(4);
        assertEquals(selection.getBeginIndex(),4);    	
    }
    
    @Test 
    void setEndIndex() {
        Selection selection = engine.getSelection();
        engine.insert(TEST_STRING);
        selection.setEndIndex(4);
        assertEquals(selection.getEndIndex(),4);    	
    }
}
