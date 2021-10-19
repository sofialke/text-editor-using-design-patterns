package TextEditor;

public class SelectionImpl implements Selection{

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return
     */
    @Override
    public int getBeginIndex(){
        // TODO
        return null;
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
        // TODO
        return null;
    }

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    @Override
    public int getBufferBeginIndex(){
        // TODO
        return null;
    }

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    @Override
    public int getBufferEndIndex(){
        // TODO
        return null;
    }

    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setBeginIndex(int beginIndex){
        // TODO
        return null;
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setEndIndex(int endIndex){
        // TODO
        return null;
    }
}
