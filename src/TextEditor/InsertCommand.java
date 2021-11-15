package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class InsertCommand implements Command{
    Engine engine;
	private String textToBeInserted;
    private Boolean wasReplayed = false;

    public InsertCommand(Engine engine, Invoker invoker){
        this.engine = engine;
        this.textToBeInserted = invoker.getTextToBeInserted();
    }

    public void execute(){
        engine.insert(this.textToBeInserted);
        this.wasReplayed = false;
    }
    
    public InsertMemento getMemento() {
    	return new InsertMemento(this.textToBeInserted);
    }

    public void setMemento(InsertMemento memento) {
        this.wasReplayed = true;
        this.textToBeInserted = memento.getText();
    }

}
