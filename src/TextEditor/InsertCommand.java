package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class InsertCommand implements Recordable{
    Engine engine;
    private Recorder recorder;
	private String textToBeInserted;
    private Boolean wasReplayed = false;

    public InsertCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        this.recorder = recorder;
        this.textToBeInserted = invoker.getTextToBeInserted();
    }

    public void execute(){
        engine.insert(this.textToBeInserted);
        recorder.save(this);
        this.wasReplayed = false;
    }
    
    @Override
    public InsertMemento getMemento() {
    	
       	return new InsertMemento(this.textToBeInserted);
    }

    @Override
    public void setMemento(Memento memento) {
        this.wasReplayed = true;
        this.textToBeInserted = ((InsertMemento)memento).getText();
    }

}
