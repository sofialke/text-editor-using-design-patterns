package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class PasteCommand implements Command{
    EngineImpl engine;

    public PasteCommand(EngineImpl engine){
        this.engine = engine;
    }

    public void execute(){
        engine.pasteClipboard();
    }

}
