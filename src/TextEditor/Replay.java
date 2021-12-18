package TextEditor;
import java.util.Optional;

/**
 * Class that implements replay command by implementing the Recordable interface.
 */
public class Replay implements Recordable{
    Engine engine;
    Recorder recorder;
    
    /**
     * Replay constructor that initializes a replay command instance
     * @param engine EngineImpl instance used for initiating command instance.
     * @param invoker InvokerImpl instance used for initiating command instance.
     * @param recorder Recorder instance used for initiating command instance.
     */
    public Replay(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        this.recorder = recorder;
        }
    
    /**
     * Executes the replay action by calling the replay method on recorder
     */
    public void execute(){
        recorder.replay();
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
     * Implements the setMemento operation without any action since the replay command generates no memento
     */
    public void setMemento(Memento memento){

    }

}
