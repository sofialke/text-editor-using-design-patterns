package TextEditor;

/**
 * Class that implements selection change command by implementing the Command interface.
 */
public class SelectionCommand implements Recordable{
    private Engine engine;
    private Recorder recorder;
    private Integer beginIndex;
    private Integer endIndex;
    private Boolean wasReplayed = false;

    public SelectionCommand(Engine engine, Invoker invoker, Recorder recorder){
    	this.engine = engine;
    	this.recorder = recorder;
    	this.beginIndex = invoker.getBeginIndex();
    	this.endIndex = invoker.getEndIndex();

    }
    
    public void execute(){
        engine.selectionChange(this.beginIndex, this.endIndex);
        recorder.save(this);
        this.wasReplayed = false;
    }
    
	@Override
	public SelectionMemento getMemento() { 
		return new SelectionMemento(this.beginIndex, this.endIndex); 
	}
	
	@Override
	public void setMemento(Memento memento) {
	    this.wasReplayed = true;
	    this.beginIndex = ((SelectionMemento)memento).getBeginIndex(); 
	    this.endIndex = ((SelectionMemento)memento).getEndIndex();	  
	}
	 

}
