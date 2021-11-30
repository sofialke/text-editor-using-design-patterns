package TextEditor;

import java.util.Optional;

/**
 * Class that implements paste command by implementing the Recordable interface.
 */

public class InsertCommand implements Recordable{
    Engine engine;
    private Recorder recorder;
	private String textToBeInserted;
    private Boolean wasReplayed = false;


    /**
     * InsertCommand constructor that initializes an insert command instance
     * 
     * @param engine
     * @param invoker
     * @param recorder
     */
    public InsertCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        this.recorder = recorder;
        this.textToBeInserted = invoker.getTextToBeInserted();
    }

    /**
     * Executes the insert action in the engine and saves the command and memento in the recorder
     */
    public void execute(){
        engine.insert(this.textToBeInserted);
        recorder.save(this);
        this.wasReplayed = false;
    }

    /**
     * Provides the memento object for the insert command
     * 
     * @return InsertMemento memento object containing the inserted text
     */
    public Optional<Memento> getMemento() {
        return Optional.of(new InsertMemento(this.textToBeInserted));
    }

    /**
     * Changes the textToBeInserted value of the memento object
     * 
     * @param memento the InsertMemento to be changed 
     */
    @Override
    public void setMemento(Memento memento) {
        this.wasReplayed = true;
        this.textToBeInserted = ((InsertMemento)memento).getText();
    }

}
