package TextEditor;

/**
 * Class that implements the Memento interface
 */
public class SelectionMemento implements Memento {
	
	private int beginIndex;
	private int endIndex;

	/**
	 * SelectionMemento constructor that initializes a SelectionMemento instance
	 * 
	 * @param beginIndex begin index of the changed selection memento
	 * @param endIndex end index of the changed selection memento
	 */
	public SelectionMemento(int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	
	/**
	 * Provides the value of the beginIndex of the changed selection memento
	 * 
	 * @return beginIndex begin index of the selection memento
	 */
	public int getBeginIndex() {
		return beginIndex;
	}
	
	/**
	 * Provides the value of the endIndex of the selection memento
	 * 
	 * @return endIndex end index of the selection memento
	 */
	public int getEndIndex() {
		return endIndex;
	}
}
