package TextEditor;

import java.util.Optional;

/**
 * Class that implements cut command by implementing the Recordable interface.
 */

public class CutCommand implements Recordable{
    Engine engine;
    Recorder recorder;

    /**
     * CutCommand constructor that initializes a cut command instance
     * @param engine
     * @param invoker
     * @param recorder
     */
    public CutCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        Optional<Memento> memento = Optional.empty();
        this.recorder = recorder;
    }
    
    /**
     * Executes the cut action in the engine and saves the command in the recorder
     */
    public void execute(){
        engine.cutSelectedText();
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
     * Implements the setMemento operation without any action since the cut command generates no memento
     */
    public void setMemento(Memento memento){

    }
    
}
