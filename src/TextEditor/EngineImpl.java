package TextEditor;

public class EngineImpl implements Engine {

    private StringBuilder buffer;
    private String clipboard;

    /**
     * EngineImpl constructor that initializes Selection class using implemented Selection class constructor.
     */
    public EngineImpl(){
        SelectionImpl selection = new SelectionImpl();
        this.clipboard = "";
        this.buffer = new StringBuilder();
    }
    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        // TODO
        return this.getSelection();
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
        
    	Selection sel = this.getSelection();
    	String cutText = this.buffer.substring(sel.getBeginIndex(), sel.getEndIndex());
    	this.buffer.delete(sel.getBeginIndex(), sel.getEndIndex());
    	this.clipboard = cutText;
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        
    	Selection sel = this.getSelection();
    	this.clipboard = this.buffer.substring(sel.getBeginIndex(), sel.getEndIndex());
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {

    	Selection sel = this.getSelection();
    	this.buffer.replace(sel.getBeginIndex(), sel.getEndIndex(), this.clipboard);
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
    	
    	Selection sel = this.getSelection();
    	this.buffer.replace(sel.getBeginIndex(), sel.getEndIndex(), s);
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
    	
    	Selection sel = this.getSelection();
    	this.buffer.delete(sel.getBeginIndex(), sel.getEndIndex());	
    }

}

