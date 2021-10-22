package TextEditor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionTest {
    private Engine engine;
    private static String TEST_STRING = "This is a test string that will be asserted";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }
}
