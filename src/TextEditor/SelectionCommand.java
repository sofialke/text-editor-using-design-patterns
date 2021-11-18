package TextEditor;

/**
 * @author Usuario
 *
 */
public class SelectionCommand implements Command{
    private Engine engine;
    private Integer beginIndex;
    private Integer endIndex;
    private Boolean wasReplayed = false;


    public SelectionCommand(Engine engine, Invoker invoker){
    	this.engine = engine;
    	this.beginIndex = invoker.getBeginIndex();
    	this.endIndex = invoker.getEndIndex();

    }
    
    public void execute(){
        engine.selectionChange(this.beginIndex, this.endIndex);
        this.wasReplayed = false;
    }
    
	
	public SelectionMemento getMemento() { 
		return new SelectionMemento(this.beginIndex, this.endIndex); 
	}
	  
	public void setMemento(SelectionMemento memento) {
	    this.wasReplayed = true;
	    this.beginIndex = memento.getBeginIndex(); 
	    this.endIndex = memento.getEndIndex();	  
	}
	 

}
