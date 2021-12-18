package TextEditor;

import java.util.Optional;

/**
 * Class that implements selection change command by implementing the Recordable interface.
 */
public class SelectionCommand implements Recordable{
    private Engine engine;
    private Recorder recorder;
    private Integer beginIndex;
    private Integer endIndex;
    private Boolean wasReplayed = false;

    /**
     * SelectioCommand constructor that initializes a selection change command
     *
     * @param engine EngineImpl instance used for initiating command instance.
     * @param invoker InvokerImpl instance used for initiating command instance.
     * @param recorder Recorder instance used for initiating command instance.
     */
    public SelectionCommand(Engine engine, Invoker invoker, Recorder recorder){
    	this.engine = engine;
        this.recorder = recorder;
    	this.beginIndex = invoker.getBeginIndex();
    	this.endIndex = invoker.getEndIndex();

    }

    /**
     * Executes the selection change action in the engine and saves the command and memento in the recorder
     */
    public void execute(){
        engine.selectionChange(this.beginIndex, this.endIndex);
        recorder.save(this);
        this.wasReplayed = false;
    }
    
    /**
     * Provides the memento object for the selection change command
     * 
     * @return SelectionMemento memento object containing the indexes
     */
	public Optional<Memento> getMemento() {
		return Optional.of(new SelectionMemento(this.beginIndex, this.endIndex));
	}

    /**
     * Changes the beginIndex and endIndex values of the memento object
     * 
     * @param memento the SelectionMemento to be changed 
     */
	public void setMemento(Memento memento) {
	    this.wasReplayed = true;
	    this.beginIndex = ((SelectionMemento)memento).getBeginIndex();
	    this.endIndex = ((SelectionMemento)memento).getEndIndex();	  
	}
	 

}
