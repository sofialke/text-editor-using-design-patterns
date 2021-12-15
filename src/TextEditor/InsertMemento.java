package TextEditor;

/**
 * Class that implements the Memento interface
 */
public class InsertMemento implements Memento {
	
	private String textThatWasInserted;
	
	/**
	 * InsertMemento constructor that initializes an insert memento instance
	 * 
	 * @param textThatWasInserted inserted text of the insert memento
	 */
	public InsertMemento(String textThatWasInserted) {
		this.textThatWasInserted = textThatWasInserted;
	}
	
	/**
	 * Provides the value of the inserted text of the insert memento
	 * 
	 * @return textThatWasInserted inserted text of the insert memento
	 */
	public String getText() {
		return textThatWasInserted;
	}
}
