package TextEditor;

import java.util.Optional;

/**
 * Class that implements paste command by implementing the Command interface.
 */

public class InsertCommand implements Recordable{
    Engine engine;
    private Recorder recorder;
	private String textToBeInserted;
    private Boolean wasReplayed = false;

    public InsertCommand(Engine engine, Invoker invoker){
        this.engine = engine;
        this.recorder = new Recorder(this);
        this.textToBeInserted = invoker.getTextToBeInserted();
    }

    public void execute(){
        engine.insert(this.textToBeInserted);
        recorder.save(this);
        this.wasReplayed = false;
    }

    public Optional<Memento> getMemento() {
        return Optional.of(new InsertMemento(this.textToBeInserted));
    }

    @Override
    public void setMemento(Memento memento) {
        this.wasReplayed = true;
        this.textToBeInserted = ((InsertMemento)memento).getText();
    }

}
