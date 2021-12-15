package TextEditor;

import java.util.Optional;

/**
 * Class that implements undo command by implementing the Recordable interface.
 */
public class UndoCommand implements Recordable{
    private Engine engine;
    private UndoManager undomanager;
    
    
    /**
     * UndoCommand constructor that initializes an undo command instance
     * 
     * @param engine
     * @param invoker
     * @param undomanager
     */
    public UndoCommand(Engine engine, Invoker invoker, UndoManager undomanager){
        this.undomanager = undomanager;
        this.engine = engine;
    }
    
    /**
     * Implements the setMemento operation without any action since the undo command generates no memento
     */
    public void setMemento(Memento memento){

    }

    /**
     * Executes the replay action by calling the undo method on UndoManager
     */
    public void execute(){
        undomanager.undo(engine);
    }

    /**
     * Provides empty Optional memento object
     * 
     * @return empty Optional object
     */
	@Override
	public Optional<Memento> getMemento() {
        return Optional.empty(); 
 	}

}