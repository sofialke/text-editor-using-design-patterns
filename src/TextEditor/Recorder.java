package TextEditor;

import java.util.Objects;

public class Recorder {
		
	private InsertMemento memento;
	private InsertCommand command;

	/**
	 * Recorder initializer with parameters.
	 * Preconditions - parameters cannot be null.
	 * @param command
	 * @param memento
	 */
	public Recorder(InsertCommand command, InsertMemento memento) {
		Objects.requireNonNull(command);
		Objects.requireNonNull(memento);
		this.command = command;
		this.memento = memento;
	}

	/**
	 * A method to save a command.
	 * Precondition - input parameter cannot be null.
	 * @param command
	 */
	public void save(InsertCommand command) {
		Objects.requireNonNull(command);
		this.memento = command.getMemento();
	}

	
	
	
	

}
