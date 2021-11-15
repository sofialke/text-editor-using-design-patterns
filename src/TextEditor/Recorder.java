package TextEditor;public class Recorder {
		
	private InsertMemento memento;
	private InsertCommand command;
	
	public Recorder(InsertCommand command, InsertMemento memento) {
		this.command = command;
		this.memento = memento;
	}
	
	public void save(InsertCommand c) {
		this.memento = c.getMemento(); 	
	}

	
	
	
	

}
