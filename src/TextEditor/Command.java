package TextEditor;

public interface Command {
    /**
     * Execute method which will be invoked by the invoker who doesn't know what kind of command will be executed.
     */
    void execute();
    
}
