package TextEditor;

import java.util.List;
import java.util.Objects;

/**
 * Caretaker class for Engine. It saves the past and future states of the engine, as well as performs redo and undo
 * on the engine.
 */
public class UndoManager {

    private List<Memento> pastStates;
    private List<Memento> futureStates;
    private Engine engine;

    /**
     * Constructor for the UndoManager with engine as a parameter
     * @param engine- EngineImpl instance.
     */
    public UndoManager(Engine engine){
        this.engine = engine;
    }

    /**
     * Method that stores the current memento state in the list.
     */
    public void store(){
        Memento memento = this.engine.getMemento();
        this.pastStates.add(memento);
    }

    /**
     * Method that undo's the last change in the engine.
     */
    public void undo(){
        Memento currentMemento = this.engine.getMemento();
        Memento pastMemento = pastStates.get(pastStates.size()-1);
        this.futureStates.add(currentMemento);
        this.engine.setMemento(pastMemento);
        this.pastStates.remove(pastMemento);
    }

    /**
     * Method that redo's the last change in the engine.
     */
    public void redo(){
        Memento currentMemento = this.engine.getMemento();
        Memento futureMemento = this.futureStates.get(futureStates.size()-1);
        this.engine.setMemento(futureMemento);
        this.pastStates.add(currentMemento);
        this.futureStates.remove(futureMemento);
    }
}
