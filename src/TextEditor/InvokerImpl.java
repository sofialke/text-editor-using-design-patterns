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
    private String textToBeInserted;
    private Map<String,Recordable> mapOfCOmmands;
    private Integer beginIndex = 0;
    private Integer endIndex = 0;

    public InvokerImpl(Engine engineInput){
        this.engine = engineInput;
        mapOfCOmmands = new HashMap<>();
        //QUITAR, HACER EN INVOKER TEST
        //invoker.addCommand("I",new InsertCommand(engine, this, recorder))
    }

    public String getTextToBeInserted(){
        return this.textToBeInserted;
    }
    
    public void addCommand(String key, Recordable command) {
    	mapOfCOmmands.put(key, command);
    }

    public Integer getBeginIndex(){
        return this.beginIndex;
    }

    public Integer getEndIndex(){
        return this.endIndex;
    }

    public void setTextToBeInserted(String text){
        this.textToBeInserted = text;
    }

    public void setBeginIndex(Integer index){
        this.beginIndex = index;
    }

    public void setEndIndex(Integer index){
        this.endIndex = index;
    }

    public void execute(String keyName){
        mapOfCOmmands.get(keyName).execute();
    }
}
