package TextEditor;

import java.util.Objects;

public class Recorder {
		
	private InsertMemento memento;
	private InsertCommand command;
	
	public Recorder(InsertCommand command, InsertMemento memento) {
		Objects.requireNonNull(command);
		Objects.requireNonNull(memento);
		this.command = command;
		this.memento = memento;
	}
	
	public void save(InsertCommand command) {
		Objects.requireNonNull(command);
		this.memento = c.getMemento();
	}

	
	
	
	

}
