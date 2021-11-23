package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
public class InvokerTest {
    private Engine engine;
    private Invoker invoker;
    private Recorder recorder;
    private static String TEST_STRING = "This is a test string that will be asserted";
    private static String EXCEPTION_MESSAGE_WRONG_BEGIN_INDEX = "Begin index can't be bigger than end index";
    private static String EXCEPTION_MESSAGE_WRONG_END_INDEX = "End index can't be smaller than end index";
    private static String EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO = "Begin index can't be smaller than 0";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        invoker = new InvokerImpl(engine);
        recorder = new Recorder();
//        invoker.addCommand("C", new CopyCommand(engine, this.invoker));
//        invoker.addCommand("CU", new CutCommand(engine, this.invoker));
//        invoker.addCommand("S", new SelectionCommand(engine, this.invoker));
//        invoker.addCommand("D", new DeleteCommand(engine, this.invoker));
//        invoker.addCommand("P", new PasteCommand(engine, this.invoker));
//        invoker.addCommand("R", new Replay(engine));
        //recorder should be one of the parameters when creating a command!!!
        //recorder is a  receiver like engine - we should create it here and send it to the commands when we create them
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
}
