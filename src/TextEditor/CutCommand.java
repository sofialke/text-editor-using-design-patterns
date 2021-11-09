package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class CutCommand implements Command{
    Engine engine;

    public CutCommand(Engine engine){
        this.engine = engine;
    }

    public void execute(){
        engine.cutSelectedText();
    }

}
