package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class InsertCommand implements Command{
    Engine engine;
	private String s;

    public InsertCommand(Engine engine, String s){
        this.engine = engine;
        this.s = s;
    }

    public void execute(){
        engine.insert(s);
    }

}
