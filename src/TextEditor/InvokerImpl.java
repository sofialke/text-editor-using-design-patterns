package TextEditor;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InvokerImpl implements Invoker{

    private Engine engine;
    private String textToBeInserted;
    private Map<String,Recordable> mapOfCOmmands;
    private Integer beginIndex = 0;
    private Integer endIndex = 0;

    /**
     * Invoker constructor that initializes an Invoker instance
     * It has a mapOfCommands Map attribute that contains instances of commands with a string as key representig the type of command
     * 
     * @param engineInput
     */
    public InvokerImpl(Engine engineInput){
        this.engine = engineInput;
        mapOfCOmmands = new HashMap<>();

    }
    
    /**
     * Provides the text that will be inserted
     * 
     * @return the text to be inserted 
     */
    public String getTextToBeInserted(){
        return this.textToBeInserted;
    }
    
    /**
     * adds a command instance to the map of commands
     * 
     * @param key - string representing the type of command
     * @param command - instance of a command to be executed
     */
    public void addCommand(String key, Recordable command) {
    	mapOfCOmmands.put(key, command);
    }

    /**
     * Provides the begin index in the selection
     * 
     * @return begin index of selection
     */
    public Integer getBeginIndex(){
        return this.beginIndex;
    }
    
    /**
     * Provides the end index in the selection
     * 
     * @return end index of selection
     */
    public Integer getEndIndex(){
        return this.endIndex;
    }

    /**
     * Changes the value of the text to be inserted
     * 
     * @param text - new value to be set
     */
    public void setTextToBeInserted(String text){
        this.textToBeInserted = text;
    }

    /**
     * Changes the value of the begin index
     * 
     * @param index - new value to be set
     */
    public void setBeginIndex(Integer index){
        this.beginIndex = index;
    }

    /**
     * Changes the value of the end index
     * 
     * @param text - new value to be set
     */
    public void setEndIndex(Integer index){
        this.endIndex = index;
    }

    /**
     * Executed the command in the map that belongs to the specific key
     * 
     * @param keyname - string key representing a command
     */
    public void execute(String keyName){
        mapOfCOmmands.get(keyName).execute();
    }
}
