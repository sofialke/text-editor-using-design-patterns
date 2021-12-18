package TextEditor;

import java.util.Optional;

/**
 * Class that implements paste command by implementing the Recordable interface.
 */

public class DeleteCommand implements Recordable{
    Engine engine;
    Recorder recorder;

    /**
     * DeleteCommand constructor that initializes a delete command instance
     *
     * @param engine engine class instance used for initiating command instance.
     * @param invoker invoker class instance used for initiating command instance.
     * @param recorder recorder class instance used for initiating command instance.
     */
    public DeleteCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        Optional<Memento> memento = Optional.empty();
        this.recorder = recorder;
    }
    
    /**
     * Executes the delete action in the engine and saves the command in the recorder
     */
    public void execute(){
        engine.delete();
        recorder.save(this);
    }

    /**
     * Provides empty Optional memento object
     * 
     * @return empty Optional object
     */
    public Optional<Memento> getMemento(){
        return Optional.empty();
    }

    /**
     * Implements the setMemento operation without any action since the delete command generates no memento
     */
    public void setMemento(Memento memento){

    }
    

}
