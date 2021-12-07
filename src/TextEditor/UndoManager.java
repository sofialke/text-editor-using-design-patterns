package TextEditor;

import java.util.List;
import java.util.Objects;

public class UndoManager {

    private List<Memento> pastStates;
    private List<Memento> futureStates;
    private Engine engine;

    public void store(){
        //memento = engine.getMemento
        //put memento in paststates
    }

    public void undo(){
        //setMemento(last memento from the pastlist)
        //last state goes to future states
    }

    public void redo(){
        //setMemento(last memento from the futurelist)
        //
    }
}
