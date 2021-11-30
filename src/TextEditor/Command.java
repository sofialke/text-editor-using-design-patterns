package TextEditor;

/**
 * Command interface implemented by the concrete command classes
 * Extended by the Recordable interface
 */
public interface Command {
    /**
     * Execute method which will be invoked by the invoker who doesn't know what kind of command will be executed.
     */
    void execute();
    
}
