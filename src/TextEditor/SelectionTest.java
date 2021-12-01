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

}
