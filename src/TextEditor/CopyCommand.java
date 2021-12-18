package TextEditor;

import java.util.Optional;

/**
 * Class that implements copy command by implementing the Recordable interface.
 */

public class CopyCommand implements Recordable{
    Engine engine;
    Recorder recorder;

    /**
     * CopyCommand constructor that initializes a copy command instance
     * 
     * @param engine engine class instance used for initiating command instance.
     * @param invoker invoker class  instance used for initiating command instance.
     * @param recorder recorder class  instance used for initiating command instance.
     */
    public CopyCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        Optional<Memento> memento = Optional.empty();
        this.recorder = recorder;
    }
    
    /**
     * Executes the copy action in the engine and saves the command in the recorder
     */
    public void execute(){
        engine.copySelectedText();
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
     * Implements the setMemento operation without any action since the copy command generates no memento
     */
    public void setMemento(Memento memento){

    }


}
