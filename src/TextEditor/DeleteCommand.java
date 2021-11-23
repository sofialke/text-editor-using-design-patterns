package TextEditor;

import java.util.Optional;

/**
 * Class that implements paste command by implementing the Command interface.
 */

public class DeleteCommand implements Recordable{
    Engine engine;
    Recorder recorder;

    public DeleteCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        Optional<Memento> memento = Optional.empty();
        this.recorder = recorder;
    }

    public Optional<Memento> getMemento(){
        return Optional.empty();
    }

    public void setMemento(Memento memento){

    }

    public void execute(){
        engine.delete();
        recorder.save(this);
    }

}
