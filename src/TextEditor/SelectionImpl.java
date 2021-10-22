package TextEditor;

public class SelectionImpl implements Selection{

    private StringBuilder buffer;
    private int beginIndex;
    private int endIndex;
    private int bufferBeginIndex;
    private int bufferEndIndex;

    /**
     * Selection cunstructor which sets the beginIndex and endIndex to 0.
     */
    public SelectionImpl(){
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0;
        this.bufferEndIndex = 0;
    }

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return
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
        return bufferEndIndex;
    }

    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setBeginIndex(int beginIndex) throws IndexOutOfBoundsException{
        if(beginIndex <= this.endIndex){
            this.beginIndex = beginIndex;
        }else {
            throw new IndexOutOfBoundsException("Begin index can't be bigger than end index");
        }
        return;
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
        return;
    }
}
