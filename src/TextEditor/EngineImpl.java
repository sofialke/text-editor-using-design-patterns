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
     * 
     * @throws IllegalArgumentException if the selection is empty
     */
    @Override
    public void cutSelectedText() throws IllegalArgumentException {
    	if (selection.getBeginIndex()==selection.getEndIndex()) {
            throw new IllegalArgumentException("Selection cannot be empty");
    	}else {
        	copySelectedText();
        	delete();
    	}

    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     * 
     * @throws IllegalArgumentException if the selection is empty
     */
    @Override
    public void copySelectedText() throws IllegalArgumentException {
        if(selection.getBeginIndex()==selection.getEndIndex()) {
            throw new IllegalArgumentException("Selection cannot be empty");
        }else {
        	this.clipboard = this.buffer.substring(selection.getBeginIndex(), selection.getEndIndex());
        }
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     * 
     * @throws IllegalArgumentException if clipboard is empty
     */
    @Override
    public void pasteClipboard() throws IllegalArgumentException {
    	if (clipboard.isEmpty()) {
            throw new IllegalArgumentException("Clipboard cannot be empty");
    	}else {
            Integer previousBeginIndex = this.selection.getBeginIndex();
        	this.buffer.replace(selection.getBeginIndex(), selection.getEndIndex(), this.clipboard);
            this.selection.setEndIndex(previousBeginIndex + clipboard.length());
            this.selection.setBeginIndex(previousBeginIndex + clipboard.length());
    	}
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     * @throws IllegalArgumentException if inserted text is empty
     */
    @Override
    public void insert(String s) throws IllegalArgumentException {
    	if (s.isEmpty()) {
            throw new IllegalArgumentException("Inserted text cannot be empty");
    	} else {      	
        	Integer previousBeginIndex = this.selection.getBeginIndex();
        	this.buffer.replace(selection.getBeginIndex(), selection.getEndIndex(), s);
            this.selection.setEndIndex(previousBeginIndex + s.length());
            this.selection.setBeginIndex(previousBeginIndex + s.length());
    	}

    }

    /**
     * Removes the contents of the selection in the buffer
     * 
     * @throws IndexOutOfBoundsException if the selection is empty and at the beginning of the buffer 
     */
    @Override
    public void delete() throws IndexOutOfBoundsException {
    	
    	if(this.selection.getBeginIndex() == this.selection.getBufferBeginIndex() && this.selection.getBeginIndex() == this.selection.getEndIndex()) {
    		throw new IndexOutOfBoundsException("You cannot delete at the beginning of the buffer");
    	} else {
    		
        	if (this.selection.getBeginIndex() == this.selection.getEndIndex()) {
        		
            	Integer previousBeginIndex = this.selection.getBeginIndex();
        		this.buffer.delete((selection.getBeginIndex()-1), selection.getBeginIndex());
        		this.selection.setBeginIndex(previousBeginIndex-1);
                this.selection.setEndIndex(previousBeginIndex-1);

        	}else {
            	
            	Integer previousBeginIndex = this.selection.getBeginIndex();
            	this.buffer.delete(selection.getBeginIndex(), selection.getEndIndex());
                this.selection.setEndIndex(previousBeginIndex);
        		
        	}    		
    	}

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

    /**
     * Gets memento of the current state of the engine.
     * @return Memento- state of the engine.
     */
    @Override
    public Memento getMemento(){
        return new EditorMemento(this.buffer, this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    /**
     * Sets the state on the engine to be the state of the memento sent as a parameter.
     * @param memento- state of the engine that will be set.
     */
    @Override
    public void setMemento(Memento memento){
        this.buffer = ((EditorMemento)memento).getBufferContent();
        this.selectionChange(((EditorMemento)memento).getBeginIndex(), ((EditorMemento)memento).getEndIndex());
    }

}

