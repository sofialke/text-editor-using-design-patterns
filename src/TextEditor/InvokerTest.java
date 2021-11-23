package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
public class InvokerTest {
    private Engine engine;
    private Invoker invoker;
    private static String TEST_STRING = "This is a test string that will be asserted";
    private static String EXCEPTION_MESSAGE_WRONG_BEGIN_INDEX = "Begin index can't be bigger than end index";
    private static String EXCEPTION_MESSAGE_WRONG_END_INDEX = "End index can't be smaller than end index";
    private static String EXCEPTION_MESSAGE_BEGIN_INDEX_SMALLER_THAN_ZERO = "Begin index can't be smaller than 0";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        invoker = new InvokerImpl(engine);
//        invoker.addCommand("C", new CopyCommand(engine));
//        invoker.addCommand("CU", new CutCommand(engine));
//        invoker.addCommand("S", new SelectionCommand(engine, this.invoker));
//        invoker.addCommand("D", new CutCommand(engine));
//        invoker.addCommand("P", new PasteCommand(engine));
    }

    @Test
    void testSettingAndGettingTextToBeInserted(){
        invoker.setTextToBeInserted(TEST_STRING);
        assertEquals(TEST_STRING, invoker.getTextToBeInserted());
    }
    @Test
    void testSettingAndGettingIndexes(){
        invoker.setTextToBeInserted(TEST_STRING);
        invoker.addCommand("I", new InsertCommand(engine, this.invoker));
        invoker.execute("I");
        invoker.setEndIndex(1);
        invoker.setBeginIndex(0);
        assertEquals(0, invoker.getBeginIndex());
        assertEquals(1, invoker.getEndIndex());
    }
}
