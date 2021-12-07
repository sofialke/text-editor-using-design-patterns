package TextEditor;

public class EditorMemento implements Memento{
	
	private StringBuilder bufferContent;
	private int beginIndex;
	private int endIndex;
	
	public EditorMemento(StringBuilder bufferContent, int beginIndex, int endIndex) {
		
		this.bufferContent = bufferContent;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;		
	}

	public StringBuilder getBufferContent() {
		return bufferContent;
	}

	public void setBufferContent(StringBuilder bufferContent) {
		this.bufferContent = bufferContent;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
