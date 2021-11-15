package TextEditor;

import java.util.Objects;

public class Recorder {
		
	private InsertMemento memento;
	private Command command;

	/**
	 * Recorder initializer with parameters.
	 * Preconditions - parameters cannot be null.
	 * @param command
	 * @param memento
	 */
	public Recorder(Command command, InsertMemento memento) {
		Objects.requireNonNull(command);
		Objects.requireNonNull(memento);
		this.command = command;
		this.memento = memento;
	}

	public Recorder(Command command) {
		Objects.requireNonNull(command);
		this.command = command;
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

	public void execute(){
		this.command.execute();
	}
}
