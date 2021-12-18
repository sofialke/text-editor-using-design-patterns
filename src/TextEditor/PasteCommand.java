package TextEditor;

import java.util.Optional;

/**
 * Class that implements paste command by implementing the Recordable interface.
 */

public class PasteCommand implements Recordable{
    Engine engine;
    private Recorder recorder;

    /**
     * PasteCommand constructor that initializes a paste command instance
     * 
     * @param engine EngineImpl instance used for initiating command instance.
     * @param invoker InvokerImpl instance used for initiating command instance.
     * @param recorder InvokerImpl instance used for initiating command instance.
     */
    public PasteCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        Optional<Memento> memento = Optional.empty();
        this.recorder = recorder;
    }
    
    /**
     * Executes the paste action in the engine and saves the command in the recorder
     */
    public void execute(){
        engine.pasteClipboard();
        recorder.save(this);
    }
    
    /**
     * Provides empty Optional memento object
     * 
     * @return empty Optional object
     */
    public Optional<Memento> getMemento(){
        Optional<Memento> memento = Optional.empty();
        return memento;
    }

    /**
     * Implements the setMemento operation without any action since the paste command generates no memento
     */
    public void setMemento(Memento memento){

    }
    

}
