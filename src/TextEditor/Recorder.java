package TextEditor;

import java.util.Objects;
import java.util.Optional;

public class Recorder {
		
	private Optional<Memento> memento;
	private Recordable command;

	/**
	 * Recorder initializer without parameters.
	 */
	public Recorder() {
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

	public void replay(){
		if (memento.isPresent()){
			this.command.setMemento(memento.get());
		}
		execute();
	}

	public void execute(){
		this.command.execute();
	}
}
