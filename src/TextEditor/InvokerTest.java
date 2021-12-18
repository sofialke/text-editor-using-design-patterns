package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class testing InvokerImpl class.
 */
public class InvokerTest {
    private Engine engine;
    private Invoker invoker;
    private Recorder recorder;
    private static String TEST_STRING = "This is a test string that will be asserted";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        invoker = new InvokerImpl(engine);
        recorder = new Recorder();
    }

    @Test
    void testSettingAndGettingTextToBeInserted(){
        invoker.setTextToBeInserted(TEST_STRING);
        assertEquals(TEST_STRING, invoker.getTextToBeInserted());
    }
    @Test
    void testSettingAndGettingIndexes(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setEndIndex(1);
        invoker.setBeginIndex(0);
        assertEquals(0, invoker.getBeginIndex());
        assertEquals(1, invoker.getEndIndex());
    }
    @Test
    void testExecutingInsertCommand(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        assertEquals(TEST_STRING,engine.getBufferContents());
    }
    
    @Test
    void testExecutingSelectionCommand(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(3);
        invoker.setEndIndex(6);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        Selection selectionTest = engine.getSelection();
        assertEquals(3,selectionTest.getBeginIndex());
        assertEquals(6,selectionTest.getEndIndex());
    }
    
    
    @Test
    void testExecutingCopyCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("C", new CopyCommand(engine, this.invoker, this.recorder));
        invoker.execute("C");
        String clipboardTest = engine.getClipboardContents();
        assertEquals("This", clipboardTest);
    }
    
    
    @Test
    void testExecutingDeleteCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("D", new DeleteCommand(engine, this.invoker, this.recorder));
        invoker.execute("D");
        assertEquals(" is a test string that will be asserted", engine.getBufferContents());
        Selection selectionTest = engine.getSelection();
        assertEquals(0,selectionTest.getBeginIndex());
        assertEquals(0,selectionTest.getEndIndex());
        
        invoker.setBeginIndex(6);
        invoker.setEndIndex(10);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("D", new DeleteCommand(engine, this.invoker, this.recorder));
        invoker.execute("D");
        assertEquals(" is a  string that will be asserted", engine.getBufferContents());
        selectionTest = engine.getSelection();
        assertEquals(6,selectionTest.getBeginIndex());
        assertEquals(6,selectionTest.getEndIndex());

    }
    
    @Test
    void testExecutingCutCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("CU", new CutCommand(engine, this.invoker, this.recorder));
        invoker.execute("CU");
        String clipboardTest = engine.getClipboardContents();
        assertEquals("This", clipboardTest);
        assertEquals(" is a test string that will be asserted", engine.getBufferContents());
        Selection selectionTest = engine.getSelection();
        assertEquals(0,selectionTest.getBeginIndex());
        assertEquals(0,selectionTest.getEndIndex());
    }
    
    
    @Test
    void testExecutingPasteCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("C", new CopyCommand(engine, this.invoker, this.recorder));
        invoker.execute("C");
        invoker.setBeginIndex(engine.getSelection().getBufferEndIndex());
        invoker.setEndIndex(engine.getSelection().getBufferEndIndex());
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("P", new PasteCommand(engine, this.invoker, this.recorder));
        invoker.execute("P");
        assertEquals("This is a test string that will be assertedThis",engine.getBufferContents());
    }
    
    @Test 
    void testExecutingReplayInsertCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("R", new Replay(engine, this.invoker, this.recorder));
        invoker.execute("R");
        Selection selection = engine.getSelection();
        assertEquals(selection.getBeginIndex(),0);     
        assertEquals(selection.getEndIndex(),4);               
    }
    
    @Test 
    void testExecutingReplaySelectionCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.addCommand("R", new Replay(engine, this.invoker, this.recorder));
        invoker.execute("R");
        assertEquals("This is a test string that will be assertedThis is a test string that will be asserted",engine.getBufferContents());     
               
    }
    
    @Test 
    void testExecutingReplayDeleteCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(4);
        invoker.setEndIndex(7);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("D", new DeleteCommand(engine, this.invoker, this.recorder));
        invoker.execute("D");   
        assertEquals("This a test string that will be asserted",engine.getBufferContents());     
        invoker.addCommand("R", new Replay(engine, this.invoker, this.recorder));
        invoker.execute("R");        
        assertEquals("Thi a test string that will be asserted",engine.getBufferContents());                  
    }
    
    @Test
    void testExecutingReplayPasteCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("C", new CopyCommand(engine, this.invoker, this.recorder));
        invoker.execute("C");
        invoker.setBeginIndex(engine.getSelection().getBufferEndIndex());
        invoker.setEndIndex(engine.getSelection().getBufferEndIndex());
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("P", new PasteCommand(engine, this.invoker, this.recorder));
        invoker.execute("P");
        invoker.addCommand("R", new Replay(engine, this.invoker, this.recorder));
        invoker.execute("R"); 
        assertEquals("This is a test string that will be assertedThisThis",engine.getBufferContents());
    }
    
    @Test
    void testExecutingReplayCopyCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("C", new CopyCommand(engine, this.invoker, this.recorder));
        invoker.execute("C");
        invoker.addCommand("R", new Replay(engine, this.invoker, this.recorder));
        invoker.execute("R"); 
        String clipboardTest = engine.getClipboardContents();
        assertEquals("This", clipboardTest);
    }
    
    @Test
    void testExecutingReplayCutCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(4);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("CU", new CutCommand(engine, this.invoker, this.recorder));
        invoker.execute("CU");
        invoker.addCommand("R", new Replay(engine, this.invoker, this.recorder));
        invoker.execute("R");
        String clipboardTest = engine.getClipboardContents();
        assertEquals("This", clipboardTest);
        assertEquals(" is a test string that will be asserted", engine.getBufferContents());    }
        
}
