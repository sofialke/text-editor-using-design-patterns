package TextEditor;

import java.util.Objects;
import java.util.Optional;

public class Recorder {
		
	private Optional<Memento> memento;
	private Recordable command;

	/**
	 * Recorder initializer with parameters.
	 * Preconditions - parameters cannot be null.
	 * @param command
	 * @param memento
	 */
	public Recorder(Recordable command) {
		Objects.requireNonNull(command);
		this.command = command;
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
