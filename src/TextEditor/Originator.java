package TextEditor;

import java.util.Optional;

/**
 * Originator interface implemented by the concrete command classes
 * Extended by the Recordable interface
 */
public interface Originator {
	
    /**
     * Provides the memento object (or an empty Optional object)
     * 
     * @return memento object of the executed command
     */
	Optional<Memento> getMemento();
	
    /**
     * Changes the content values of the memento object
     * 
     * @param memento to be changed 
     */
	void setMemento(Memento memento);

}
