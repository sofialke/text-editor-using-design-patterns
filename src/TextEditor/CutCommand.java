package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class CutCommand implements Command{
    EngineImpl engine;

    public CutCommand(EngineImpl engine){
        this.engine = engine;
    }

    public void execute(){
        engine.cutSelectedText();
    }

}
