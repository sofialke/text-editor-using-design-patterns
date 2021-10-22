package TextEditor;
/**
 * Class that implements copy command by implementing the Command interface.
 */

public class CopyCommand implements Command{
    EngineImpl engine;

    public CopyCommand(EngineImpl engine){
        this.engine = engine;
    }

    public void execute(){
        engine.copySelectedText();
    }

}
