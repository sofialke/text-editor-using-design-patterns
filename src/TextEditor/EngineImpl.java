package TextEditor;

public class EngineImpl implements Engine {

    private StringBuilder buffer;
    private String clipboard;
    private Selection selection; 

    /**
     * EngineImpl constructor that initializes Selection class using implemented Selection class constructor.
     */
    public EngineImpl(){
        this.clipboard = "";
        this.buffer = new StringBuilder();
        selection = new SelectionImpl(buffer);

    }
    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */ 
    @Override
    public Selection getSelection() {
        // TODO
        return selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        
        return this.buffer.toString();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        
        return this.clipboard;
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        
    	copySelectedText();
    	this.buffer.delete(selection.getBeginIndex(), selection.getEndIndex());
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        
    	this.clipboard = this.buffer.substring(selection.getBeginIndex(), selection.getEndIndex());
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        Integer previousBeginIndex = this.selection.getBeginIndex();
    	this.buffer.replace(selection.getBeginIndex(), selection.getEndIndex(), this.clipboard);
        this.selection.setEndIndex(previousBeginIndex + clipboard.length());
        this.selection.setBeginIndex(previousBeginIndex + clipboard.length());
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
    	Integer previousBeginIndex = this.selection.getBeginIndex();
    	this.buffer.replace(selection.getBeginIndex(), selection.getEndIndex(), s);
        this.selection.setEndIndex(previousBeginIndex + s.length());
        this.selection.setBeginIndex(previousBeginIndex + s.length());
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
    	
    	this.buffer.delete(selection.getBeginIndex(), selection.getEndIndex());	
    }
    
    /**
     * Changes the selection indexes
     *
     * @param beginIndex the first index of the selection
     * @param endIndex the last index of the selection
     */
    @Override
    public void selectionChange(int beginIndex, int endIndex) {
    	selection.setBeginIndex(0);
        selection.setEndIndex(0);
    	selection.setEndIndex(endIndex);
    	selection.setBeginIndex(beginIndex);
    }

}

