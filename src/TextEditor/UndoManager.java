package TextEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Caretaker class for Engine. It saves the past and future states of the engine, as well as performs redo and undo
 * on the engine.
 */
public class UndoManager {

    private List<Memento> pastStates;
    private List<Memento> futureStates;

    /**
     * Constructor for the UndoManager with engine as a parameter
     * 
     */
    public UndoManager(){
        pastStates = new ArrayList<Memento>();
        futureStates = new ArrayList<Memento>();
    }

    /**
     * Method that stores the current memento state in the list.
     * 
     * @param engine - EngineImpl instance.
     */
    public void store(Engine engine){
        Memento memento = engine.getMemento();
        this.pastStates.add(memento);
    }

    /**
     * Method that un-does the last change in the engine.
     * 
     * @param engine - EngineImpl instance.
     */
    public void undo(Engine engine){
    	if (!this.pastStates.isEmpty()) {
    		Memento currentMemento = engine.getMemento();
            Memento pastMemento = pastStates.get(pastStates.size()-1);
            this.futureStates.add(currentMemento);
            engine.setMemento(pastMemento);
            this.pastStates.remove(pastMemento);
    	}       
    }

    /**
     * Method that re-does the last change in the engine.
     * 
     * @param engine- EngineImpl instance.
     */
    public void redo(Engine engine){
    	if (!this.futureStates.isEmpty()) {
    		Memento currentMemento = engine.getMemento();
            Memento futureMemento = this.futureStates.get(futureStates.size()-1);
            engine.setMemento(futureMemento);
            this.pastStates.add(currentMemento);
            this.futureStates.remove(futureMemento);
    	}
       
    }
}
