package TextEditor;

/**
 * Implementation class for editor memento. It implements Memento interface.
 */
public class EditorMemento implements Memento{
	
	private final String bufferContent;
	private final int beginIndex;
	private final int endIndex;

	/**
	 * Constructor of EditorMemento instance with parameters of the Engine.
	 * @param bufferContent contents of the engine.
	 * @param beginIndex begin index of the current selection in the engine.
	 * @param endIndex end index of the current selection in the engine.
	 */
	public EditorMemento(String bufferContent, int beginIndex, int endIndex) {
		this.bufferContent = bufferContent;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;		
	}

	/**
	 * Method to get buffer content of the memento.
	 * @return bufferContent- Stringbuilder
	 */
	public String getBufferContent() {
		return bufferContent;
	}

	/**
	 * Method to get begin index of the memento.
	 * @return beginIndex - Integer
	 */
	public int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * Method to get end index of the current engine state.
	 * @return
	 */
	public int getEndIndex() {
		return endIndex;
	}

}
