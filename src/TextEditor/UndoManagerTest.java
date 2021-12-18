package TextEditor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class running tests of UndoManager class.
 */
public class UndoManagerTest {
    private Engine engine;
    private Invoker invoker;
    private UndoManager undoManager;
    private Recorder recorder;
    private static String TEST_STRING = "This is a test string that will be asserted";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        undoManager = new UndoManager();
        engine = new EngineImpl(undoManager);
        invoker = new InvokerImpl(engine);
        recorder = new Recorder();
    }

    @Test
    void testUndoingInsertCommand(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void testRedoingInsertCommand(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        assertEquals(TEST_STRING,engine.getBufferContents());
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        assertEquals(TEST_STRING,engine.getBufferContents());
    }

    @Test
    void testRedoingDeleteCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(2);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("D", new DeleteCommand(engine, this.invoker, this.recorder));
        invoker.execute("D");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        assertEquals("is is a test string that will be asserted", engine.getBufferContents());
    }

    @Test
    void testUndoingDeleteCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(TEST_STRING.length());
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("D", new DeleteCommand(engine, this.invoker, this.recorder));
        invoker.execute("D");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

    @Test
    void testUndoingCutCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(2);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("CU", new CutCommand(engine, this.invoker, this.recorder));
        invoker.execute("CU");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        assertEquals(TEST_STRING, engine.getBufferContents());
    }

    @Test
    void testRedoingCutCommand() {
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setBeginIndex(0);
        invoker.setEndIndex(2);
        invoker.addCommand("S", new SelectionCommand(engine, this.invoker, this.recorder));
        invoker.execute("S");
        invoker.addCommand("CU", new CutCommand(engine, this.invoker, this.recorder));
        invoker.execute("CU");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        assertEquals("is is a test string that will be asserted", engine.getBufferContents());
    }


    @Test
    void testRedoingPasteCommand() {
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
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        assertEquals(TEST_STRING+"This", engine.getBufferContents());
    }

    @Test
    void testUndoingPasteCommand() {
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
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        assertEquals(TEST_STRING, engine.getBufferContents());
    }
    
    
    @Test
    void testMultipleUndoingAndRedoing(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.setTextToBeInserted("First undo test");
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");invoker.setTextToBeInserted("Second undo test");
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        invoker.addCommand("U", new UndoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("U");
        assertEquals(TEST_STRING,engine.getBufferContents());
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        assertEquals(TEST_STRING+"First undo test"+"Second undo test",engine.getBufferContents());
    }
    
    @Test
    void testWrongUndoingAndRedoing(){
    	undoManager.undo(engine);
        assertEquals("", engine.getBufferContents());
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker, this.recorder));
        invoker.execute("I");
        assertEquals(TEST_STRING,engine.getBufferContents());
        invoker.addCommand("RE", new RedoCommand(engine, this.invoker, this.undoManager));
        invoker.execute("RE");
        assertEquals(TEST_STRING,engine.getBufferContents());
    }
}
