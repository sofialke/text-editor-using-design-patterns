package TextEditor;

public class InsertMemento implements Memento {
	
	private String textThatWasInserted;
	
	public InsertMemento(String textThatWasInserted) {
		this.textThatWasInserted = textThatWasInserted;
	}
	
	public String getText() {
		return textThatWasInserted;
	}
	
	public void setText(String textThatWasInserted) {
		this.textThatWasInserted = textThatWasInserted;
	}

}
