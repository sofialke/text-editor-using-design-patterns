package TextEditor;

/**
 * Implementation class for editor memento. It implements Memento interface.
 */
public class EditorMemento implements Memento{
	
	private StringBuilder bufferContent;
	private int beginIndex;
	private int endIndex;

	/**
	 * Constructor of EditorMemento instance with parameters of the Engine.
	 * @param bufferContent contents of the engine.
	 * @param beginIndex begin index of the current selection in the engine.
	 * @param endIndex end index of the current selection in the engine.
	 */
	public EditorMemento(StringBuilder bufferContent, int beginIndex, int endIndex) {
		
		this.bufferContent = bufferContent;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;		
	}

	/**
	 * Method to get buffer content of the memento.
	 * @return bufferContent- Stringbuilder
	 */
	public StringBuilder getBufferContent() {
		return bufferContent;
	}

	/**
	 * Method to set buffer content of the memento.
	 * @param bufferContent - current engine's buffer content.
	 */
	public void setBufferContent(StringBuilder bufferContent) {
		this.bufferContent = bufferContent;
	}

	/**
	 * Method to get begin index of the memento.
	 * @return beginIndex - Integer
	 */
	public int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * Method to set begin index of the memento.
	 * @param beginIndex beginIndex of the current engine state.
	 */
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	/**
	 * Method to get end index of the current engine state.
	 * @return
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * Method to set end index of the engine.
	 * @param endIndex - endindex of the memento.
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
