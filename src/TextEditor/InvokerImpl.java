package TextEditor;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InvokerImpl implements Invoker{
    //map with an id for every command, only one instance per command. there will be a parameter that will hold the text
    //that will be inserted. the map will have keys- I for Insert, D for Delete itp

    private Engine engine;
    private Recorder recorder;
    private String textToBeInserted;
    private Map<String,Command> mapOfCOmmands;
    private Integer beginIndex = 0;
    private Integer endIndex = 0;

    public InvokerImpl(Engine engineInput){
        engine = engineInput;
        mapOfCOmmands = new HashMap<>();
        //QUITAR, HACER EN INVOKER TEST
        //invoker.addCommand("I",new InsertCommand(engine, this, recorder))
        mapOfCOmmands.put("I", new InsertCommand(engine, this, recorder));
        mapOfCOmmands.put("CO", new CopyCommand(engine));
        mapOfCOmmands.put("CU", new CutCommand(engine));
        mapOfCOmmands.put("S", new SelectionCommand(engine, this, recorder));
        mapOfCOmmands.put("D", new CutCommand(engine));
        mapOfCOmmands.put("P", new PasteCommand(engine));
    }

    public String getTextToBeInserted(){
        return this.textToBeInserted;
    }
    
    public void addCommand(Command c, String key) {
    	mapOfCOmmands.put(key, c);
    }

    public Integer getBeginIndex(){
        return this.beginIndex;
    }

    public Integer getEndIndex(){
        return this.endIndex;
    }

    public void setTextToBeInserted(String text){
        this.textToBeInserted = text;
        mapOfCOmmands.put("I", new InsertCommand(engine, this, recorder));
    }

    public void setBeginIndex(Integer index){
        this.beginIndex = index;
        mapOfCOmmands.put("S", new SelectionCommand(engine, this, recorder));
    }

    public void setEndIndex(Integer index){
        this.endIndex = index;
        mapOfCOmmands.put("S", new SelectionCommand(engine, this, recorder));
    }

    public void execute(String keyName){
        mapOfCOmmands.get(keyName).execute();
    }
}
