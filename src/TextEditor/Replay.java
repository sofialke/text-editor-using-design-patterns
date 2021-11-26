package TextEditor;
import java.util.Optional;

public class Replay implements Recordable{
    Engine engine;
    Recorder recorder;
    public Replay(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        this.recorder = recorder;
        }

    public Optional<Memento> getMemento(){
        return Optional.empty();
    }

    public void setMemento(Memento memento){

    }

    public void execute(){
        recorder.replay();
    }
}
