package TextEditor;

/**
 * Main API for the text editing engine
 *
 * @author plouzeau
 * @version 1.0
 */

public interface Engine {

    /**
     * Provides access to the selection control object
     * @return the selection object
     */
    Selection getSelection();

    /**
     * Provides the whole contents of the buffer, as a string
     * @return a copy of the buffer's contents
     */
    String getBufferContents();

    /**
     * Provides the clipboard contents
     * @return a copy of the clipboard's contents
     */
    String getClipboardContents();

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    void cutSelectedText();

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    void copySelectedText();

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    void pasteClipboard();

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     * @param s the text to insert
     */
    void insert(String s);
    
    /**
     * Changes the selection indexes
     *
     * @param beginIndex the first index of the selection
     * @param endIndex the last index of the selection
     */
    void selectionChange(int beginIndex, int endIndex);

    /**
     * Removes the contents of the selection in the buffer
     */
    void delete();

    /**
     * Gets memento of the current state of the engine.
     * @return Memento- state of the engine.
     */
    Memento getMemento();

    /**
     * Sets the state on the engine to be the state of the memento sent as a parameter.
     * @param memento- state of the engine that will be set.
     */
    void setMemento(Memento memento);

}

