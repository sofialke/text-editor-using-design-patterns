package TextEditor;

import java.util.Objects;

public class Recorder {
		
	private Memento memento;
	private Recordable command;

	/**
	 * Recorder initializer with parameters.
	 * Preconditions - parameters cannot be null.
	 * @param command
	 * @param memento
	 */
	public Recorder(Recordable command, Memento memento) {
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
	public void save(Recordable command) {
		Objects.requireNonNull(command);
		this.memento = command.getMemento();
		this.command = command;
	}
	


	public void execute(){
		this.command.execute();
	}
}
