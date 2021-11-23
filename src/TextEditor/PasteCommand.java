package TextEditor;

import java.util.Optional;

/**
 * Class that implements paste command by implementing the Command interface.
 */

public class PasteCommand implements Recordable{
    Engine engine;
    private Recorder recorder;

    public PasteCommand(Engine engine, Invoker invoker, Recorder recorder){
        this.engine = engine;
        Optional<Memento> memento = Optional.empty();
        this.recorder = recorder;
    }

    public Optional<Memento> getMemento(){
        Optional<Memento> memento = Optional.empty();
        return memento;
    }

    public void setMemento(Memento memento){

    }

    public void execute(){
        engine.pasteClipboard();
        recorder.save(this);
    }

}
