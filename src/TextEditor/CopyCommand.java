package TextEditor;
/**
 * Class that implements copy command by implementing the Command interface.
 */

public class CopyCommand implements Command{
    Engine engine;

    public CopyCommand(Engine engine){
        this.engine = engine;
    }

    public void execute(){
        engine.copySelectedText();
    }

}
