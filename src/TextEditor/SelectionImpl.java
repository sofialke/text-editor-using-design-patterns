package TextEditor;

/**
 * SelectionImpl class implementing Selection interface. Used for keeping track of the current selection in the buffer.
 */
public class SelectionImpl implements Selection{

    private StringBuilder buffer;
    private int beginIndex;
    private int endIndex;
    private int bufferBeginIndex;

    /**
     * Selection constructor which sets the beginIndex and endIndex to 0.
     * @param buffer Stringbuilder value of the buffer's content.
     */
    public SelectionImpl(StringBuilder buffer){
    	this.buffer = buffer;
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0;
    }

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return the begin index
     */
    @Override
    public int getBeginIndex(){
        return this.beginIndex;
    }

    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    @Override
    public int getEndIndex(){

        return this.endIndex;
    }

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    @Override
    public int getBufferBeginIndex(){
        // TODO for now buffer begin index will always be 0. In the future we will introduce a different, transparent approach.
        return bufferBeginIndex;
    }

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    @Override
    public int getBufferEndIndex(){
        // TODO for now buffer end index will be the last index of the buffer. In the future we will introduce a different, transparent approach.
        return buffer.length();
    }

    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setBeginIndex(int beginIndex) throws IndexOutOfBoundsException{
        if(beginIndex <= this.endIndex && beginIndex >= 0){
            this.beginIndex = beginIndex;
        }else if(beginIndex < 0){
            throw new IndexOutOfBoundsException("Begin index can't be smaller than 0");
        }else if(beginIndex < bufferBeginIndex){
            throw new IndexOutOfBoundsException("Begin index can't be smaller than begin buffer index");
        }else {
            throw new IndexOutOfBoundsException("Begin index can't be bigger than end index");
        }
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setEndIndex(int endIndex){
        if(endIndex >= this.beginIndex){
            this.endIndex = endIndex;
        }else {
            throw new IndexOutOfBoundsException("End index can't be smaller than end index");
        }
    }
}
