package TextEditor;

public class SelectionMemento implements Memento {
	
	private int beginIndex;
	private int endIndex;

	
	public SelectionMemento(int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	
	public int getBeginIndex() {
		return beginIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
	
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}
