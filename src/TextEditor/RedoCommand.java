package TextEditor;

import java.util.Optional;

public class RedoCommand implements Recordable {
    private Engine engine;
    private UndoManager undomanager;
    
    /**
     * RedoCommand constructor that initializes a redo command instance
     * 
     * @param engine
     * @param invoker
     * @param undomanager
     */
    public RedoCommand(Engine engine, Invoker invoker, UndoManager undomanager){
        this.undomanager = undomanager;
        this.engine = engine;
    }
    
    /**
     * Implements the setMemento operation without any action since the redo command generates no memento
     */
    public void setMemento(Memento memento){

    }

    /**
     * Executes the redo action by calling the redo method on UndoManager
     */
    public void execute(){
        undomanager.redo(engine);
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
