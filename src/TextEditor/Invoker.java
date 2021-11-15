package TextEditor;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Invoker {
    //map with an id for every command, only one instance per command. there will be a parameter that will hold the text
    //that will be inserted. the map will have keys- I for Insert, D for Delete itp

    private Engine engine;
    private String textToBeInserted;
    private Map<String,Command> mapOfCOmmands;

    public Invoker(){
        engine = new EngineImpl();
        mapOfCOmmands = new HashMap<>();
        mapOfCOmmands.put("I", new InsertCommand(engine, this));
        mapOfCOmmands.put("CO", new CopyCommand(engine));
        mapOfCOmmands.put("CU", new CutCommand(engine));
        mapOfCOmmands.put("S", new SelectionCommand(engine));
        mapOfCOmmands.put("D", new CutCommand(engine));
    }

    public String getTextToBeInserted(){
        return this.textToBeInserted;
    }
}
