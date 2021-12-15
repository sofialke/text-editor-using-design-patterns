package TextEditor;

/**
 * Invoker interface implemented by the InvokerImpl
 *
 */
public interface Invoker {
	
    /**
     * Provides the text that will be inserted 
     * @return the text to be inserted 
     */
    String getTextToBeInserted();
    
    /**
     * Provides the begin index in the selection
     * @return begin index of selection
     */
    Integer getBeginIndex();
    
    /**
     * Provides the end index in the selection
     * @return end index of selection
     */
    Integer getEndIndex();
    
    /**
     * Changes the value of the text to be inserted
     * @param text - new value to be set
     */
    void setTextToBeInserted(String text);
    
    /**
     * Changes the value of the begin index
     * @param index - new value to be set
     */
    void setBeginIndex(Integer index);

    /**
     * Changes the value of the end index
     * @param text - new value to be set
     */
    void setEndIndex(Integer index);
    
    /**
     * Executed the command in the map that belongs to the specific key
     * @param keyname - string key representing a command
     */
    void execute(String keyName);
    
    /**
     * adds a command instance to the map of commands 
     * @param key - string representing the type of command
     * @param command - instance of a command to be executed
     */
    void addCommand(String key, Recordable command);
}
