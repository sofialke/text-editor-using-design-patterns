package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class PasteCommand implements Command{
    Engine engine;

    public PasteCommand(Engine engine){
        this.engine = engine;
    }

    public void execute(){
        engine.pasteClipboard();
    }

}
